import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Dijkstra {

    private Map<Vertex, Double> costMap = new HashMap<>();
    private final Vertex source;
    private AdjMatrix graph;

    public Dijkstra(Vertex source, Map<String, Vertex> map, AdjMatrix graph){
        this.source = source;
        this.createMap(map);
        this.graph = graph;
    }

    private void createMap(Map<String, Vertex> map){

        costMap.put(source, 0.0);

        for(Vertex v : map.values()){
            costMap.put(v, Double.POSITIVE_INFINITY);
        }
    }

}
