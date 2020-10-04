
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingfile) {
        FirstRatings r = new FirstRatings();
        myRaters = r.loadRaters(ratingfile);
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        int people = 0;
        double total = 0.0;
        for(Rater rr : myRaters) {
            double rating = rr.getRating(id);
            if(rating == -1) continue;
            people ++;
            total += rating;
        }
        if(people < minimalRaters)return 0.0;
        return total / people;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> average = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies){
            double rate = getAverageByID(id, minimalRaters);
            if(rate != 0.0){
                Rating r = new Rating(id, rate);
                average.add(r);
            }
        }
        return average;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(Filter f, int minimalRaters){
        ArrayList<Rating> average = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        for(String id : movies){
            double rate = getAverageByID(id, minimalRaters);
            if(rate != 0.0){
                Rating r = new Rating(id, rate);
                average.add(r);
            }
        }
        return average;
    }
}
