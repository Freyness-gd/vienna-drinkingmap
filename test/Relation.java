public class Relation {

    public Character end;
    public int costs;


    public Relation(Character start, Character end, int costs) {

        this.end = end;
        this.costs = costs;
    }

    @Override
    public String toString() {
        String a = "Relation: "  + end + " with cost: " + costs;

        return a;
    }


    public boolean equals(Relation obj) {

        return this.end.equals(obj.end) && this.costs == obj.costs;
    }
}
