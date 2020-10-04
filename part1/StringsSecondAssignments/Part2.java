
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String a, String b){
        int total = 0;
        int start = 0;
        while(true){
            int curr = b.indexOf(a, start);
            if (curr == -1){break;}
            total++;
            start = a.length() + curr;
        }
        
        return total;
    }
    
    public void testHowMany() {
        String a = "GAA";
        String b = "ATGAACGAATTGAATC";
        System.out.println("String: " + b + " Search: " + a);
        System.out.println("Times: " + howMany(a, b));
        a = "AA";
        b = "ATAAAA";
        System.out.println("String: " + b + " Search: " + a);
        System.out.println("Times: " + howMany(a, b));
        
    }
}
