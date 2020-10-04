import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder out = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i+= totalSlices){
            out.append(message.charAt(i));
        }
        return out.toString();
    }
    
    public void testSliceString(){
        System.out.println(sliceString("abcdefghijklm", 0, 3));
        System.out.println(sliceString("abcdefghijklm", 1, 3));
        System.out.println(sliceString("abcdefghijklm", 0, 4));
        System.out.println(sliceString("abcdefghijklm", 3, 5));
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++){
            CaesarCracker crack = new CaesarCracker();
            String en = sliceString(encrypted, i, klength);
            int num = crack.getKey(en);
            key[i] = num;
        }
        return key;
    }
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> out = new HashSet<>();
        for(String word: fr.lines()){
            out.add(word.toLowerCase());
        }
        return out;
    }
    
    public char mostCommonCharIn(HashSet<String> dic){
        HashMap<Character, Integer> map = new HashMap<>();
        for(String word: dic){
            for(int i = 0; i < word.length(); i++){
                char c = word.charAt(i);
                if(map.containsKey(c)){
                    map.put(c, map.get(c)+1);
                }else{
                    map.put(c, 1);
                }
            }
        }
        char out = 'e';
        int max = 0;
        for(char c: map.keySet()){
            if(map.get(c) > max){
                out = c;
                max = map.get(c);
            }
        }
        return out;
    }
    
    public int countWords(String mes, HashSet<String> dic){
        int out = 0;
        for(String word: mes.split("\\W+")){
            if(dic.contains(word.toLowerCase())){
                out++;
            }
        }
        return out;
    }
    
    public String breakForLanguage(String en, HashSet<String> dic){
        int max = 0;
        String out = "";
        int[] key = new int[0];
        char com = mostCommonCharIn(dic);
        
        for(int i = 1; i < 100; i++){
            int[] curkey = tryKeyLength(en, i, com);
            VigenereCipher cipher = new VigenereCipher(curkey);
            String mes = cipher.decrypt(en);
            int cur = countWords(mes, dic);
            if(cur > max){
                max = cur;
                out = mes;
                key = curkey;
            }
            int s = 38;
            if(i == s){
                //System.out.println("Valid words of key length of " + s + ": " + cur);
            }
        }
        
        //System.out.println(Arrays.toString(key));
        //System.out.println("The key length is: " + key.length);
        //System.out.println("Valid words: " + max);
        return out;
    }
    
    public void breakForAllLangs(String en, HashMap<String, HashSet<String>> lans){
        int max = 0;
        String language = "";
        String out = "";
        for(String l : lans.keySet()){
            String temp = breakForLanguage(en, lans.get(l));
            int cur = countWords(temp, lans.get(l));
            if(cur > max){
                max = cur;
                language = l;
                out = temp;                
            }
        }
        System.out.println("The language is: " + language);
        System.out.println(out);
    }
    
    
    public void breakVigenere () {

        FileResource fr = new FileResource();
        String en = fr.asString();
        
        //FileResource fdic = new FileResource();
        //HashSet<String> dic = readDictionary(fdic);
        //String out = breakForLanguage(en, dic);
        //System.out.println(out);
        HashMap<String, HashSet<String>> map = new HashMap<>();
        String[] lans = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for(String lan:lans){
            FileResource fdic = new FileResource("dictionaries/" + lan);
            HashSet<String> dic = readDictionary(fdic);
            map.put(lan, dic);
            System.out.println("Finished insert" + lan);
        }
        breakForAllLangs(en, map);
    
        
    }
    
}
