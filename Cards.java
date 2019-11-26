import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Cards implements Comparable{
    private int cardnumber;
    private String suit;
    private ArrayList<Cards> deck;
    
    public Cards(int cardnumber, String suit){
        this.cardnumber = cardnumber;
        this.suit= suit;
    }

    public int getnumber(){
        return cardnumber;

    }
    public String getsuit(){
        return suit;
    }
    
    public String cardToString() {
    	String cardAsString = "";
    	if (getnumber() == 1) {
    		cardAsString = "Ace";
    	} else if (getnumber() == 11) {
    		cardAsString = "Jack";
    	} else if (getnumber() == 12) {
    		cardAsString = "Queen";
    	} else if (getnumber() == 13) {
    		cardAsString = "King";
    	} else {
    		cardAsString = Integer.toString(getnumber());
    	}
        cardAsString = cardAsString + " of " + getsuit();
    	return cardAsString;
    }
    
    // for sorting the cards
	@Override
	public int compareTo(Object compareCard) {
		int compareNum = ((Cards) compareCard).getnumber();
		return this.cardnumber - compareNum;
	}
 
}