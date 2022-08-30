public class Vertex {

    private final String id;
    private final double lon;
    private final double lat;

    public Vertex (String id, double lon, double lat){
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public double getLat() { return lat; }
    public double getLon() { return lon; }
    public String getId() { return id; }

    @Override
    public String toString(){
        return "NODE " + id + " " + lon + " " + lat;
    }

}
