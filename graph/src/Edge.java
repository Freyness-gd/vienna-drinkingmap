import codedraw.CodeDraw;

import static java.lang.Math.*;

public class Edge implements Way {

    private final String streetName;
    private final Vertex v1, v2;

    public Edge(Vertex v1, Vertex v2){

        this.streetName = "";
        this.v1 = v1;
        this.v2 = v2;

    }

    public Vertex getV1(){ return v1; }
    public Vertex getV2(){ return v2; }

    @Override
    public double getLength(){
        return sqrt((pow(v2.getLat() - v1.getLat(), 2)) + pow(v2.getLon() - v1.getLon(), 2));
    }

    @Override
    public String getName() {
        return streetName;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("EDGE OF STREET ").append(this.streetName).append("\n");
        s.append("WITH THE NODES: \n");
        s.append(this.v1).append("\n");
        s.append(this.v2).append("\n");

        return s.toString();
    }



}
