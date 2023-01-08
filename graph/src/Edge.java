import codedraw.CodeDraw;

import static java.lang.Math.*;

public class Edge implements Way {

    private final String streetName;
    private final Vertex head, tail;

    public Edge(Vertex head, Vertex tail) {

        this.streetName = "";
        this.head = head;
        this.tail = tail;

    }

    public Vertex gethead() {
        return head;
    }

    public Vertex gettail() {
        return tail;
    }

    @Override
    public double getLength() {

        double R = 6378.137; // Earth Radius in KM
        double dLat = tail.getLat() * Math.PI / 180 - head.getLat() * Math.PI / 180;
        double dLon = tail.getLon() * Math.PI / 180 - head.getLon() * Math.PI / 180;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(head.getLat() * Math.PI / 180) * Math.cos(tail.getLat() * Math.PI / 180) *
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
        StringBuilder s = new StringBuilder();
        s.append("EDGE OF STREET ").append(this.streetName).append("\n");
        s.append("WITH THE NODES: \n");
        s.append(this.head).append("\n");
        s.append(this.tail).append("\n");

        return s.toString();
    }


}
