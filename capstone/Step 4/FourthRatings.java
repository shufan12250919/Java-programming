
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters) {
        int people = 0;
        double total = 0.0;
        for(Rater rr : RaterDatabase.getRaters()) {
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
    
    private double dotProduct(Rater me, Rater r){
        double weight = 0.0;
        for(String id : me.getItemsRated()){
            if(r.hasRating(id)){
                weight += (me.getRating(id) - 5)*(r.getRating(id) -5);
            }
        }
        return weight;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()){
            if(!r.getID().equals(id)){
                double sim = dotProduct(me, r);
                if(sim > 0){
                    Rating rate = new Rating(r.getID(), sim);
                    list.add(rate);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> average = new ArrayList<Rating>();
        
        ArrayList<Rating> temp = getSimilarities(id);
        //System.out.println(temp.size());
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        for(String mid : movies){
            int people = 0;
            double total = 0.0;
            for(int i = 0; i < numSimilarRaters; i++){
                Rater cur = RaterDatabase.getRater(temp.get(i).getItem());
                if(cur.hasRating(mid)){
                    double score = temp.get(i).getValue() * cur.getRating(mid);
                    total += score;
                    people++;
                }
            }
            if(people >= minimalRaters){
                average.add(new Rating(mid, total / people));
            }    
        }
        Collections.sort(average, Collections.reverseOrder());
        return average;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter f){
        ArrayList<Rating> average = new ArrayList<Rating>();
        
        ArrayList<Rating> temp = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(f);
        //System.out.println(movies.size());
        
        for(String mid : movies){
            int people = 0;
            double total = 0.0;
            for(int i = 0; i < numSimilarRaters; i++){
                Rater cur = RaterDatabase.getRater(temp.get(i).getItem());
                if(cur.hasRating(mid)){
                    double score = temp.get(i).getValue() * cur.getRating(mid);
                    total += score;
                    people++;
                }
            }
            if(people >= minimalRaters){
                average.add(new Rating(mid, total / people));
            }    
        }
        Collections.sort(average, Collections.reverseOrder());
        return average;
    }
}
