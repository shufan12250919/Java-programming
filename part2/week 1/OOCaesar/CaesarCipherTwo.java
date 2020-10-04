
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipherTwo {
    private int key1;
    private int key2;
    
    private String alphabet;
    private String alphabetl;
    private String shiftedalphabet1;
    private String shiftedalphabetl1;
    private String shiftedalphabet2;
    private String shiftedalphabetl2;
    
    public CaesarCipherTwo(int keyf, int keys){
        key1 = keyf;
        key2 = keys;
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedalphabet1 = alphabet.substring(key1)+
        alphabet.substring(0,key1);
        shiftedalphabet2 = alphabet.substring(key2)+
        alphabet.substring(0,key2);
        
        alphabetl = "abcdefghijklmnopqrstuvwxyz";
        shiftedalphabetl1 = alphabetl.substring(key1)+
        alphabetl.substring(0,key1);
        shiftedalphabetl1 = alphabetl.substring(key2)+
        alphabetl.substring(0,key2);
    }
    
    public String encrypt(String input){
        String odd = "";
        String even = "";
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(i % 2 == 0){
                odd = odd + input.charAt(i);
            } else {
                even = even + input.charAt(i);
            }
        }
        CaesarCipher c1 = new CaesarCipher(key1);
        CaesarCipher c2 = new CaesarCipher(key2);
        odd = c1.encrypt(odd);
        even = c2.encrypt(even);
        for(int i = 0; i < odd.length(); i++){
            out.append(odd.charAt(i));
            if(i < even.length()) out.append(even.charAt(i));
        }
        return out.toString();
    }
    
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        return cc.encrypt(input);
    }
    
}
