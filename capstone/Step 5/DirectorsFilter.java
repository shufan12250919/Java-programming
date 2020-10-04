
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String[] director;
	
    public DirectorsFilter(String name) {
    	director = name.split(",");
    	
    }
    
    @Override
    public boolean satisfies(String id) {
        for(String d : director){
            if(MovieDatabase.getDirector(id).contains(d)){
                return true;
            }
        }
        return false;
    }
}
