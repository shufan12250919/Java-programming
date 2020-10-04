
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String s) {
        int start = s.indexOf("ATG");
        if (start == -1) {return "";}
        int stop = s.indexOf("TAA", start+3);
        if (stop == -1) { return "";}
        String result = s.substring(start, stop);
        if (result.length() % 3 == 0) {
            return result;
        }
        return "";
            
    }
    
    public void testSimpleGene(){
        String dna = "ATGAAACCCTAA";
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGAAACCTAA";
        System.out.println(findSimpleGene(dna));
        
        dna = "CCCATGAAACCCTAA";
        System.out.println(findSimpleGene(dna));
        
        dna = "AAACCCTAA";
        System.out.println(findSimpleGene(dna));
        
        dna = "ATGAAACCC";
        System.out.println(findSimpleGene(dna));
    }
    

}
