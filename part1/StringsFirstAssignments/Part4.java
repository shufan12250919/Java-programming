
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.File;

public class Part4 {
    
    public static void main(String arg[]){
        URLResource file = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for(String word: file.words()){
            String lword = word.toLowerCase();
            int mid = lword.indexOf("youtube.com");
            if(mid == -1) { continue;}
            int first = word.indexOf("\"", mid-13);
            if(first == -1) { continue;}
            int second = word.indexOf("\"", mid+1);
            if (second == -1) {continue;}
            System.out.println(word.substring(first+1, second));
            
        }
    }
}
