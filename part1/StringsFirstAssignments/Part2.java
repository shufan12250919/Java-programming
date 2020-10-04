
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String s, String startcod, String stopcod) {
        int start = s.indexOf(startcod);
        if (start == -1) {return "";}
        int stop = s.indexOf(stopcod, start+3);
        if (stop == -1) { return "";}
        String result = s.substring(start, stop);
        if (result.length() % 3 == 0) {
            return result;
        }
        return "";
            
    }
    
    public void testSimpleGene(){
        String dna = "ATGAAACCCTAA";
        System.out.println(findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATGAAACCTAA";
        System.out.println(findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "CCCATGAAACCCTAA";
        System.out.println(findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "AAACCCTAA";
        System.out.println(findSimpleGene(dna, "ATG", "TAA"));
        
        dna = "ATGAAACCC";
        System.out.println(findSimpleGene(dna, "ATG", "TAA"));
    }
}
