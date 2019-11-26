import java.util.ArrayList;
import java.util.List;



public class Player{
    String name;
    int howmuchworth;
    ArrayList<Cards> hand;
    Deck deck;
    String handType;
    Cards highCard = new Cards(0, "Hearts");
    int handVal;


    public Player(String name, int howmuchworth, Deck deck){
        this.name = name;
        this.howmuchworth = howmuchworth;
        hand = new ArrayList<Cards>();
        this.deck = deck;
    }

    public int getvalue(){
        return howmuchworth;
    }

    public List getHand(){
        List realhand = new ArrayList();
        String puttogether;
        for(Cards card : hand){
        	if (card.getnumber() == 1) {
        		puttogether = "Ace";
        	} else if (card.getnumber() == 11) {
        		puttogether = "Jack";
        	} else if (card.getnumber() == 12) {
        		puttogether = "Queen";
        	} else if (card.getnumber() == 13) {
        		puttogether = "King";
        	} else {
        		puttogether = Integer.toString(card.getnumber());
        	}
            puttogether = puttogether + " of " + card.getsuit();
            realhand.add(puttogether);

        }

        return realhand;
    }
    
    public Cards[] handToArray() {
    	Cards[] handArr = new Cards[hand.size()];
    	for (int i = 0; i < hand.size(); i++) {
    		handArr[i] = hand.get(i);
    	}
    	return handArr;
    }

    public void addcards(){
        int i = 0;
        for(Cards card: deck.getdeck()){
            if(i< 2){
                hand.add(card);
            }
            i++;
        }
        deck.removehowmany(2);
    }




}