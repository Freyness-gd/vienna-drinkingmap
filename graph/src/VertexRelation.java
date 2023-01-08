public class VertexRelation implements Comparable<VertexRelation> {

    private final Vertex start, end;
    private final Double costs;

    public VertexRelation(Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.costs = start.getDistance(end);
    }

    public double getCosts() {
        return this.costs;
    }

    public Vertex getEnd() {
        return this.end;
    }

    public Vertex getStart() {
        return this.start;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder("Relation ");
        sb.append(start.getId()).append(" with ").append(end.getId()).append(" costs: ").append(this.costs).append("\n");

        return sb.toString();
    }

    @Override
    public int compareTo(VertexRelation o) {
        return this.costs.compareTo(o.costs);
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof VertexRelation)) return false;

        VertexRelation r = (VertexRelation) o;

        if ((this.start == r.start || this.start == r.end) &&
                (this.end == r.end || this.end == r.start)) return true;

        return false;
    }
}
