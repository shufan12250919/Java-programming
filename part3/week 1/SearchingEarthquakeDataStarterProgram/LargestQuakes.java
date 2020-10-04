
/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class LargestQuakes {
    
    public void findLargestQuake(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /* for(QuakeEntry eq : list){
            System.out.println(eq);
        } */
        //System.out.println(indexOfLargest(list));
        System.out.println("There are " + list.size() + " earthquakes!");
        int howMany = 50;
        System.out.println("The " + howMany + " largest earthquakes!");
        ArrayList<QuakeEntry> largests =  getLargest(list, howMany);
        for(QuakeEntry eq:largests){
            System.out.println(eq);
        }
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> list){
        int index = 0;
        double max = 0;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getMagnitude() > max){
                index = i;
                max = list.get(i).getMagnitude();
            }
        }     
        return index;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> list, int howMany){
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(list);
        for(int j=0; j < howMany; j++) {
            int index = indexOfLargest(copy);
            ret.add(copy.get(index));
            copy.remove(index);
        }
        return ret;
    }
}
