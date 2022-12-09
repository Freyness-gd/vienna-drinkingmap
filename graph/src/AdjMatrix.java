import java.util.ArrayList;
import java.util.HashMap;

public class AdjMatrix {

    private HashMap<Vertex, ArrayList<VertexRelation>> matrix = new HashMap<>();
    private OSM osm;

    public AdjMatrix(OSM osm){
        this.osm = osm;
    }

    public boolean addConnection(Vertex vertex, HashMap<String,Street> streets){

        ArrayList<VertexRelation> vertexMatrix = new ArrayList<>();
        boolean inStreet = false;

        for(Street s : streets.values()){
            for(Edge e : s.getEdges()){
                if(e.gethead().equals(vertex)){
                    vertexMatrix.add(new VertexRelation(e.gethead(), e.gettail()));
                    inStreet = true;
                } else if(e.gettail().equals(vertex)){
                    vertexMatrix.add(new VertexRelation(e.gettail(), e.gethead()));
                    inStreet = true;
                }
            }
        }

        if(inStreet){
            matrix.put(vertex, vertexMatrix);
        }

        return inStreet;
    }

    public double getDistance(Vertex start, Vertex end){

        for(VertexRelation v : matrix.get(start)){
            if(v.getEnd().equals(end)){
                return v.getCosts();
            }
        }

        return -0.1;
    }

    public int getSize() { return matrix.size(); }

    public void print() {
        for(Vertex v : matrix.keySet()){
            if(matrix.get(v).size() > 3){
                System.out.println(matrix.get(v));
            }
        }
    }

    public void printConnectionById(Vertex v){
        System.out.println(matrix.get(v));
    }

}


