
/**
 * Write a description of babyname here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class babyname {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord r : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(r.get(2));
            if (numBorn >= 100){
                System.out.println("Name: " + r.get(0) + " Gender: " + r.get(1) + " Num Born: " + r.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr){
        int totalBirth = 0;
        int totalname = 0;
        int totalboy = 0;
        int boyname = 0;
        int totalgirl = 0;
        int girlname = 0;
        for(CSVRecord r : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(r.get(2));
            totalBirth += numBorn;
            totalname++;
            if(r.get(1).equals("M")){
                totalboy += numBorn;
                boyname++;
            } else {
                totalgirl += numBorn;
                girlname++;
            }
        }
        System.out.println("Total birth: " + totalBirth);
        System.out.println("Names of birth: " + totalname);
        System.out.println("Total boys: " + totalboy);
        System.out.println("Names of boys: " + boyname);
        System.out.println("Total girls: " + totalgirl);
        System.out.println("Names of girls: " + girlname);
        
    }
    
    public void testtotalBirth(){
        FileResource fr = new FileResource("data/us_babynames_by_year/yob1900.csv");
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender){
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        if(gender.equals("F")){
            int rank = 0;
            for(CSVRecord r : fr.getCSVParser(false)){
                if(r.get(1).equals("M")) break;
                rank++;
                if(r.get(0).equals(name)) return rank;
            }
        }else{
            int rank = 0;
            for(CSVRecord r : fr.getCSVParser(false)){
                if(r.get(1).equals("M")) {
                    rank++;
                    if(r.get(0).equals(name)) return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        int cur = 0;
        if(gender.equals("F")){
            for(CSVRecord r : fr.getCSVParser(false)){
                cur++;
                if(r.get(1).equals("M")) break; 
                if(cur == rank) return r.get(0);
            }
        } else {
            for(CSVRecord r : fr.getCSVParser(false)){
                if(r.get(1).equals("M")) cur++; 
                if(cur == rank) return r.get(0);
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newyear, String gender){
        int rank = getRank(year, name, gender);
        System.out.println(name + " born in " + year +
                            " would be " + getName(newyear, rank, gender)
                            + " if she was born in " + newyear);
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int rank = 0;
        int year = 0;
        for (File f : dr.selectedFiles()) {
            String years = f.getName();
            int curyear = Integer.parseInt(years.substring(3,7));
            if(rank == 0){
                int currank = getRank(curyear, name, gender);
                if(currank != -1){
                    rank = getRank(curyear, name, gender);
                    year = curyear;
                }
            } else {
                int cur = getRank(curyear, name, gender);
                if(cur == -1) continue;
                if(cur < rank){
                    rank = cur;
                    year = curyear;
                }
            }
        }
        return year;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double rank = 0.0;
        int year = 0;
        for (File f : dr.selectedFiles()) {
            String years = f.getName();
            int curyear = Integer.parseInt(years.substring(3,7));
            int currank = getRank(curyear, name, gender);
            if(currank != -1){
                rank += getRank(curyear, name, gender);
                year ++;
            }   
        }
        rank = rank / year;
        return rank;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        FileResource fr = new FileResource("data/us_babynames_by_year/yob" + year + ".csv");
        int rank = getRank(year, name, gender);
        int totalBirth = 0;
        int cur = 0;
        for(CSVRecord r : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(r.get(2));
            if(r.get(1).equals(gender)){
                cur ++;
                if(cur < rank){
                    
                    totalBirth += numBorn;
                } else {
                    break;
                }
            }
        }
        return totalBirth;
    }
}
