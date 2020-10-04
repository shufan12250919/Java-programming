
/**
 * Write a description of DepthFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DepthFilter implements Filter{
    private double min;
    private double max;
    public DepthFilter(double minn, double maxn){
        min = minn;
        max = maxn;
    }
    
    public boolean satisfies(QuakeEntry qe){
        return qe.getDepth() >= min && qe.getDepth() <= max;

    }
    
    public String getName(){
        return "Depth";
    }
}
