import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public class main {

    public static OSM osm = OSM.getInstance();
    public static OSMParser parser = OSMParser.getInstance("data/map.osm");

    public static void main(String[] args) {

        try{
            parser.getBounds(osm);
        } catch (XMLStreamException e) { e.printStackTrace(); }

    }

}
