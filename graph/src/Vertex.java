public record Vertex(String id, double lon, double lat) implements Comparable<Vertex> {

    // Haversine formula
    public double getDistance(Vertex v) {

        double R = 6378.137; // Earth Radius in KM
        double dLat = v.lat() * Math.PI / 180 - this.lat() * Math.PI / 180;
        double dLon = v.lon() * Math.PI / 180 - this.lon() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(this.lat() * Math.PI / 180) * Math.cos(v.lat() * Math.PI / 180) *
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
