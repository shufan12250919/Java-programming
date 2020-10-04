
/**
 * Write a description of MarkovWordTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWordTwo implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
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
        int index = myRandom.nextInt(myText.length-2);  // random word to start with
        String key1 = myText[index];
        String key2 = myText[index+1];
        sb.append(key1);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");
        for(int k=0; k < numWords-2; k++){
            ArrayList<String> follows = getFollows(key1, key2);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key1 = key2;
            key2 = next;
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> ans = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length - 1){
            int ind = indexOf(myText, key1, key2, pos);
            if(ind >= myText.length - 2 || ind == -1) break;
            ans.add(myText[ind+2]);
            pos = ind + 1;
        }
        return ans;
    }
    
    private int indexOf(String[] text, String key1, String key2, int pos){
        if(text == null || text.length == 0) return -1;
        for(int i = pos; i < text.length - 2; i++){
            if(text[i].equals(key1) && text[i+1].equals(key2)){
                return i;
            }
        }
        return -1;
    }
    
    public void testGetFollows(){
        String s = "this is just a test yes this is a simple test";
        MarkovWordTwo markovWord = new MarkovWordTwo();
        markovWord.setTraining(s);
        ArrayList<String> follow = markovWord.getFollows("just", "a");
        
        for(String word:follow){
            System.out.println(word);
        }
    }
}
