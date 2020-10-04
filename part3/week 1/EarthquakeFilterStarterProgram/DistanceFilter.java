
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter{
    private double max;
    private Location from;
    public DistanceFilter(Location loc, double maxn){
        from = loc;
        max = maxn;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return from.distanceTo(qe.getLocation()) < max;

    }
    
    public String getName(){
        return "Distance";
    }
}
