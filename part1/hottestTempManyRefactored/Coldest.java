
/**
 * Write a description of Coldest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class Coldest {
    public CSVRecord coldestHourInFile(CSVParser p){
        CSVRecord cold = null;
        for (CSVRecord currentRow : p) {
            if (cold == null) {
                cold = currentRow;
            }
            //Otherwise
            else {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(cold.get("TemperatureF"));
                if(currentTemp == -9999) continue;
                if (currentTemp < coldestTemp) {
                    cold = currentRow;
                }
            }
        }
        return cold;
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("coldtest temperature was " + coldest.get("TemperatureF") +
                   " at " + coldest.get("DateUTC"));
    }
    
    public String fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldone = null;
        CSVRecord coldest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord cold = coldestHourInFile(fr.getCSVParser());
            if(coldest == null) {
                coldest = cold;
                coldone = f;
            }
            else {
                double currentTemp = Double.parseDouble(cold.get("TemperatureF"));
                double coldTemp = Double.parseDouble(coldest.get("TemperatureF"));
                if(currentTemp < coldTemp){
                    coldest = cold;
                    coldone = f;
                }
            }
        }
        return coldone.getName();
    }
    
    public void testFileWithColdestTemperature(){
        String c = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + c);
        FileResource fr = new FileResource(c);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
        //System.out.println("All the Temperatures on the coldest day were:");
        //for(CSVRecord currentRow:fr.getCSVParser()){
        //    System.out.println(currentRow.get("DateUTC") + ": "+  currentRow.get("TemperatureF"));
        //}
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser p){
        CSVRecord low = null;
        for (CSVRecord currentRow : p) {
            if(currentRow.get("Humidity").equals("N/A")){
                continue;
            }
            if (low == null) {
                low = currentRow;
            }
            else {
                int currenthum = Integer.parseInt(currentRow.get("Humidity"));
                int coldesthum = Integer.parseInt(low.get("Humidity"));
            if (currenthum < coldesthum) {
                low = currentRow;
            }
            }
        }
        return low;
    }
    
    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public String lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        File lowone = null;
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord curlow = lowestHumidityInFile(fr.getCSVParser());
            if(lowest == null) {
                lowest = curlow;
                lowone = f;
            }
            else {
                double currenthum = Double.parseDouble(curlow.get("Humidity"));
                double hum = Double.parseDouble(lowest.get("Humidity"));
                if(currenthum < hum){
                    lowest = curlow;
                    lowone = f;
                }
            }
        }
        return lowone.getName();
    }
    
    public void testLowestHumidityInManyFiles(){
        String c = lowestHumidityInManyFiles();
        FileResource fr = new FileResource(c);
        CSVRecord lowest = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
        
    }
    
    public double averageTemperatureInFile(CSVParser p){
        double sum = 0.0;
        for (CSVRecord currentRow : p) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            sum = sum + currentTemp;
            
        }
        
        return sum / 23;
    }
    
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(fr.getCSVParser()));
                   
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser p, int value){
        double sum = 0.0;
        double total = 0.0;
        for (CSVRecord currentRow : p) {
            if(currentRow.get("Humidity").equals("N/A")){
                continue;
            }
            int hum = Integer.parseInt(currentRow.get("Humidity"));
            if (hum >= value){
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                sum = sum + currentTemp;
                total = total + 1;
            }
            
        }
        
        return sum / total;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        System.out.println("Average temperature in file is " + averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80));
         
        
    }
    
    
}
