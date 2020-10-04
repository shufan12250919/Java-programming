
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer le = new LogAnalyzer();
        le.readFile("short-test_log");
        le.printAll();
        
    }
    
    public void testUniqIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs.");
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        la.printAllHigherThanNum(400);
    }
        
    public void testUniqueIPVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        ArrayList<String> ips = la.uniqueIPVisitsOnDay("Sep 27");
        for(String ip:ips){
            System.out.println(ip);
        }
        System.out.println("There are " + ips.size() + " IPs.");
    }
    
    public void testCountUniqueIPsInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high = 299;
        int UniqueIPsInRange = la.countUniqueIPsInRange(low,high);
        System.out.println("There are " + UniqueIPsInRange + " in range from "
                            + low + " to " + high);
    }
        
    public void testCounts(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        System.out.println(counts);
        System.out.println("The maximum visit time is " + la.mostNumberVisitsByIP(counts));
        System.out.println("The maximum visit ips are: ");
        System.out.println(la.IPsMostVisits(counts));
    }
    
    public void testIPsForDays(){
       LogAnalyzer la = new LogAnalyzer();
       la.readFile("weblog2_log");
       HashMap<String, ArrayList<String>> counts = la.IPsForDays();
       System.out.println(counts);
       
       System.out.println("The day with most IP visit: " + la.dayWithMostIPVisits(counts));
       
       String date = "Sep 29";
       System.out.println("The IPs with most visit on " + date + " :");
       System.out.println(la.iPsWithMostVisitsOnDay(counts, date));
    }
    
}
