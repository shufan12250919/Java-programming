
/**
 * Write a description of MarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class MarkovModel extends AbstractMarkovModel{

    private int number;
    
    public MarkovModel(int num) {
    	myRandom = new Random();
    	number = num;
    	order = num;
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    

    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length() - number);
    	String key = myText.substring(index, index+number);
    	sb.append(key);
    	for(int k=0; k < numChars - number; k++){
    	        ArrayList<String> follows = getFollows(key);
    	        if(follows.size() == 0) break;
    	        index = myRandom.nextInt(follows.size());
    	        String next = follows.get(index);
    	        sb.append(next);
    	        key = key.substring(1) + next;
    	}
    	
    	return sb.toString();
    }
}
