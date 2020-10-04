
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
    	myRandom = new Random();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    }
    
    public void printTraining(){
    	System.out.println(myText);
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int start = 0;
        while(myText.indexOf(key, start) != -1 ){
            int ind = myText.indexOf(key, start);
            if(ind >= myText.length() - 1) break;
            ans.add(myText.substring(ind+key.length(), ind+key.length()+1));
            start = ind + 1;
        }
        return ans;
    }
    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length() - 1);
    	String key = myText.substring(index, index+1);
    	sb.append(key);
    	for(int k=0; k < numChars - 1; k++){
    	        ArrayList<String> follows = getFollows(key);
    	        if(follows.size() == 0) break;
    	        index = myRandom.nextInt(follows.size());
    	        String next = follows.get(index);
    	        sb.append(next);
    	        key = next;
    	}
    	
    	return sb.toString();
    }
    
   
        
}
