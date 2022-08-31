import codedraw.CodeDraw;

import java.util.ArrayList;

public class Street implements Way{

    private ArrayList<Edge> edges = new ArrayList<>();
    private double length = 0.0;
    private final String id, name, type;

    public Street(String id, String name, String type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Street(String id, String name,String type, ArrayList<Edge> e){
        this.id = id;
        this.name = name;
        this.type = type;
        for(Edge i : e) this.addEdge(i);
    }

    public void addEdge(Edge e){
        edges.add(e);
        length += e.getLength();
    }

    public ArrayList<Edge> getEdges(){ return this.edges; }

    public String getType(){ return this.type; }
    public String getId(){ return this.id; }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder("STREET - " + name + " - " + id);
        b.append("\n");
        b.append(edges);
        return b.toString();
    }




}
