
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int order;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setTraining(String s) {
        myText = s.trim();
    }
    
    protected void printTraining(){
    	System.out.println(myText);
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    @Override
    public String toString(){
    	return "MarkovModel of order " + order;
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> ans = new ArrayList<String>();
        int start = 0;
        while(myText.indexOf(key, start) != -1 ){
            int ind = myText.indexOf(key, start);
            if(ind >= myText.length() - key.length()) break;
            ans.add(myText.substring(ind+key.length(), ind+key.length()+1));
            start = ind + 1;
        }
        return ans;
    }
 
    abstract public String getRandomText(int numChars);

}
