import java.util.ArrayList;
import java.util.HashMap;

public class graph_test {

    public static HashMap<Character, ArrayList<Relation>> matrix = new HashMap<>();

    public static void main(String[] args) {

        Relation ab = new Relation('A', 'B', 4);
        Relation ac = new Relation('A', 'C', 2);
        Relation ag = new Relation('A', 'G', 7);
        Relation cf = new Relation('C', 'F', 8);
        Relation cg = new Relation('C', 'G', 3);
        Relation bd = new Relation('B', 'D', 2);
        Relation dg = new Relation('D', 'G', 5);
        Relation dh = new Relation('D', 'H', 6);
        Relation gj = new Relation('G', 'J', 4);
        Relation fj = new Relation('F', 'J', 3);
        Relation hj = new Relation('H', 'J', 2);

        GFG minHeap = new GFG(15);

        ArrayList<Relation> a = new ArrayList<>();
        ArrayList<Relation> b = new ArrayList<>();
        ArrayList<Relation> c = new ArrayList<>();
        ArrayList<Relation> d = new ArrayList<>();
        ArrayList<Relation> f = new ArrayList<>();
        ArrayList<Relation> g = new ArrayList<>();
        ArrayList<Relation> h = new ArrayList<>();

        a.add(ab);
        a.add(ac);
        a.add(ag);
        b.add(bd);
        c.add(cg);
        c.add(cf);
        d.add(dg);
        d.add(dh);
        g.add(gj);
        f.add(fj);
        h.add(hj);

        matrix.put('A', a);
        matrix.put('B', b);
        matrix.put('C', c);
        matrix.put('D', d);
        matrix.put('F', f);
        matrix.put('H', h);

        System.out.println(matrix);

    }

}

