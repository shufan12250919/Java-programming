 import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetl = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedAlphabetl = alphabetl.substring(key)+
        alphabetl.substring(0,key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            if(Character.isUpperCase(currChar)){
                int idx = alphabet.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            } else {
                int idx = alphabetl.indexOf(currChar);
                //If currChar is in the alphabet
                if(idx != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetl.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }
            }
            
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public void testCaesar() {
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
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
        odd = encrypt(odd, key1);
        even = encrypt(even, key2);
        for(int i = 0; i < odd.length(); i++){
            out.append(odd.charAt(i));
            if(i < even.length()) out.append(even.charAt(i));
        }
        return out.toString();
    }
    
    public void testEncryptTwoKeys(){
        int key1 = 21;
        int key2 = 8;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        String encrypted = encryptTwoKeys(message, key1, key2);
        System.out.println("key1 is " + key1 + " key2 is " + key2 + "\n" + encrypted);
    }
}

