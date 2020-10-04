
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings r = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        int people = 35;
        ArrayList<Rating> average = r.getAverageRatings(people);
        Collections.sort(average);
        System.out.println(average.size() +" movies rated by more than " + people + " raters.");
        for(Rating item : average) {
            System.out.println(item.getValue() + " " + MovieDatabase.getTitle(item.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings r = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
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
    
    public void printSimilarRatings(){
        FourthRatings r = new FourthRatings();
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        ArrayList<Rating> list = r.getSimilarRatings("71", 20, 5);
        System.out.println("There are " + list.size() + " movies recommended!");
        for(Rating id: list){
            System.out.println(MovieDatabase.getTitle(id.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings r = new FourthRatings();
        Filter fr = new GenreFilter("Mystery");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        ArrayList<Rating> list = r.getSimilarRatingsByFilter("964", 20, 5, fr);
        System.out.println("There are " + list.size() + " movies recommended!");
        for(Rating id: list){
            System.out.println(MovieDatabase.getTitle(id.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings r = new FourthRatings();
        Filter fr = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        ArrayList<Rating> list = r.getSimilarRatingsByFilter("120", 10, 2, fr);
        System.out.println("There are " + list.size() + " movies recommended!");
        for(Rating id: list){
            System.out.println(MovieDatabase.getTitle(id.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings r = new FourthRatings();
        AllFilters fr = new AllFilters();
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80, 160);
        fr.addFilter(f1);
        fr.addFilter(f2);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        ArrayList<Rating> list = r.getSimilarRatingsByFilter("168", 10, 3, fr);
        System.out.println("There are " + list.size() + " movies recommended!");
        for(Rating id: list){
            System.out.println(MovieDatabase.getTitle(id.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings r = new FourthRatings();
        AllFilters fr = new AllFilters();
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70, 200);
        fr.addFilter(f1);
        fr.addFilter(f2);
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("There " + MovieDatabase.size()+ " movies.");
        System.out.println("There " + RaterDatabase.size() + " raters.");
        ArrayList<Rating> list = r.getSimilarRatingsByFilter("314", 10, 5, fr);
        System.out.println("There are " + list.size() + " movies recommended!");
        for(Rating id: list){
            System.out.println(MovieDatabase.getTitle(id.getItem()));
        }
    }
}
