
/**
 * Write a description of EfficientMarkovWord here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class EfficientMarkovWord implements IMarkovModel{
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram, ArrayList<String>> map;
    
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<WordGram, ArrayList<String>>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
    
    public void buildMap(){
        for(int i = 0; i < myText.length - myOrder; i++){
            WordGram key = new WordGram(myText, i, myOrder);
            if(map.containsKey(key)){
                map.get(key).add(myText[i+myOrder]);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(myText[i+myOrder]);
                map.put(key, list);
            }
        }
        WordGram key = new WordGram(myText, myText.length - myOrder, myOrder);
        if(!map.containsKey(key)){
            map.put(key, new ArrayList<String>());
        }
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
        return map.get(kGram);
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
    
    public void printHashMapInfo(){
        if(map.size() < 30){
            for(WordGram key : map.keySet()){
                System.out.println(map.get(key));
            }
        }
        System.out.println("There are " + map.size() + " keys in the HashMap.");
        int max = 0;
        ArrayList<WordGram> largest = new ArrayList<>();
        for(WordGram key: map.keySet()){
            if(map.get(key).size() > max){
                max = map.get(key).size();
                largest = new ArrayList<WordGram>();
                largest.add(key);
            } else if (map.get(key).size() == max){
                largest.add(key);
            }
        }
        System.out.println("The size of the largest value in HashMap is " + max);
        System.out.println("Those keys that have the largest value are: ");
        for(WordGram w : largest){
            System.out.println(w.toString());
        }
    }
}
