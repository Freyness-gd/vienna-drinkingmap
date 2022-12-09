import java.util.ArrayList;
import java.util.HashMap;

public class Dij {

    private Character source;
    private HashMap<Character, ArrayList<Relation>> hashMap;
    private HashMap<Character, Integer> array;
    private GFG gfg;

    public Dij(Character s, HashMap<Character, ArrayList<Relation>> h, int size){
        this.source = s;

        if(h.size() != 0) hashMap.putAll(h);
        array = new HashMap<>(size);
        assert hashMap != null;
        for(Character c : hashMap.keySet()){
            if(c.equals(source)) array.put(source, 0);
            array.put(c, Integer.MAX_VALUE);
        }
        this.gfg = new GFG(++size);
        gfg.insert(new Relation('A', 'A', 0));
    }

    public HashMap<Character, Integer> getShortestPath(){

        while(gfg.size() != 0){
            Relation u = gfg.remove();

        }

        return array;
    }



}
