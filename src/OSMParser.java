import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.xml.stream.*;
import javax.xml.stream.events.*;


public class OSMParser {

    private static OSMParser parser;
    private static XMLEventReader eventReader;

    private OSMParser(String path) {

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

    public static void getVertices(ArrayList<Vertex> vertex){

    }


}
