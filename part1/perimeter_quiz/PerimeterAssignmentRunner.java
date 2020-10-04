import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int total = 0;
        for (Point currPt: s.getPoints()){
            total++;
        }
        return total;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double avg = getPerimeter(s) / getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double max = 0.0;
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // check if the dist is larger than the max dis
            if (currDist > max){    
                // Update max
                max = currDist;
            }
            
            prevPt = currPt;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        int max = prevPt.getX();
        for (Point currPt : s.getPoints()) {
            // check if the dist is larger than the max dis
            if (currPt.getX() > max){    
                // Update max
                max = currPt.getX();
            }
            
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double max = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri > max){
                max = peri;
            }
        }
        return max;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;  
        double max = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri > max){
                max = peri;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        int points = getNumPoints(s);
        System.out.println("Number of points = " + points);
        double avg = getAverageLength(s);
        System.out.println("Average of length = " + avg);
        double large = getLargestSide(s);
        System.out.println("The largest of length = " + large);
        double largeX = getLargestX(s);
        System.out.println("The largest of X = " + largeX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double max = getLargestPerimeterMultipleFiles();
        System.out.println("perimeter = " + max);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String max = getFileWithLargestPerimeter();
        System.out.println("file name = " + max);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
