import java.util.ArrayList;
import java.util.Collections;


public class Deck{
    private ArrayList<Cards> deck;

    public Deck(){
        deck = new ArrayList<Cards>();
    }

    
    public void builddeck(){        
        ArrayList<String> allsuits = new ArrayList<String>();
        allsuits.add("Hearts");
        allsuits.add("Spades");
        allsuits.add("Diamonds");
        allsuits.add("Clubs");
        for(String j: allsuits){        
            for(int i = 1; i < 14; i++){
                Cards newcard = new Cards(i,j);
                deck.add(newcard);
            }
        }
    }

    
    public void printdeck(){
        int i = 0;
        for(Cards card : deck){
            System.out.println(card.getnumber() + " " + card.getsuit());
            
            i++;
        }
        System.out.println(i);
    }


    public void shuffledeck(){
        Collections.shuffle(deck);

        
    }


    public void removecard(Cards card){
        int i = 0;
        for(Cards acard: deck){
            if((acard.getnumber() == card.getnumber()) && (acard.getsuit().equals(card.getsuit()))){
                break;
            }
            i++;
        }
        deck.remove(i);
    }
    public void removehowmany(int i){
        int j = 0;
        while(j < i){
            deck.remove(0);
            j++;
        }
    }
    public ArrayList<Cards> getdeck(){
        return deck;
    }



}