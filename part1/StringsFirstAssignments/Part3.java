
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    
    public boolean twoOccurrences(String a, String b) {
        int total = 0;
        int first = b.indexOf(a);
        if (first == -1) {return false;}
        if (b.indexOf(a, first+1) == -1) {return false;}
        return true;
        
    }
    
    public String lastPart(String a, String b) {
        int first = b.indexOf(a);
        if (first == -1) {return b;}
        
        return b.substring(first+a.length());
    }
    
    public void testing(){
        String a = "a";
        String b = "banana";
        System.out.println(twoOccurrences(a, b));
        
        a = "atg";
        b = "ctgtatgta";
        System.out.println(twoOccurrences(a, b));
        
        a = "an";
        b = "banana";
        System.out.println(lastPart(a, b));
        
    }

}
