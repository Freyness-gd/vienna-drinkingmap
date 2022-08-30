import javax.xml.stream.XMLStreamException;
import java.util.HashMap;
import java.util.Map;

public class OSM {

    private static OSM osm;
    private String path = "";
    private double minlon, maxlon, minlat, maxlat;
    private OSMParser parser;

    private Map<String, Vertex> vertex = new HashMap<>();

    private OSM(String path){
        this.path = path;
        parser = OSMParser.getInstance(path);

        try{
            parser.getBounds(this);
            parser.getVertices(vertex);
        } catch (XMLStreamException e) { e.printStackTrace(); }

    };

    public static OSM getInstance(String path){
        if(osm == null) osm = new OSM(path);
        return osm;
    }

    public void setConstraints(double minlon, double maxlon, double minlat, double maxlat){
        this.minlon = minlon;
        this.maxlon = maxlon;
        this.minlat = minlat;
        this.maxlat = maxlat;

        System.out.println("Constraints set to: \n --------------------\n");
        System.out.println("Min Lon: " + minlon);
        System.out.println("Max Lon: " + maxlon);
        System.out.println("Min Lat: " + minlat);
        System.out.println("Max Lat: " + maxlat +"\n");
    }

    public void test(){
        System.out.println(parser);
    }

    //Getters
    public double getMinLon(){return minlon;}
    public double getMaxLon(){return maxlon;}
    public double getMinLat(){return minlat;}
    public double getMaxLat(){return maxlat;}
    public double getLonRange(){ return maxlon - minlon; }
    public double getLatRange(){ return maxlat - minlat; }
    public Map<String, Vertex> getVertex(){ return vertex; }

    //minlon, maxlon, minlat, maxlat
    public Map<String, Double> getBounds() {
        Map<String, Double> bounds = new HashMap<>();
        bounds.put("MinLon", minlon);
        bounds.put("MaxLon", maxlon);
        bounds.put("MinLat", minlat);
        bounds.put("MaxLat", maxlat);

        return bounds;
    }
}
