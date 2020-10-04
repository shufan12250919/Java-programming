
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    private ArrayList<String> characters;
    private ArrayList<Integer> shown;
    
    public CharactersInPlay(){
        characters = new ArrayList<String>();
        shown = new ArrayList<Integer>();
    }
    
    public void update(String person){
        if(characters.contains(person)){
            int id = characters.indexOf(person);
            int time = shown.get(id);
            shown.set(id, time+1);
        } else {
            characters.add(person);
            shown.add(1);
        }
    }
    
    public void findAllCharacters(){
        characters.clear();
        shown.clear();
        FileResource resource = new FileResource();
        for(String s : resource.lines()){
            int pos = s.indexOf(".");
            if(pos != -1){
                String name = s.substring(0,pos);
                for(int i = 0; i < name.length(); i++){
                    if(Character.isLetter(name.charAt(i))){
                        name = name.substring(i);
                        break;
                    }
                }
                update(name);
            }
        }
    }
    
    public void tester(){
        findAllCharacters();
        int max = shown.get(0);
        String main = characters.get(0);
        for(int i= 0; i < characters.size(); i++){
            if(shown.get(i) > 1){
                System.out.println(characters.get(i) + " " + shown.get(i));
            }
            if(shown.get(i) > max){
                max = shown.get(i);
                main = characters.get(i);
            }
        }
        System.out.println("The main character is: " + main + " shown " + max);
        
        int num1 = 2;
        int num2 = 100;
        System.out.println("Characters that shown from " + num1 + " to " + num2);
        charactersWithNumParts(num1, num2);
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i =0; i < characters.size(); i++){
            if(shown.get(i) >= num1 && shown.get(i) <= num2){
                System.out.println(characters.get(i));
            }            
        }
    }
}
