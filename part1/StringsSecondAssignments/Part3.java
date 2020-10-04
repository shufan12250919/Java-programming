
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int start, String stop){
        int index = dna.indexOf(stop, start+3);
        while (index != -1) {
            if((index - start)%3 == 0) {
                return index;
            }
            index = dna.indexOf(stop, index+1);
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        String a = "ATGAAATAA";
        System.out.println(findStopCodon(a, 0, "TAA"));
        a = "ATGAATAA";
        System.out.println(findStopCodon(a, 0, "TAA"));
    }
    
    public String findGene(String dna, int where){
        int start = dna.indexOf("ATG", where);
        if (start == -1) {return "";}
        int taaIndex = findStopCodon(dna, start, "TAA");
        int tagIndex = findStopCodon(dna, start, "TAG");
        int tgaIndex = findStopCodon(dna, start, "TGA");
        int min = Math.min(Math.min(taaIndex, tagIndex), tgaIndex);
        if (min == dna.length()){return "";}
        return dna.substring(start, min+3);
    }
    
    public void testFindGene() {
        String a = "TAA";
        System.out.println("First: " + a);
        System.out.println("Gene: " + findGene(a, 0));
        a = "ATGAAATAA";
        System.out.println("Second: " + a);
        System.out.println("Gene: " + findGene(a, 0));
        a = "ATGAAATGATAA";
        System.out.println("Third: " + a);
        System.out.println("Gene: " + findGene(a, 0));
        a = "ATGAAATAGTGATAA";
        System.out.println("Forth: " + a);
        System.out.println("Gene: " + findGene(a, 0));
        a = "ATGAATAA";
        System.out.println("Fifth: " + a);
        System.out.println("Gene: " + findGene(a, 0));
    }
    
    public void printAllGenes(String dna) {
        int start = 0;
        while(true) {
            String ge = findGene(dna, start);
            if (ge.isEmpty()){
                break;
            }
            System.out.println(ge);
            start = dna.indexOf(ge, start) + ge.length();
        }
    }
    
    public int countGenes(String dna) {
        int start = 0;
        int total = 0;
        while(true) {
            String ge = findGene(dna, start);
            if (ge.isEmpty()){
                break;
            }
            total++;
            start = dna.indexOf(ge, start) + ge.length();
        }
        return total;
    }
    
    public void testCountGenes(){
        String a = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA: " + a);
        System.out.println("Genes count: " + countGenes(a));
    }
}
