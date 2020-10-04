
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;

public class Tester {
    
    public void testGetFollows(){
        String s = "this is a test yes this is a test.";
        MarkovOne one = new MarkovOne();
        one.setTraining(s);
        //one.printTraining();
        ArrayList<String> list = one.getFollows("es");
        for(String word : list){
            System.out.println(word + " ");
        }
        System.out.println("end of list");
    }
    
    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	MarkovOne markov = new MarkovOne();
	markov.setTraining(st);
	ArrayList<String> list = markov.getFollows("he");
	System.out.println("There are " + list.size() + " elements in the follow array.");
    }
}
