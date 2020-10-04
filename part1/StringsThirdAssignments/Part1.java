
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.File;

public class Part1 {
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
    
    public StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource genes = new StorageResource();
        while(true) {
            String ge = findGene(dna, start);
            if (ge.isEmpty()){
                break;
            }
            genes.add(ge);
            start = dna.indexOf(ge, start) + ge.length();
        }
        return genes;
    }
    
    public void testgetAllGenes(){
        String a = "ATGAAATAAATGTTTTAA";
        StorageResource genes = getAllGenes(a);
        for(String s:genes.data()){
            System.out.println(s);
        }
        
    }
    
    public double cgRatio(String dna) {
        double cg = 0.0;
        for (int i = 0; i < dna.length(); i++){
            if (dna.charAt(i) == 'G' || dna.charAt(i) == 'C'){
                cg = cg + 1.0;
            }
        }
        return cg / dna.length(); 
    }
    
    public int countCTG(String dna){
        int total = 0;
        int start = 0;
        int index = dna.indexOf("CTG", start);
        while(index != -1) {
            total++;
            start = index+3;
            index = dna.indexOf("CTG", start);
        }
        return total;
    }
    
    public void processGenes(StorageResource sr){
        System.out.println("Genes:" + sr.size());
        
        int longerThanNine = 0;
        System.out.println("Genes are longer than 60:");
        for(String s:sr.data()){
            if(s.length() > 60){
                longerThanNine++;
                System.out.println(s);
            }   
        }
        System.out.println("Numbers of Genes is longer than 60: " + longerThanNine);
        
        int cgNumbers = 0;
        for(String s:sr.data()){
            if(cgRatio(s) > 0.35){
                cgNumbers++;
                System.out.println(s);
            }   
        }
        System.out.println("Numbers of Genes' cgRatio larger than 0.35: " + cgNumbers);
        
        int max = 0;
        for(String s:sr.data()){
            if(s.length() > max){
                max = s.length();
            }   
        }
        System.out.println("The longest Gene: " + max);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        processGenes(getAllGenes(dna));
        System.out.println("CTG counting:" + countCTG(dna));
        
        
    }
    
}
