
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int ind = indexOf(myText, key, pos);
            if(ind == myText.length - 1 || ind == -1) break;
            ans.add(myText[ind+1]);
            pos = ind + 1;
        }
        return ans;
    }
    
    private int indexOf(String[] text, String key, int pos){
        if(text == null || text.length == 0) return -1;
        for(int i = pos; i < text.length; i++){
            if(text[i].equals(key)){
                return i;
            }
        }
        return -1;
    }
    
    public void testIndexOf(){
        String s = "this is just a test yes this is a simple test";
        String[] ss = s.split("\\s+");
        String key = "this";
        int pos = 0;
        int index = indexOf(ss, key, pos);
        while(index != -1){
            System.out.println(key + " is at index " + index);
            pos = index + 1;
            index = indexOf(ss, key, pos);
        }
    }
}
