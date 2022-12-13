import java.util.*;

public class Dijkstra {

    private final Map<Vertex, CostNode> costMap = new HashMap<>();
    private final Vertex source;
    private final AdjMatrix graph;

    private final List<VertexRelation> discovered = new ArrayList<>();

    public Dijkstra(Vertex source, Map<String, Vertex> map, AdjMatrix graph){
        this.source = source;
        this.createMap(map);
        this.graph = graph;
        this.calculateCosts();
    }

    private void createMap(Map<String, Vertex> map){

        for(Vertex v : map.values()){
            costMap.put(v, new CostNode(null, Double.POSITIVE_INFINITY));
        }

        costMap.put(source, new CostNode(source, 0.0));
    }

    private void calculateCosts(){

        Queue<VertexRelation> queue = new PriorityQueue<VertexRelation>();
        queue.addAll(graph.getConnection(source));
        //discovered.add(source);

        int iterations = 0;

        while(!queue.isEmpty() && iterations < 50){



            VertexRelation rel = queue.poll();
            Vertex v = rel.getEnd();



            if(!discovered.contains(rel) ){
                System.out.println("Iteration: "  + iterations  + "\n---------------------");
                System.out.println("Relation: " + rel + " START: " + rel.getStart().getId() + "\t END: " + v.getId() + " \n");

                discovered.add(rel);
                Double costTillVertex = costMap.get(rel.getStart()).costs();

                System.out.println("costTillVertex = " + costTillVertex + " \n");

                costMap.put(v, new CostNode(rel.getStart(), rel.getCosts() + costTillVertex));
                queue.addAll(graph.getConnection(v));
                iterations++;
            }
        }

    }

//    private void calculateCosts(){
//        Queue<Vertex> queue = new PriorityQueue<>();
//        queue.add(source);
//        discovered.add(source);
//
//        int iterations = 0;
//
//        while(!queue.isEmpty() && iterations < 100){
//
//            for(VertexRelation i : graph.getConnection(queue.poll())){
//
//                Vertex v = i.getEnd();
//                //System.out.println("NODE: " + v);
//                if(!discovered.contains(v)){
//                    discovered.add(v);
//                    queue.add(v);
//                    costMap.replace(v, this.graph.getDistance(i.getStart(), v));
//                    iterations++;
//                }
//            }
//        }

//        System.out.println("iterations = " + iterations);
//
//    }

    public List<VertexRelation> discovered() { return this.discovered; }

    class CostNode{

        private final Vertex over;
        private final Double costs;

        public CostNode(Vertex over, Double costs){
            this.over = over;
            this.costs = costs;
        }

        public Vertex over(){ return this.over; }
        public Double costs(){ return this.costs; }

    }

}
