import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import codedraw.*;
import java.time.LocalTime;
public class main {

    public static OSM osm = OSM.getInstance("data/map.osm");
    public static HashMap<String, Street> streets = new HashMap<>();

    public static int windowrange = 1000;
    public static GUI gui = GUI.getInstance(windowrange, OSM.getBounds());
    public static OSMParser parser = OSMParser.getInstance("data/map.osm");
    public static AdjMatrix matrix = new AdjMatrix(osm);

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        LocalTime start = LocalTime.now();

        streets.putAll(OSMParser.getStreets(OSM.getVertex()));

//        for(Street i : streets.values()){
//            for(Edge e : i.getEdges()){
//                GUI.drawNode(e.gethead());
//                GUI.drawNode(e.gettail());
//            }
//        }

        for(Street i : streets.values()){
            switch(i.getType()){
                case "highway-primary":
                    GUI.setColour(Color.red);
                    GUI.setLineWidth(4);
                    GUI.drawStreet(i);
                    break;
                case "highway-secondary":
                    GUI.setColour(Color.orange);
                    GUI.setLineWidth(3);
                    GUI.drawStreet(i);
                    break;
                case "highway-tertiary":
                    GUI.setColour(Color.GREEN);
                    GUI.setLineWidth(3);
                    GUI.drawStreet(i);
                    break;
                case "highway-residential":
                    GUI.setColour(Color.blue);
                    GUI.setLineWidth(2);
                    GUI.drawStreet(i);
                    break;
                case "highway-pedestrian":
                case "highway-footway":
                    GUI.setColour(Color.MAGENTA);
                    GUI.setLineWidth(1);
                    GUI.drawStreet(i);
                    break;

               /*default:
                    if(i.getType().contains("highway-")){
                        GUI.setColour(Color.darkGray);
                        GUI.setLineWidth(1);
                        GUI.drawStreet(i);

                    }*/
            }

            /*for(Edge e : streets.get("Singerstraße").getEdges()){
                GUI.setColour(Color.cyan);
                GUI.setLineWidth(3);
                GUI.drawEdge(e);
            }*/

        }
//        ArrayList<Vertex> toRemove = new ArrayList<>();
////
//        System.out.println("Initial size: " + OSM.getVertex().size());
//
//       for(Vertex v : OSM.getVertex().values()){
//           if(!matrix.addConnection(v, streets)){
//               toRemove.add(v);
//           }
//
//       }
////
////        System.out.println("MATRIX: " + matrix.getSize());
//
//        for(Vertex v : toRemove){
//            OSM.getVertex().remove(v.getId());
//        }
//
//        System.out.println("Final size: " + OSM.getVertex().size());

        //Vertex v = OSM.getVertex().get("2330619457");

        //System.out.println("COSTS: " + matrix.getDistance(OSM.getVertex().get("2330619457"), OSM.getVertex().get("17322928")));
        matrix.printConnectionById(osm.getVertex().get("33182908"));
        GUI.drawNode(OSM.getVertex().get("2330619457"));
        GUI.drawNode(OSM.getVertex().get("17322928"));
        GUI.show();

        //System.out.println("Total Run Time: " + (LocalTime.now().getSecond() - start.getSecond()));
    }

}
