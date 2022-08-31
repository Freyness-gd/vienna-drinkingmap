import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import codedraw.*;

public class main {

    public static OSM osm = OSM.getInstance("data/map.osm");
    public static Map<String, Street> streets = new HashMap<>();
    public static int windowrange = 1000;
    public static GUI gui = GUI.getInstance(windowrange, osm.getBounds());
    public static OSMParser parser = OSMParser.getInstance("data/map.osm");

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        ArrayList<Street> streets = parser.getStreets(osm.getVertex());
        System.out.println("NR STREETS: " + streets.size());

//        for(Street i : streets){
//            for(Edge e : i.getEdges()){
//                GUI.drawNode(e.getV1());
//                GUI.drawNode(e.getV2());
//            }
//        }

        for(Street i : streets){
            switch(i.getType()){
                case "highway-primary":
                    GUI.setColour(Color.red);
                    GUI.setLineWidth(7);
                    GUI.drawStreet(i);
                    break;
                case "highway-secondary":
                    GUI.setColour(Color.orange);
                    GUI.setLineWidth(5);
                    GUI.drawStreet(i);
                    break;
                case "highway-tertiary":
                    GUI.setColour(Color.GREEN);
                    GUI.setLineWidth(5);
                    GUI.drawStreet(i);
                    break;
                case "highway-residential":
                    GUI.setColour(Color.blue);
                    GUI.setLineWidth(3);
                    GUI.drawStreet(i);
                    break;
                case "highway-pedestrian":
                case "highway-footway":
                    GUI.setColour(Color.MAGENTA);
                    GUI.setLineWidth(3);
                    GUI.drawStreet(i);
                    break;

                default:
                    if(i.getType().contains("highway-")){
                        GUI.setColour(Color.darkGray);
                        GUI.setLineWidth(3);
                        GUI.drawStreet(i);

                    }
            }
            GUI.show();
        }

        System.out.println("FINISHED DRAWING");
    }

}
