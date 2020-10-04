
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> myMap;
    
    public WordsInFiles(){
        myMap = new HashMap<String, ArrayList<String>>();
    }
    
    private void addWordsFromFile(File fr){
        FileResource f = new FileResource(fr);
        for(String w:f.words()){
            if(!myMap.containsKey(w)){
                ArrayList<String> list = new ArrayList<String>();
                list.add(fr.getName());
                myMap.put(w, list);
            } else {
                if(!myMap.get(w).contains(fr.getName())){
                    myMap.get(w).add(fr.getName());
                }
            }
        }
    }
    
    private void buildWordFileMap(){
        myMap.clear();
        DirectoryResource many = new DirectoryResource();
        for(File fr:many.selectedFiles()){
            addWordsFromFile(fr);
        }
    }
    
    public int maxNumber(){
        int max =0;
        for(String s: myMap.keySet()){
            int cur = myMap.get(s).size();
            if(cur > max){
                max = cur;
            }
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFiles(int number){
        ArrayList<String> out = new ArrayList<String>();
        for(String s:myMap.keySet()){
            if(myMap.get(s).size() == number){
                out.add(s);
            }
        }
        return out;
    }
    
    public void printFilesIn(String word){
        System.out.println(word + " appears in these filses: ");
        for(String s: myMap.get(word)){
            System.out.println(s);
        }
    }
    
    public void tester(){
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("Total nubmers of words in the files: " + myMap.size());
        System.out.println("The maximum numbers of file a word is: " + max);
        int num = 4;
        ArrayList<String> list = wordsInNumFiles(num);
        System.out.println("Total words occur in " + num +" files: " + list.size());
        //for(int i = 0; i < list.size(); i++){
        //    System.out.println("word" + i + ":");
        //    printFilesIn(list.get(i));
        //}
        printFilesIn("tree");    
    }

}
