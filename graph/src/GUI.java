import codedraw.CodeDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUI{

    private static GUI gui;
    private static CodeDraw cd;
    private static int windowrange = 0;
    private static Map<String, Double> bounds = new HashMap<>();


    private GUI(int windowrange, Map<String, Double> bounds){
        cd = new CodeDraw(windowrange, windowrange);
        GUI.windowrange = windowrange;
        GUI.bounds.putAll(bounds);
        cd.setColor(Color.BLACK);
        cd.setLineWidth(3);
    }

    public static synchronized GUI getInstance(int windowrange, Map<String, Double> bounds){
        if(gui == null) gui = new GUI(windowrange, bounds);
        return gui;
    }

    public static void drawEdge(Edge e){
        double startX = mapLon(e.gethead().getLon());
        double startY = mapLat(e.gethead().getLat());
        double endX = mapLon(e.gettail().getLon());
        double endY = mapLat(e.gettail().getLat());

        cd.drawLine(startX, windowrange - startY, endX, windowrange - endY);
    }

    public static void drawEdge(ArrayList<Edge> e){
        for(Edge i : e) drawEdge(i);
    }

    public static void drawNode(Vertex v){
        cd.setColor(Color.BLACK);
        cd.fillCircle(mapLon(v.getLon()), windowrange-mapLat(v.getLat()), 3);
    }

    public static void setColour(Color c){
        cd.setColor(c);
    }

    public static void setLineWidth(int w){
        cd.setLineWidth(w);
    }

    public static void drawNode(Map<String, Vertex> v){
        Collection<Vertex> c = v.values();

        for(Vertex i : c){
            cd.fillCircle(mapLon(i.getLon()), mapLat(i.getLat()), 3);
        }

    }

    public static void drawStreet(Street s){
        if(s != null)  drawEdge(s.getEdges());
    }

    public static int mapLon(double val){
        return (int) (((val-bounds.get("MinLon"))*windowrange )
                /(bounds.get("MaxLon")-bounds.get("MinLon")));
    }
    public static int mapLat(double val) {
        return (int) (((val-bounds.get("MinLat"))*windowrange)
                /(bounds.get("MaxLat")-bounds.get("MinLat")));
    }

    public static void show(){ cd.show(); }
}
