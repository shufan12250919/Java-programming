
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    
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
    
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4; // 'e' is at index 4, index of a is the key
        if(maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }
    
    public void testDecrypt(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(decrypt(message));
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
    
    public String decryptTwoKeys(String enc){
        String first = halfOfString(enc, 0);
        String second = halfOfString(enc, 1);
        CaesarCipher cc = new CaesarCipher();
        int key1 = getKey(first);
        int key2 = getKey(second);
        //key1 = 14;
        //key2 = 24;
        System.out.println("The first dkey is " + key1);
        System.out.println("The second dkey is " + key2);
        first = cc.encrypt(first, 26 - key1);
        second = cc.encrypt(second, 26 - key2);
        String out = "";
        for(int i = 0; i < first.length(); i++){
            out = out + first.charAt(i);
            if(i < second.length()) {
                out = out + second.charAt(i);
            }
        }
        return out;
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println(decryptTwoKeys(message));
    }
    
    public void testDecryptTwoKeys2(){
        
        String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        System.out.println(decryptTwoKeys(message));
    }
}
