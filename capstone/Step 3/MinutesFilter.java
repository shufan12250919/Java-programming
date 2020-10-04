
/**
 * Write a description of MinutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int min;
    private int max;
	
    public MinutesFilter(int least, int more) {
    	min = least;
    	max = more;
    }
    
    @Override
    public boolean satisfies(String id) {
        int duration = MovieDatabase.getMinutes(id);
        return duration >= min && duration <= max;
    }
}
