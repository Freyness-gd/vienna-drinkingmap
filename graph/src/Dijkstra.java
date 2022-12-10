import java.util.*;

public class Dijkstra {

    private Map<Vertex, Double> costMap = new HashMap<>();
    private final Vertex source;
    private AdjMatrix graph;

    public Dijkstra(Vertex source, Map<String, Vertex> map, AdjMatrix graph){
        this.source = source;
        this.createMap(map);
        this.graph = graph;
        this.calculateCosts();
    }

    private void createMap(Map<String, Vertex> map){

        costMap.put(source, 0.0);

        for(Vertex v : map.values()){
            costMap.put(v, Double.POSITIVE_INFINITY);
        }
    }

    private void calculateCosts(){
        Queue<Vertex> queue = new PriorityQueue<>();
        queue.add(source);

        while(!queue.isEmpty()){
            for(VertexRelation i : graph.getConnection(queue.poll())){
                queue.add(i.getEnd());
                costMap.replace(i.getEnd(), this.graph.getDistance(i.getStart(), i.getEnd()));
            }
        }

    }

}
