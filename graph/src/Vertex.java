import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Vertex implements Comparable<Vertex> {

    private final String id;
    private final double lon;
    private final double lat;

    public Vertex(String id, double lon, double lat) {
        this.id = id;
        this.lon = lon;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getId() {
        return id;
    }

    // Haversine formula
    public double getDistance(Vertex v) {

        double R = 6378.137; // Earth Radius in KM
        double dLat = v.getLat() * Math.PI / 180 - this.getLat() * Math.PI / 180;
        double dLon = v.getLon() * Math.PI / 180 - this.getLon() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(this.getLat() * Math.PI / 180) * Math.cos(v.getLat() * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d * 1000;
    }

    @Override
    public String toString() {
        return "NODE " + id + " " + lon + " " + lat;
    }

    public boolean equals(Vertex v) {
        return this.id.equals(v.id);
    }

    @Override
    public int compareTo(Vertex o) {
        return (int) this.getDistance(o);
    }
}
