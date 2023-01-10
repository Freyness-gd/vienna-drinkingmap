public class Edge implements Way, Comparable<Edge> {

    private final String streetName;
    private final Vertex start, end;
    private final Double costs;

    public Edge(Vertex start, Vertex end) {

        this.streetName = "";
        this.start = start;
        this.end = end;
        this.costs = getLength();

    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    @Override
    public double getLength() {

        double R = 6378.137; // Earth Radius in KM
        double dLat = end.lat() * Math.PI / 180 - start.lat() * Math.PI / 180;
        double dLon = end.lon() * Math.PI / 180 - start.lon() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(start.lat() * Math.PI / 180) * Math.cos(end.lat() * Math.PI / 180) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;

        return d * 1000;
    }

    @Override
    public String getName() {
        return streetName;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Relation ");
//        s.append("EDGE OF STREET ").append(this.streetName).append("\n");
//        s.append("WITH THE NODES: \n");
//        s.append(this.start).append("\n");
//        s.append(this.end).append("\n");
        s.append(start.id()).append(" with ").append(end.id()).append(" costs: ").append(this.costs).append("\n");
        return s.toString();
    }

    @Override
    public int compareTo(Edge o) {
        return this.costs.compareTo(o.costs);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Edge)) return false;

        Edge r = (Edge) o;

        if ((this.start == r.start || this.start == r.end) &&
                (this.end == r.end || this.end == r.start)) return true;

        return false;
    }


}
