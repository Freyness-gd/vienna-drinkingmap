import codedraw.CodeDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GUI {

    private static GUI gui;
    private static CodeDraw cd;
    private static int windowrange = 0;
    private static Map<String, Double> bounds = new HashMap<>();


    private GUI(int windowrange, Map<String, Double> bounds) {
        cd = new CodeDraw(windowrange, windowrange);
        GUI.windowrange = windowrange;
        GUI.bounds.putAll(bounds);
        cd.setColor(Color.BLACK);
        cd.setLineWidth(3);
    }

    public static synchronized GUI getInstance(int windowrange, Map<String, Double> bounds) {
        if (gui == null) gui = new GUI(windowrange, bounds);
        return gui;
    }

    public static void drawEdge(Edge e) {
        double startX = mapLon(e.getStart().lon());
        double startY = mapLat(e.getStart().lat());
        double endX = mapLon(e.getEnd().lon());
        double endY = mapLat(e.getEnd().lat());

        cd.drawLine(startX, windowrange - startY, endX, windowrange - endY);
    }

    public static void drawEdge(ArrayList<Edge> e) {
        for (Edge i : e) drawEdge(i);
    }

    public static void drawNode(Vertex v) {
        cd.setColor(Color.BLACK);
        cd.fillCircle(mapLon(v.lon()), windowrange - mapLat(v.lat()), 3);
    }

    public static void drawNode(Vertex v, Color c, double r) {
        cd.setColor(c);
        cd.fillCircle(mapLon(v.lon()), windowrange - mapLat(v.lat()), r);
    }

    public static void setColour(Color c) {
        cd.setColor(c);
    }

    public static void setLineWidth(int w) {
        cd.setLineWidth(w);
    }

    public static void drawNode(Map<String, Vertex> v) {
        Collection<Vertex> c = v.values();

        for (Vertex i : c) {
            cd.fillCircle(mapLon(i.lon()), mapLat(i.lat()), 3);
        }

    }

    public static void drawStreet(Street s) {
        if (s != null) drawEdge(s.getEdges());
    }

    public static int mapLon(double val) {
        return (int) (((val - bounds.get("MinLon")) * windowrange)
                / (bounds.get("MaxLon") - bounds.get("MinLon")));
    }

    public static int mapLat(double val) {
        return (int) (((val - bounds.get("MinLat")) * windowrange)
                / (bounds.get("MaxLat") - bounds.get("MinLat")));
    }

    public static void show() {
        cd.show();
    }
}
