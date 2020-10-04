
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Movie> list = new ArrayList<>();
        for(CSVRecord r : fr.getCSVParser()) {
            String id = r.get("id");
            String title = r.get("title");
            String year = r.get("year");
            String genres = r.get("genre");
            String director = r.get("director");
            String country = r.get("country");
            String poster = r.get("poster");
            int minutes = Integer.parseInt(r.get("minutes"));
            Movie cur = new Movie(id, title, year, genres, director, country, poster, minutes);
            list.add(cur);
        }
        return list;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Rater> list = new ArrayList<>();
        HashMap<String, Rater> map = new HashMap<>();
        for(CSVRecord r : fr.getCSVParser()) {
            String id = r.get("rater_id");
            String movieID = r.get("movie_id");
            int rating = Integer.parseInt(r.get("rating"));
            Rater cur = new Rater(id);
            if(map.containsKey(id)){
                map.get(id).addRating(movieID, rating);
            } else {
                cur.addRating(movieID, rating);
                map.put(id, cur);
            }
        }
        for(String id : map.keySet()){
            list.add(map.get(id));
        }
        return list;
    }
    
    public void testLoadMovies() {
        ArrayList<Movie> list = loadMovies("ratedmoviesfull.csv");
        System.out.println("There are " + list.size() + " movies in the data file.");
        //for(Movie m : list) {
        //   System.out.println("Movie: " + m.getTitle());
        //}
        
        int comedies = 0;
        for(Movie m : list) {
            if(m.getGenres().contains("Comedy")){
                comedies++;
            }
        }
        System.out.println("There are " + comedies + " comedies.");
        
        int longmovies = 0;
        for(Movie m : list) {
            if(m.getMinutes() > 150){
                longmovies++;
            }
        }
        System.out.println("There are " + longmovies + " movies over 150 mins.");
        
        HashMap<String, Integer> map = new HashMap<>();
        for(Movie m : list) {
            String[] directors = m.getDirector().split(",");
            for(String director : directors){
                director = director.trim();
                if(map.containsKey(director)){
                    map.put(director, map.get(director) + 1);
                } else {
                    map.put(director, 1);
                }
            }
        }
        int directmovies = 0;
        for(String director : map.keySet()){
            if(map.get(director) > directmovies) {
                directmovies = map.get(director);
            }
        }
        System.out.println("The maximum number of movies directed by one director is : " + directmovies);
        for(String director : map.keySet()){
            if(map.get(director) == directmovies) {
                System.out.println(director);
            }
        }
    }
    
    public void testLoadRaters(){
        ArrayList<Rater> list = loadRaters("ratings.csv");
        System.out.println("There are " + list.size() + " raters in the data file.");
        //for(Rater r : list){
        //   System.out.println("The rater " + r.getID() + " rates " + r.numRatings() + " movies.");
        //   for(String m : r.getItemsRated()){
        //       System.out.println(m + " : " + r.getRating(m));
        //   }
        //}
        
        String id = "193";
        int num = 0;
        for(Rater r : list){
            if(r.getID().equals(id)){
                num = r.numRatings();
                break;
            }
        }
        System.out.println("There are " + num + " rating from rater " + id);
        
        int max = 0;
        ArrayList<Rater> many = new ArrayList<>();
        for(Rater r : list){
            if(r.numRatings() > max){
                max = r.numRatings();
                many = new ArrayList<>();
            }
            if(r.numRatings() == max){
                many.add(r);
            }
        }
        System.out.println("The maximum number of ratings by any rater is " + max);
        System.out.println("There are " + many.size() + " raters has "  + max + " rating");
        for(Rater r : many){
            System.out.println(r.getID());
        }
        
        int total = 0;
        String movieID = "1798709";
        for(Rater r:list){
            if(r.hasRating(movieID)){
                total++;
            }
        }
        System.out.println("There are " + total + " rating for movie ID " + movieID);
        
        HashSet<String> mov = new HashSet<>();
        for(Rater r:list){
            for(String item: r.getItemsRated()){
                mov.add(item);
            }
        }
        System.out.println("There are total " + mov.size() + " movies be rated.");
        
    }
}
