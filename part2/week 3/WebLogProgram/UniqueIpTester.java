
/**
 * Write a description of UniqueIpTester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class UniqueIpTester {
    public void testUniqIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("There are " + uniqueIPs + " IPs.");
    }
}
