
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    
    public int[] countWordLengths(FileResource fr, int[] counts){
        for(String s : fr.words()){
            if(Character.isLetter(s.charAt(0))){
                int len = s.length();
                if(!Character.isLetter(s.charAt(len-1))){
                    len --;
                }
                if(len > counts.length){
                    counts[counts.length - 1]++;
                } else {
                    counts[len] ++ ;
                }
            }
	}
        return counts;
    }
    
    public void testCountWordLengths(){
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        counts = countWordLengths(fr, counts);
        for(int i = 0; i < counts.length; i++){
            System.out.println("Words of length " + i + " : " + counts[i]);
        }
        System.out.println("The most common word of length is " + IndexOfMax(counts));
    }
    
    public int IndexOfMax(int[] values){
        int max = 0;
        int index = -1;
        for(int i = 0; i < values.length; i++){
            if(values[i] > max){
                max = values[i];
                index = i;
            }
        }
        return index;
    }
}
