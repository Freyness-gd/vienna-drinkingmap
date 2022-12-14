import javax.xml.stream.XMLStreamException;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.time.LocalTime;
import java.util.List;

// TODO: add annotations
// TODO: document code functionality

public class main {
    public static String path = "data/map.osm";
    public static int windowrange = 1000;
    public static OSM osm = OSM.getInstance(path);
    public static GUI gui = GUI.getInstance(windowrange, OSM.getBounds());
    public static AdjMatrix matrix = new AdjMatrix(path);
    //public static OSM osm = OSM.getInstance("data/map.osm");

    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {

        LocalTime start = LocalTime.now();

        for (Street i : osm.map().values()) {
            switch (i.getType()) {
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


        }
        ArrayList<Vertex> toRemove = new ArrayList<>();

        System.out.println("Initial size: " + OSM.getMap().size());

        for (Vertex v : OSM.getMap().values()) {
            if (!matrix.addConnection(v, osm.map())) {
                toRemove.add(v);
            }

        }

        System.out.println("MATRIX: " + matrix.getSize());

        for (Vertex v : toRemove) {
            OSM.getMap().remove(v.id());
        }

        System.out.println("Final size: " + OSM.getMap().size());


        Vertex source = OSM.getVertex("33196433");
        //System.out.println(matrix.getConnection(source));

//        VertexRelation vr1 = new VertexRelation(OSM.getVertex("33196433"), OSM.getVertex("1950497176"));
//        VertexRelation vr2 = new VertexRelation(OSM.getVertex("1950497176"), OSM.getVertex("33196433") );
//        VertexRelation vr3 = new VertexRelation( OSM.getVertex("60571042"), OSM.getVertex("33196433"));
//
//        System.out.println("Is the same: " + vr1.equals(vr1));
//        System.out.println("Is the same: " + vr1.equals(vr2));
//        System.out.println("Is the same: " + vr1.equals(vr3));

        Dijkstra alg = new Dijkstra(source, OSM.getMap(), matrix);


        //System.out.println("COSTS: " + matrix.getDistance(OSM.getVertex().get("2330619457"), OSM.getVertex().get("17322928")));
//        matrix.printConnectionById(OSM.getVertex("1068425372"));
//        GUI.drawNode(OSM.getVertex("1068425372"), Color.ORANGE, 4);
//        for(VertexRelation i : matrix.getConnection(OSM.getVertex("1068425372"))){
//            GUI.drawVertexRelation(i);
//        }
        //GUI.drawNode(OSM.getVertex().get("2330619457"));
        //GUI.drawNode(OSM.getVertex().get("17322928"));

//        for(Edge e : streets.get("Michaelerplatz").getEdges()){
//            GUI.setColour(Color.cyan);
//            GUI.setLineWidth(3);
//            GUI.drawEdge(e);
//        }

        GUI.drawNode(source, Color.red, 5);




//        for(VertexRelation v : alg.discovered()){
//            GUI.drawVertexRelation(v);
//        }

        List<Vertex> pathTo = alg.getPath(OSM.getVertex("33182887"));

        for (Vertex v : pathTo) {
            GUI.drawNode(v);
        }

        GUI.show();


        //System.out.println("Total Run Time: " + (LocalTime.now().getSecond() - start.getSecond()));
    }

}
