
/**
 * Write a description of RecommandationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class RecommendationRunner implements Recommender{
    Random random = new Random();
    
    public ArrayList<String> getItemsToRate (){
        //get current year
        int year = 2010;
        YearAfterFilter fil = new YearAfterFilter(year);
        ArrayList<String> movies = MovieDatabase.filterBy(fil);
        if(movies.size() <= 10) return movies;
        //make sure the list is less than 20 movies
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < 10; i++){
            int index = random.nextInt(movies.size() - 1);
            list.add(movies.get(index));
        }
        return list;
    }
    
    public void printRecommendationsFor (String webRaterID){
        FourthRatings r = new FourthRatings();
        ArrayList<Rating> average = r.getAverageRatings(10);
        ArrayList<String> same = new ArrayList<>();
        for(Rating rr: average){
            if(RaterDatabase.getRater(webRaterID).hasRating(rr.getItem())){
                same.add(rr.getItem());
            }
        }
        Collections.sort(average);
        ArrayList<Rating> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(average.get(i));
        }
        if(list.size() == 0){
            System.out.println("<br> There is no any movie could recommend to you! </br>");
        }
        for(Rating rate : list){
            if(same.contains(rate.getItem())) continue;
            System.out.println("<br>Title: " + MovieDatabase.getTitle(rate.getItem()) + ", Genre: " + MovieDatabase.getGenres(rate.getItem()) + "</br>");
            System.out.println("<br><img src =\"" + MovieDatabase.getPoster(rate.getItem()) + "\"></br>");
        }
    }
}
