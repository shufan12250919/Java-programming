
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for(String line:fr.lines()){
             records.add(WebLogParser.parseEntry(line));
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIps = new ArrayList<>();
         for(LogEntry le:records){
             String ipAd = le.getIpAddress();
             if(!uniqueIps.contains(ipAd)){
                 uniqueIps.add(ipAd);
             }
        }
        return uniqueIps.size();
    }
    
    public void printAllHigherThanNum(int num){
        for(LogEntry le:records){
            int sta = le.getStatusCode();
            if(sta > num){
                System.out.println(le);
            }
        }
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday){
        ArrayList<String> uniqueIP = new ArrayList<>();
        for(LogEntry le:records){
            String date = le.getAccessTime().toString().substring(4,10);
            //System.out.println(date);
            if(date.equals(someday) && !uniqueIP.contains(le.getIpAddress())){
                uniqueIP.add(le.getIpAddress());
            }
        }
        return uniqueIP;
    }
    
    public int countUniqueIPsInRange(int low, int high){
        ArrayList<String> unIP = new ArrayList<>();
        for(LogEntry le:records){
            int sta = le.getStatusCode();
            if(sta >= low && sta <= high){
                if(!unIP.contains(le. getIpAddress())){
                    unIP.add(le.getIpAddress());
                }
            }
        }
        return unIP.size();
    }
    
    public HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> counts = new HashMap<>();
        for(LogEntry le:records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip, 1);
            }else{
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
        int max = Integer.MIN_VALUE;
        for(String key:counts.keySet()){
            if(counts.get(key) > max){
                max = counts.get(key);
            }
        }
        return max;
    }
    
    public ArrayList<String> IPsMostVisits(HashMap<String, Integer> counts){
        ArrayList<String> ips = new ArrayList<>();
        int max = mostNumberVisitsByIP(counts);
        for(String key:counts.keySet()){
            if(counts.get(key) == max){
                ips.add(key);
            }
        }
        return ips;
    }
    
    public HashMap<String, ArrayList<String>> IPsForDays(){
        HashMap<String, ArrayList<String>> ipforday = new HashMap<>();
        for(LogEntry le:records){
            String date = le.getAccessTime().toString().substring(4,10);
            if(!ipforday.containsKey(date)){
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(le.getIpAddress());
                ipforday.put(date, ips);
            }else{
                ArrayList<String> curips = ipforday.get(date);
                String curip = le.getIpAddress();
                curips.add(curip);
                //if(!curips.contains(le.getIpAddress())){
                //    curips.add(le.getIpAddress());
                //    ipforday.put(date, curips);
                //}
            }
        }
        return ipforday;
    }
    
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> ipsForDay){
        int max = 0;
        String day = "";
        for(String date:ipsForDay.keySet()){
            int total = ipsForDay.get(date).size();
            if(total > max){
                max = total;
                day = date;
            }
        }
        return day;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> ipsForDay, String date){
        ArrayList<String> ips = new ArrayList<>();
        for(String day:ipsForDay.keySet()){
            if(day.equals(date)){
                ips = ipsForDay.get(day);
                break;
            }
        }
        
        HashMap<String, Integer> counts = new HashMap<>();
        for(String ip:ips){
            if(!counts.containsKey(ip)){
                counts.put(ip, 1);
            } else {
                counts.put(ip, counts.get(ip) + 1);
            }
        }
        return IPsMostVisits(counts);
    }
}
