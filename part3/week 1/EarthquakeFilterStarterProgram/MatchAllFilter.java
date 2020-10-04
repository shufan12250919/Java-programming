
/**
 * Write a description of MatchAllFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MatchAllFilter implements Filter{
    
    private ArrayList<Filter> list;
    
    public MatchAllFilter(){
        list = new ArrayList<Filter>();
    }
    
    public void addFilter(Filter f){
        list.add(f);
    }
    
    public boolean satisfies(QuakeEntry qe){
        for(Filter f:list){
            if(!f.satisfies(qe)) return false;
        }
        return true;
    }
    
    public String getName(){
        StringBuilder out = new StringBuilder();
        for(Filter f:list){
            out.append(f.getName());
            out.append(" ");
        }
        return out.toString();
    }
}
