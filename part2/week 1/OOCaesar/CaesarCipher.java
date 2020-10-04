
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    private String alphabet;
    private String alphabetl;
    private String shiftedalphabet;
    private String shiftedalphabetl;
    private int mainkey;
    
    public CaesarCipher(int key){
        mainkey = key;
        
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedalphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        
        alphabetl = "abcdefghijklmnopqrstuvwxyz";
        shiftedalphabetl = alphabetl.substring(key)+
        alphabetl.substring(0,key);
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if(Character.isUpperCase(currChar)){
                int idx = alphabet.indexOf(currChar);
                if(idx != -1){
                    char newChar = shiftedalphabet.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = alphabetl.indexOf(currChar);
                if(idx != -1){
                    char newChar = shiftedalphabetl.charAt(idx);
                    encrypted.setCharAt(i, newChar);
                }
            }
            
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainkey);
        return cc.encrypt(input);
    }
}
