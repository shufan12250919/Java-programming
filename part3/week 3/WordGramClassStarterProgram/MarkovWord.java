
/**
 * Write a description of MarkoWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MarkovWord implements IMarkovModel{
    
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length - myOrder);  // random word to start with
        WordGram key = new WordGram(myText, index, myOrder);
        sb.append(key.toString());
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
            key = key.shiftAdd(next);
        }
        
        return sb.toString().trim();
    }
    
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follow = new ArrayList<String>();
        int pos = 0;
        while(pos < myText.length){
            int ind = indexOf(myText, kGram, pos);
            if(ind == myText.length - myOrder || ind == -1) break;
            follow.add(myText[ind + myOrder]);
            pos = ind + 1;
        }
        return follow;
    }
    
    private int indexOf(String[] words, WordGram target, int start){
        if(words == null || words.length == 0) return -1;
        for(int i = start; i < words.length - myOrder; i++){
            WordGram temp = new WordGram(words, i, myOrder);
            if(target.equals(temp)){
                return i;    
            }
        }
        return -1;
    }  
    
}
