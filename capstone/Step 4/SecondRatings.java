
/**
 * Write a description of SecondRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingfile) {
        FirstRatings r = new FirstRatings();
        myMovies = r.loadMovies(moviefile);
        myRaters = r.loadRaters(ratingfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        for(Movie m : myMovies){
            double rate = getAverageByID(m.getID(), minimalRaters);
            if(rate != 0.0){
                Rating r = new Rating(m.getID(), rate);
                average.add(r);
            }
        }
        return average;
    }
    
    public String getTitle(String id){
        for(Movie m : myMovies){
            if(m.getID().equals(id)) return m.getTitle();
        }
        return "ID was not found.";
    }
    
    public String getID(String title) {
        for(Movie m : myMovies){
            if(m.getTitle().equals(title)) return m.getID();
        }
        return "NO SUCH TITLE.";
    }
}
