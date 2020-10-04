
/**
 * Write a description of PhraseFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PhraseFilter implements Filter{
    private String type;
    private String phrase;
    
    public PhraseFilter(String t, String p){
        type = t;
        phrase = p;
    }
    
    public boolean satisfies(QuakeEntry qe){
        String title = qe.getInfo();
        if(type.equals("start")){
             if(title.startsWith(phrase)) return true;
        }
        if(type.equals("any")){
            if(title.contains(phrase)) return true;
        }
        if(type.equals("end")){
            if(title.endsWith(phrase)) return true;
        }
        return false;
    }
    
    public String getName(){
        return "Phrase";
    }
}
