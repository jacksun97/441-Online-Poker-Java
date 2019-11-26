import java.util.ArrayList;
import java.util.List;

public class Board{
    private ArrayList<Cards> board;
    Deck deck;

    public Board(Deck deck){

        this.deck = deck;
        board = new ArrayList<Cards>();
    }

    public void firstthree(){
        int i = 0;
        
        for(Cards card: deck.getdeck()){
            if(i == 3){
                break;
            }
            board.add(card);
            i++;
        }
        deck.removehowmany(3);
        

    }
    public void turncard(){
        int i = 0;
        deck.removehowmany(1);
        for(Cards card: deck.getdeck()){
            if(i == 1){
                break;
            }
            board.add(card);
            i++;
        }
        deck.removehowmany(1);
        
    }
    public void rivercard(){
        int i = 0;
        deck.removehowmany(1);
        for(Cards card: deck.getdeck()){
            if(i == 1){
                break;
            }
            board.add(card);
            i++;
        }
        deck.removehowmany(1);

    }
    public List printboard(){
        List newboard = new ArrayList();
        String puttogether;
        for(Cards card : board){
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
            newboard.add(puttogether);
            
        }
        return newboard;
    }
    
    public ArrayList<Cards> getBoard() {
    	return this.board;
    }
}