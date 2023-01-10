import java.util.ArrayList;
import java.util.HashMap;

public class AdjMatrix {

    private final HashMap<Vertex, ArrayList<Edge>> matrix = new HashMap<>();
    private final OSM osm;

    public AdjMatrix(String path) {
        this.osm = OSM.getInstance(path);
    }

    public boolean addConnection(Vertex vertex, HashMap<String, Street> streets) {

        ArrayList<Edge> vertexMatrix = new ArrayList<>();
        boolean inStreet = false;

        for (Street s : streets.values()) {
            for (Edge e : s.getEdges()) {
                if (e.getStart().equals(vertex)) {
                    vertexMatrix.add(new Edge(e.getStart(), e.getEnd()));
                    inStreet = true;
                } else if (e.getEnd().equals(vertex)) {
                    vertexMatrix.add(new Edge(e.getEnd(), e.getStart()));
                    inStreet = true;
                }
            }
        }

        if (inStreet) {
            matrix.put(vertex, vertexMatrix);
        }

        return inStreet;
    }

    public double getDistance(Vertex start, Vertex end) {

        for (Edge v : matrix.get(start)) {
            if (v.getEnd().equals(end)) {
                return v.getLength();
            }
        }

        return -0.1;
    }

    public int getSize() {
        return matrix.size();
    }

    public void print() {
        for (Vertex v : matrix.keySet()) {
            if (matrix.get(v).size() > 3) {
                System.out.println(matrix.get(v));
            }
        }
    }

    public void printConnectionById(Vertex v) {
        System.out.println(matrix.get(v));
    }

    public ArrayList<Edge> getConnection(Vertex v) {
        return matrix.get(v);
    }

}


