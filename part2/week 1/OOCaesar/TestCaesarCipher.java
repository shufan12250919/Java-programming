
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    public int[] countLetters(String str){
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for(int i = 0; i < str.length(); i++){
            char c = Character.toLowerCase(str.charAt(i));
            int ind = alph.indexOf(c);
            if(ind != -1){
                counts[ind]++;
            }
        }
        return counts;
    }
    
    public int maxIndex(int[] ar){
        int max = 0;
        int index = -1;
        for(int i = 0; i < ar.length; i++){
            if(ar[i] > max){
                max = ar[i];
                index = i;
            }
        }
        return index;
    }
    
    public void simpleTest(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 18;
        CaesarCipher cc = new CaesarCipher(key);
        String en = cc.encrypt(message);
        System.out.println("Message after encrypted: " + en);
        String de = cc.decrypt(en);
        System.out.println("Message after decrypted: " + de);
        
        String auto = breakCaesarCipher(en);
        System.out.println("Using auto detect to decrypt: " + auto);
    }
    
    public String breakCaesarCipher(String input){
        int[] freqs = countLetters(input);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 'e' is at index 4, index of a is the key
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        CaesarCipher cc = new CaesarCipher(dkey);
        String out = cc.decrypt(input);
        return out;
    }
    
}
