
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class CodonCount {
    private HashMap<String, Integer> myMap;
    
    public CodonCount(){
        myMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        myMap.clear();
        int s = start;
        while(s <= dna.length() - 3){
            String codon = dna.substring(s,s+3);
            if(!myMap.containsKey(codon)){
                myMap.put(codon, 1);
            } else {
                myMap.put(codon, myMap.get(codon)+1);
            }
            s += 3;
        }
    }
    
    public String getMostCommonCodon(){
        int max = 0;
        String most = "";
        for(String s:myMap.keySet()){
            if(myMap.get(s) > max){
                max = myMap.get(s);
                most = s;
            }
        }
        return most;
    }
    
    public void printCodonCounts(int start, int end){
        System.out.println("Codon counts between " + start + " and " + end);
        for(String s: myMap.keySet()){
            if(myMap.get(s) >= start && myMap.get(s) <= end){
                System.out.println(s + " " + myMap.get(s));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        String dna = fr.asString().trim();
        dna = dna.toUpperCase();
        for(int i = 0; i <= 2; i++){
            System.out.println("Reading frame " + i + ":");
            buildCodonMap(i,dna);
            System.out.println("Number of unique codon: " + myMap.size());
            String mm = getMostCommonCodon();
            System.out.println("The most common codon: " + mm + " " + myMap.get(mm));
            printCodonCounts(7,7);
        }
    }
}
