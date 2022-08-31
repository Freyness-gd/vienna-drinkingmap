import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.*;

import javax.xml.stream.*;
import javax.xml.stream.events.*;


public class OSMParser {

    private static OSMParser parser;
    private static XMLEventReader eventReader;
    private final String path;

    private OSMParser(String path) {

        this.path = path;

        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            eventReader = factory.createXMLEventReader(new FileReader(path));

        } catch (FileNotFoundException e) { e.printStackTrace(); }
          catch (XMLStreamException e) { e.printStackTrace(); }

    };

    public static synchronized OSMParser getInstance(String path){
        if(parser == null)
            parser = new OSMParser(path);
        return parser;
    }

    public void getBounds(OSM osm) throws XMLStreamException {

        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            eventReader = factory.createXMLEventReader(new FileReader(path));

        } catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (XMLStreamException e) { e.printStackTrace(); }

        boolean boundsFound = false;

        while(eventReader.hasNext() && !boundsFound){
            XMLEvent event = eventReader.nextEvent();

            if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                StartElement startElement = event.asStartElement();
                String name = startElement.getName().getLocalPart();

                if(name.equalsIgnoreCase("bounds")){
                    Iterator<Attribute> it = startElement.getAttributes();
                    try{
                        double minlon = Double.parseDouble(it.next().getValue());
                        double maxlat = Double.parseDouble(it.next().getValue());
                        double minlat = Double.parseDouble(it.next().getValue());
                        double maxlon = Double.parseDouble(it.next().getValue());

                        osm.setConstraints(minlon, maxlon, minlat, maxlat);

                        boundsFound = true;
                    }catch (NoSuchElementException e){
                        System.out.println("Incorrect Bounds! Check File!\n" );
                        e.printStackTrace();
                    }

                }
            }
        }

    }

    public void getVertices(Map<String, Vertex> vertex){

        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            eventReader = factory.createXMLEventReader(new FileReader(path));

        } catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (XMLStreamException e) { e.printStackTrace(); }

        try{
            while(eventReader.hasNext()){

                if(eventReader.peek().getEventType() == XMLStreamConstants.START_ELEMENT &&
                eventReader.peek().asStartElement().getName().getLocalPart().equalsIgnoreCase("way")){
                    System.out.println("Vertices have been parsed succesfully!");
                    System.out.println("Amount of vertices: " + vertex.size() + "\n");
                    return;
                }

                XMLEvent event = eventReader.nextEvent();

                if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                    StartElement s = event.asStartElement();
                    String name = s.getName().getLocalPart();

                    if(name.equalsIgnoreCase("node")){
                        Iterator<Attribute> it = s.getAttributes();
                        double lon = Double.parseDouble(it.next().getValue());
                        String id = it.next().getValue();
                        it.next();
                        double lat = Double.parseDouble(it.next().getValue());

                        vertex.put(id, new Vertex(id, lon, lat));
                    }
                }
            }
        } catch (XMLStreamException e) { e.printStackTrace(); }

    }


    public Street getWayByID(String id, Map<String, Vertex> vertex){

        ArrayList<String> f_nodes = new ArrayList<>();
        String f_name = "";
        String f_type = "none";
        String saved_k = "";

        try{
            XMLInputFactory factory = XMLInputFactory.newInstance();
            eventReader = factory.createXMLEventReader(new FileReader(path));

        } catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (XMLStreamException e) { e.printStackTrace(); }

        try{

            while(eventReader.hasNext()){

                XMLEvent event = eventReader.nextEvent();

                if(event.getEventType() == XMLStreamConstants.START_ELEMENT){

                    StartElement s = event.asStartElement();
                    String name = s.getName().getLocalPart();

                    if(name.equalsIgnoreCase("way")){
                        Iterator<Attribute> it = s.getAttributes();
                        if(it.next().getValue().equals(id)){
                            while(eventReader.hasNext()){
                                event = eventReader.nextEvent();

                                if(event.getEventType() == XMLStreamConstants.END_ELEMENT && event.asEndElement().getName().getLocalPart().equalsIgnoreCase("way")){
                                    //System.out.print("Tag: " + event.asEndElement().getName().getLocalPart() + " / ");
                                    //System.out.println("On line: " + event.getLocation().getLineNumber());
                                    break;
                                }
                                else if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                                    StartElement event_s = event.asStartElement();
                                    Iterator<Attribute> event_it = event_s.getAttributes();

                                    if(!event_s.getName().getLocalPart().equals("nd")){
                                        while(event_it.hasNext()){
                                            String sv;
                                            switch(sv = event_it.next().getValue()){
                                                case "highway":
                                                    f_type = "highway-" + saved_k;
                                                    break;
                                                case "name":
                                                    f_name = saved_k;
                                                    break;
                                            }
                                            System.out.println(sv);
                                            saved_k = sv;
                                        }
                                    } else f_nodes.add(event_it.next().getValue());


                                }
                            }
                        }
                    }

                }

            }

        } catch (XMLStreamException e) { e.printStackTrace(); }

        /*System.out.println("Parsing DONE!");
        System.out.println("/////////////////////////////////////////\n");

        System.out.println("NAME: " + f_name);
        System.out.println("TYPE: " + f_type);
        System.out.println("NODES: " + f_nodes + "\n");*/

        Street street = new Street(id, f_name, f_type);

        //for(String i : f_nodes) System.out.println(vertex.get(i));

        for(int i = 1; i < f_nodes.size(); i++){
            street.addEdge(new Edge(vertex.get(f_nodes.get(i-1)), vertex.get(f_nodes.get(i))));
        }
        return street;
    }


    public ArrayList<Street> getStreets (Map<String, Vertex> vertices) throws XMLStreamException, FileNotFoundException {


        XMLInputFactory factory = XMLInputFactory.newInstance();
        eventReader = factory.createXMLEventReader(new FileReader(path));




        int nr_streets = 0;
        ArrayList<Street> streets = new ArrayList<>();
        String saved_k = "";

        while(eventReader.hasNext()){
            XMLEvent event = eventReader.nextEvent();

            String f_id = "none";
            String f_type = "none";
            String f_name = "none";
            ArrayList<String> f_nodes = new ArrayList<>();

            if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
                StartElement s = event.asStartElement();
                String name = s.getName().getLocalPart();

                if(name.equalsIgnoreCase("way")){
                    Iterator<Attribute> it = s.getAttributes();
                    f_id = it.next().getValue();


                    while(eventReader.hasNext()){
                        event = eventReader.nextEvent();

                        if(event.getEventType() == XMLStreamConstants.END_ELEMENT &&
                                event.asEndElement().getName().getLocalPart().equalsIgnoreCase("way")){
                            if(/*f_name.equals("none") || */!f_type.contains("highway")) break;
                            Street street = new Street(f_id, f_name, f_type);
                            for(int i = 1; i < f_nodes.size(); i++){
                                street.addEdge(new Edge(vertices.get(f_nodes.get(i-1)), vertices.get(f_nodes.get(i))));
                            }
                            streets.add(street);
                            break;
                        }else if (event.getEventType() == XMLStreamConstants.START_ELEMENT){
                            StartElement event_s = event.asStartElement();
                            Iterator<Attribute> event_it = event_s.getAttributes();

                            if(!event_s.getName().getLocalPart().equals("nd")){
                                while(event_it.hasNext()){
                                    String sv;
                                    switch(sv = event_it.next().getValue()){
                                        case "highway":
                                            f_type = "highway-" + saved_k;
                                            break;
                                        case "name":
                                            f_name = saved_k;
                                            break;
                                    }
                                    saved_k = sv;
                                }
                            }else{
                                String node_id = event_it.next().getValue();
                                if(vertices.containsKey(node_id)){
                                    f_nodes.add(node_id);
                                }
                            }
                        }
                    }
                }

            }
        }



        return streets;
    }

}
