
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerAverage {
    
    public void printAverageRatings(){
        SecondRatings r = new SecondRatings();
        //SecondRatings r = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        System.out.println("There " + r.getMovieSize() + " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 12;
        ArrayList<Rating> average = r.getAverageRatings(people);
        Collections.sort(average);
        System.out.println(average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " + r.getTitle(item.getItem()));
        }
        
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings r = new SecondRatings();
        //SecondRatings r = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        //String title = "The Maze Runner";
        //String title = "Moneyball";
        String title = "Vacation";
        int people = 3;
        ArrayList<Rating> average = r.getAverageRatings(people);
        String id = r.getID(title);
        for(Rating rate : average){
            if(rate.getItem().equals(id)){
                System.out.println("The rate of " + title + " is: " + rate.getValue());
                break;
            }
        }
    }
}
