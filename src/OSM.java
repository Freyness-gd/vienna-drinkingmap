import javax.xml.stream.XMLStreamException;
import java.util.HashMap;
import java.util.Map;

public class OSM {

    private static OSM osm;
    private static String path = "";
    private static double minlon, maxlon, minlat, maxlat;
    private static OSMParser parser;

    private static Map<String, Vertex> vertex = new HashMap<>();

    private OSM(String path){
        OSM.path = path;
        parser = OSMParser.getInstance(path);

        try{
            OSMParser.getBounds(this);
            OSMParser.getVertices(vertex);
        } catch (XMLStreamException e) { e.printStackTrace(); }

    };

    public static OSM getInstance(String path){
        if(osm == null) osm = new OSM(path);
        return osm;
    }

    public void setConstraints(double minlon, double maxlon, double minlat, double maxlat){
        OSM.minlon = minlon;
        OSM.maxlon = maxlon;
        OSM.minlat = minlat;
        OSM.maxlat = maxlat;

//        System.out.println("Constraints set to: \n --------------------\n");
//        System.out.println("Min Lon: " + minlon);
//        System.out.println("Max Lon: " + maxlon);
//        System.out.println("Min Lat: " + minlat);
//        System.out.println("Max Lat: " + maxlat +"\n");
    }

    public static void test(){
        System.out.println(parser);
    }

    //Getters
    public static double getMinLon(){return minlon;}
    public static double getMaxLon(){return maxlon;}
    public static double getMinLat(){return minlat;}
    public static double getMaxLat(){return maxlat;}
    public static double getLonRange(){ return maxlon - minlon; }
    public static double getLatRange(){ return maxlat - minlat; }
    public static Map<String, Vertex> getVertex(){ return vertex; }

    //minlon, maxlon, minlat, maxlat
    public static Map<String, Double> getBounds() {
        Map<String, Double> bounds = new HashMap<>();
        bounds.put("MinLon", minlon);
        bounds.put("MaxLon", maxlon);
        bounds.put("MinLat", minlat);
        bounds.put("MaxLat", maxlat);

        return bounds;
    }
}
