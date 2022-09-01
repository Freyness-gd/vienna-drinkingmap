import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import codedraw.*;
import java.time.LocalTime;
public class main {

    public static OSM osm = OSM.getInstance("data/map.osm");
    public static Map<String, Street> streets = new HashMap<>();
    public static int windowrange = 1000;
    public static GUI gui = GUI.getInstance(windowrange, osm.getBounds());
    public static OSMParser parser = OSMParser.getInstance("data/map.osm");

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        LocalTime start = LocalTime.now();

        streets.putAll(parser.getStreets(osm.getVertex()));
        System.out.println("NR STREETS: " + streets.size());

//        for(Street i : streets){
//            for(Edge e : i.getEdges()){
//                GUI.drawNode(e.getV1());
//                GUI.drawNode(e.getV2());
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

//                default:
//                    if(i.getType().contains("highway-")){
//                        GUI.setColour(Color.darkGray);
//                        GUI.setLineWidth(1);
//                        GUI.drawStreet(i);
//
//                    }
            }

            for(Edge e : streets.get("Franz-Josefs-Kai").getEdges()){
                GUI.setColour(Color.cyan);
                GUI.setLineWidth(3);
                GUI.drawEdge(e);
            }

        }

        GUI.show();

        //System.out.println("FINISHED DRAWING");

        //System.out.println("Total Run Time: " + (LocalTime.now().getSecond() - start.getSecond()));
    }

}
