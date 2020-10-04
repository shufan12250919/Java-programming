
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 35;
        ArrayList<Rating> average = r.getAverageRatings(people);
        Collections.sort(average);
        System.out.println(average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " + MovieDatabase.getTitle(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYear(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 20;
        int year = 2000;
        YearAfterFilter fil = new YearAfterFilter(year);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " +  MovieDatabase.getTitle(item.getItem()) + " " + MovieDatabase.getYear(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByGenre(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 20;
        String type = "Comedy";
        GenreFilter fil = new GenreFilter(type);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " +  MovieDatabase.getTitle(item.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByMinutes(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 5;
        int min = 105;
        int max = 135;
        MinutesFilter fil = new MinutesFilter(min, max);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " Time: " + MovieDatabase.getMinutes(item.getItem()) + " " +  MovieDatabase.getTitle(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByDirectors(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 4;
        String name = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        DirectorsFilter fil = new DirectorsFilter(name);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " +  MovieDatabase.getTitle(item.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 8;
        int year = 1990;
        String gen = "Drama";
        YearAfterFilter f1 = new YearAfterFilter(year);
        GenreFilter f2 = new GenreFilter(gen);
        AllFilters fil = new AllFilters();
        fil.addFilter(f1);
        fil.addFilter(f2);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " +  MovieDatabase.getTitle(item.getItem()) + " " + MovieDatabase.getYear(item.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        //ThirdRatings r = new ThirdRatings();
        ThirdRatings r = new ThirdRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + r.getRaterSize() + " raters.");
        int people = 3;
        int min = 90;
        int max = 180;
        String directors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        MinutesFilter f1 = new MinutesFilter(min, max);
        DirectorsFilter f2 = new DirectorsFilter(directors);
        AllFilters fil = new AllFilters();
        fil.addFilter(f1);
        fil.addFilter(f2);
        ArrayList<Rating> average = r.getAverageRatingsByFilter(fil, people);
        Collections.sort(average);
        System.out.println("Found " + average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " Time: " + MovieDatabase.getMinutes(item.getItem()) + " " +  MovieDatabase.getTitle(item.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(item.getItem()));
        }
        
    }
}
