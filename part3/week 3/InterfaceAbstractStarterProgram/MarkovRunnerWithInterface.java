
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
	int size = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, 50);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, 50);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, 50);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, 50);

    }
    
    public void testHashMap(){
        EfficientMarkovModel model = new EfficientMarkovModel(5);
        //String st = "yes-this-is-a-thin-pretty-pink-thistle:";
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
        int size = 50;
        runModel(model, st, size, 531);
        model.printHashMapInfo();
        
    }
    
    public void compareMethods(){
        FileResource fr = new FileResource();
	String st = fr.asString();
	st = st.replace('\n', ' ');
        int size = 1000;
        
        long past = System.nanoTime();
        EfficientMarkovModel fast = new EfficientMarkovModel(2);
        runModel(fast, st, size, 42);
        long now = System.nanoTime();
        System.out.println("Using "+ (now - past) / 1000000 + " milliseconds!");
        
        past = System.nanoTime();
        MarkovModel slow = new MarkovModel(2);
        runModel(slow, st, size, 42);
        now = System.nanoTime();
        System.out.println("Using "+ (now - past) / 1000000 + " milliseconds!");
        
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
	
}
