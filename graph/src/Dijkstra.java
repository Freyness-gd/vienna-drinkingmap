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

        Queue<VertexRelation> queue = new PriorityQueue<>();
        queue.addAll(graph.getConnection(source));
        //discovered.add(source);

        int iterations = 0;

        while(!queue.isEmpty()){

            VertexRelation rel = queue.poll();
            Vertex v = rel.getEnd();

            if(!discovered.contains(rel) ){
                //System.out.println("Iteration: "  + iterations  + "\n---------------------");
                //System.out.println("Relation: " + rel + " START: " + rel.getStart().getId() + "\t END: " + v.getId() + " \n");

                discovered.add(rel);
                Double costTillVertex = costMap.get(rel.getStart()).costs();

                //System.out.println("costTillVertex = " + costTillVertex + " \n");

                CostNode c = new CostNode(rel.getStart(), rel.getCosts() + costTillVertex);
                //System.out.println("c.costs() = " + c.costs());

                if(costMap.get(v).costs() < c.costs){
                    continue;
                } else{
                    costMap.put(v, c);
                }

                queue.addAll(graph.getConnection(v));

//                for(VertexRelation r : graph.getConnection(v)){
//                    if(!discovered.contains(r)) queue.add(r);
//                }
                iterations++;
            }

        }

    }

    public List<Vertex> getPath(Vertex v){

        List<Vertex> path = new LinkedList<>();

        if(costMap.get(v).costs() == Double.POSITIVE_INFINITY){
            System.out.println("NO PATH TO THIS POINT!");
            return path;
        }

        path.add(v);
        CostNode c = costMap.get(v);
        System.out.println("COSTS TO NODE:" + c.costs());

        while(!c.over().equals(source)){
            path.add(c.over());
            c = costMap.get(c.over());
        }

        return path;
    }

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
