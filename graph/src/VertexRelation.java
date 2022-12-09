public class VertexRelation {

    private final Vertex start, end;
    private final double costs;

    public VertexRelation(Vertex start, Vertex end){
        this.start = start;
        this.end = end;
        this.costs = start.getDistance(end);
    }

    public double getCosts(){ return this.costs; }
    public Vertex getEnd() { return this.end; }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder("Relation ");
        sb.append("with ").append(end.getId()).append(" costs: ").append(this.costs).append("\n");

        return sb.toString();
    }

}
