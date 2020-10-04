
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        int seed = 844;
        runModel(markovWord, st, 200, seed); 
    } 
    
    public void runEfficientMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord markovWord = new EfficientMarkovWord(5); 
        int seed = 844;
        runModel(markovWord, st, 200, seed); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    
    public void testHashMap(){
        //String s = "this is a test yes this is really a test";
        //String s = "this is a test yes this is really a test yes a test this is wow";
        FileResource fr = new FileResource(); 
        String s = fr.asString(); 
        s = s.replace('\n', ' '); 
        EfficientMarkovWord test = new EfficientMarkovWord(2);
        test.setTraining(s);
        test.setRandom(65);
        test.printHashMapInfo();
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        int size = 100;
        int seed = 42;
        
        long past =  System.nanoTime();
        EfficientMarkovWord fast = new EfficientMarkovWord(2);
        runModel(fast, st, size, seed);
        long now =  System.nanoTime();
        System.out.println("Use " + (now - past) / 1000 + " milliseconds!");
        
        past =  System.nanoTime();
        MarkovWord slow = new MarkovWord(2); 
        runModel(slow, st, size, seed);
        now =  System.nanoTime();
        System.out.println("Use " + (now - past) / 1000 + " milliseconds!");
    }
}
