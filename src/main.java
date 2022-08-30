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

    public static void main(String[] args) {


        /*
        try{
            parser.getBounds(osm);

            Vertex v1 = new Vertex("1", 0.0, 0.0);
            Vertex v2 = new Vertex("2", 5.0, 0.0);

            Vertex v3 = new Vertex("3", 7.0, 3.0);
            Vertex v4 = new Vertex("4", 10, 5.0);

            Edge edge = new Edge("test", v1, v2);
            Edge edge_s = new Edge("test", v3, v4);

            System.out.println(edge.getLength());
            parser.getVertices(vertex);

            Street street_test = parser.getWayByID("172434862", vertex);

            cd.setColor(Color.BLACK);


            cd.setLineWidth(3);
            for(Edge i : street_test.getEdges()){

                double startX = mapLon(i.getV1().getLon());
                double startY = mapLat(i.getV1().getLat());
                double endX = mapLon(i.getV2().getLon());
                double endY = mapLat(i.getV2().getLat());


                cd.drawLine(startX, cd.getWidth() - startY, endX, cd.getWidth() - endY);
            }

            for(Edge i : street_test.getEdges()){

                double f_x = mapLon(i.getV1().getLon());
                double f_y = mapLat(i.getV1().getLat());
                double s_x = mapLon(i.getV2().getLon());
                double s_y = mapLat(i.getV2().getLat());

                cd.fillCircle(f_x, cd.getWidth() - f_y, 2);
                cd.fillCircle(s_x, cd.getWidth() - s_y, 2);

            }

            cd.show();


        } catch (XMLStreamException e) { e.printStackTrace(); }*/

        //streets.putAll(parser.getStreets(osm.getVertex()));

        for(Street s : parser.getStreets(osm.getVertex())){
            GUI.drawStreet(s);
        }



        //GUI.drawStreet(parser.getWayByID("203868701", osm.getVertex()));

        GUI.show();

    }



}
