
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
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
    
    public String halfOfString(String str, int start){
        String out = "";
        for(int i = start; i < str.length(); i+=2){
            out = out + str.charAt(i);
        }
        return out;
    }
    
    public int getKey(String s){
        int[] freqs = countLetters(s);
        int max = maxIndex(freqs);
        int dkey = max - 4;
        if(max < 4){
            dkey = 26 + max - 4;
        }
        return dkey;
    }
    
    public void simpleTest(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key1 = 17;
        int key2 = 3;
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String en = cc.encrypt(message);
        System.out.println("Message after encrypted: " + en);
        String de = cc.decrypt(en);
        System.out.println("Message after decrypted: " + de);
        
        String auto = breakCaesarCipherTwo(en);
        System.out.println("Using auto detect to decrypt: " + auto);
    }
    
    public String breakCaesarCipherTwo(String input){
        String first = halfOfString(input, 0);
        String second = halfOfString(input, 1);
        int key1 = getKey(first);
        int key2 = getKey(second);
        System.out.println("The first key is " + key1);
        System.out.println("The second key is " + key2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String out = cc.decrypt(input);
        return out;
    }
}
