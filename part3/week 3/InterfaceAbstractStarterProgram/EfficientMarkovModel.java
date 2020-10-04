
/**
 * Write a description of EfficientMarkovModel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int order;
    private HashMap<String, ArrayList<String>> map;
    
    public EfficientMarkovModel(int num) {
    	myRandom = new Random();
    	order = num;
    	map = new HashMap<>();
    }
    
    public void setRandom(int seed){
    	myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
    	myText = s.trim();
    	buildMap();
    }
    
    private void buildMap() {
        // Build HashMap of the characters that follow each substring of order length
        for (int pos = 0; pos <= myText.length() - order; pos++) {
            int subEnd = pos + order;
            // Identify the current substring
            String sub = myText.substring(pos, subEnd);
            // If HashMap doesn't yet contain substring as key
            if (!map.containsKey(sub)) {
                map.put(sub, new ArrayList<String>());
            }
            // Add to HashMap the character that follows current substring, if there is one
            if (subEnd < myText.length()) {
                String follower = myText.substring(subEnd, subEnd + 1);
                //System.out.println(sub + ": " + follower);
                ArrayList<String> followers = map.get(sub);
                followers.add(follower);
                map.put(sub, followers);
            }
        }
    }
    
    protected ArrayList<String> getFollows(String key){
        ArrayList<String> list = map.get(key);
        return list;
    }
    
    public void printHashMapInfo(){
        /*
        for(String key : map.keySet()){
            System.out.println(key + " : " + map.get(key));
        }*/
        
        System.out.println("HashMap's size is " + map.size());
        
        int max = 0;
        String large = "";
        for(String key : map.keySet()){
            if(map.get(key).size() > max){
                max = map.get(key).size();
                large = key;
            }
        }
        System.out.println("HashMap's Largest value is " + max + " in key " + large);
        
    }
    
    public String getRandomText(int numChars){
    	if (myText == null){
    		return "";
    	}
    	StringBuilder sb = new StringBuilder();
    	int index = myRandom.nextInt(myText.length() - order);
    	String key = myText.substring(index, index+order);
    	sb.append(key);
    	for(int k=0; k < numChars - order; k++){
    	       ArrayList<String> follows = getFollows(key);
    	       if(follows == null || follows.size() == 0) break;
    	       index = myRandom.nextInt(follows.size());
    	       String next = follows.get(index);
    	       sb.append(next);
    	       key = key.substring(1) + next;
    	}
    	
    	return sb.toString();
    }
    
    @Override
    public String toString(){
    	return "EfficientMarkovModel of order " + order;
    }
    
    
}
