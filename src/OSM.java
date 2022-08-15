public class OSM {

    private static OSM osm;
    private double minlon, maxlon, minlat, maxlat;

    private OSM(){};

    public static OSM getInstance(){
        if(osm == null) osm = new OSM();
        return osm;
    }

    public void setConstraints(double minlon, double maxlon, double minlat, double maxlat){
        this.minlon = minlon;
        this.maxlon = maxlon;
        this.minlat = minlat;
        this.maxlat = maxlat;

        System.out.println("Constraints set to: \n --------------------\n");
        System.out.println("Min Lon: " + minlon);
        System.out.println("Max Lon: " + maxlon);
        System.out.println("Min Lat: " + minlat);
        System.out.println("Max Lat: " + maxlat +"\n");
    }

    //Getters
    public double getMinLon(){return minlon;}
    public double getMaxLon(){return maxlon;}
    public double getMinLat(){return minlat;}
    public double getMaxLat(){return maxlat;}

}
