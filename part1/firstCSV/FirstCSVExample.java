/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
    
    public String countryInfo(CSVParser csv, String country){
        for(CSVRecord record : csv){
            String c = record.get("Country");
            if(c.equals(country)){
                return c + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
            
        }  
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser p, String item1, String item2){
        for(CSVRecord record : p){
            String c = record.get("Country");
            if(record.get("Exports").contains(item1) && record.get("Exports").contains(item2)){
                System.out.println(c);
            }
            
        }
    }
    
    public int numberOfExporters(CSVParser p, String item){
        int sum = 0;
        for(CSVRecord record : p){
            if(record.get("Exports").contains(item)){
                sum++;
            }
        }
        return sum;
    }
    
    public void bigExporters(CSVParser p, String d){
        int l = d.length();
        for(CSVRecord record : p){
            if(record.get("Value (dollars)").length() > l){
                System.out.println(record.get("Country") + ": " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       //System.out.println(countryInfo(parser, "Germany"));
       //parser = fr.getCSVParser();
       //listExportersTwoProducts(parser, "gold", "diamonds");
       //parser = fr.getCSVParser();
       //System.out.println(numberOfExporters(parser, "gold"));
       //parser = fr.getCSVParser();
       //bigExporters(parser, "$999,999,999");
       //quiz
       parser = fr.getCSVParser();
       listExportersTwoProducts(parser, "cotton", "flowers");
       parser = fr.getCSVParser();
       System.out.println(numberOfExporters(parser, "cocoa"));
       //parser = fr.getCSVParser();
       //System.out.println(countryInfo(parser, "Nauru"));
       parser = fr.getCSVParser();
       bigExporters(parser, "$999,999,999,999");
    }
    
    
    
    
}
