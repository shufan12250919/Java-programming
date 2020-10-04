
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
 
public class WordPlay {
    public boolean IsVowel(char c){
        String vowel = "aeiouAEIOU";
        if(vowel.indexOf(c) != -1) return true;
        return false;
    }
    
    public void testIsVowel(){
        char c = 'a';
        System.out.println(IsVowel(c));
        c = 'F';
        System.out.println(IsVowel(c));
    }
    
    public String replaceVowels(String phrase, char ch){
        StringBuilder str = new StringBuilder(phrase);
        for(int i = 0; i < str.length(); i++){
            if(IsVowel(str.charAt(i)) == true) {
                str.setCharAt(i, ch);
            }
        }
        return str.toString();
    }
    
    public String emphasize(String phrase, char ch){
        StringBuilder str = new StringBuilder(phrase);
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ch) {
                if(i % 2 == 0) {
                    str.setCharAt(i, '*');
                } else {
                    str.setCharAt(i, '+');
                }
            }
        }
        return str.toString();
    }
}   
