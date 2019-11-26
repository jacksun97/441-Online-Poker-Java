import java.util.Scanner;
import java.util.ArrayList;
public class Poker {

    private static int pot = 0;
    private static int currBet = 0;
    private static boolean gameWon = false;

	public static void main(String[] args) {
        
        Scanner reader = new Scanner(System.in);
        
        Poker game = new Poker();
        Deck allcards = new Deck();
        Board board = new Board(allcards);
        Player player1 = new Player("Dheeraj", 500, allcards);
        Player player2 = new Player("John", 500, allcards);
        ArrayList<Player> currPlayers = new ArrayList<Player>();
        currPlayers.add(player1);
        currPlayers.add(player2);
        System.out.println("Welcome to poker");
        allcards.builddeck();
        allcards.shuffledeck();
        String choice;
        while (true){
        	for (Player player : currPlayers) player.addcards();
        	
            game.playerTurn(reader, currPlayers, board, game);
            if (gameWon) break;
            currBet = 0;

            board.firstthree();

            game.playerTurn(reader, currPlayers, board, game);
            if (gameWon) break;
            currBet = 0;
            
            board.turncard();
            
            
            game.playerTurn(reader, currPlayers, board, game);
            if (gameWon) break;
            currBet = 0;
            
            board.rivercard();
            
            game.playerTurn(reader, currPlayers, board, game);
            if (gameWon) break;
          
            game.determineWinner(currPlayers, board);
            
            choice = reader.nextLine();
            if(choice.equals("q")){
                break;
            }
        }        

    }
	
	//server side
	private void playerTurn(Scanner reader, ArrayList<Player> players, Board board, Poker game) {
		Calls acall;
		int raisingvalue;
		String choice = "";
		String callResult;
		Player player = null;
		boolean correctInput = false;
		boolean betRoundOver = false;
		int pl = 0;
		int lastPl = players.size() - 1;
		while (!betRoundOver) {
			player = players.get(pl % players.size());
			System.out.println("\nIt is " + player.name + "'s turn"); //send this to every one (special message to player whos turn it is)
			while (!correctInput) {
				//send this info to each player
				System.out.println("\nBoard: " + board.printboard());//send whenever new card revealed
		    	System.out.print("\nYour hand is: " + player.getHand());//send only when game starts
		    	System.out.println("\t You have $" + player.howmuchworth);//send after every action by the player
		    	System.out.print("\nCurrent pot is $" + pot);//send whenever something is added to pot
		    	System.out.println("\t Current bet is $" + currBet);//send whenever another player raises
		    	whatdo();
		        choice = reader.nextLine(); 
		        raisingvalue = todo(choice, reader); //check if they have enough to raise on client side
		        acall = new Calls(player.getvalue(), raisingvalue);
		        callResult = acall.allcalls(choice, player, game);
		        if (!callResult.equals("try again wrong input")) correctInput = true;
		        System.out.println(callResult);
		        if (acall.getiffold()) players.remove(player);
			}
			correctInput = false;	
			if (players.size() == 1) {
				win(players.get(0));
				break;
			}
			if (((pl % players.size()) == lastPl) && choice.toLowerCase().equals("raise")) {
				lastPl = (pl + 1) % players.size();
			} else if (pl % players.size() == lastPl){
				betRoundOver = true;
			}
			pl++;
		}
	}
	
	//server side
	public void determineWinner(ArrayList<Player> players, Board board) {
		Evaluate eval = new Evaluate();
		Cards[] handArr;
		
		for (Player pl : players) {
			pl.hand.addAll(board.getBoard());
			pl.hand.sort(null);
			handArr = (Cards[]) pl.handToArray();
			eval.evalHand(handArr, pl);
		}
		Player winningPl = null;
		Player pl = null;
		int winningHand = 0;
		ArrayList<Player> tiedPlayers = new ArrayList<Player>();
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).handVal == 1) {
				winningHand = players.get(i).handVal;
				winningPl = players.get(i);
			} else if (players.get(i).handVal > winningHand) {
				winningHand = players.get(i).handVal;
				winningPl = players.get(i);
			} else if (players.get(i).handVal == winningHand) {
				tiedPlayers.add(players.get(i));
			}
		}
		
		//server side
		ArrayList<Player> secondaryTie = new ArrayList<Player>();
		if (tiedPlayers.size() > 0) {
			secondaryTie.add(tiedPlayers.get(0));
			Cards highCard = secondaryTie.get(0).highCard;
			for (int i = 0; i < tiedPlayers.size(); i ++) {
				if (players.get(i).handVal == 1) {
					winningHand = tiedPlayers.get(i).handVal;
					winningPl = tiedPlayers.get(i);
				} else if (tiedPlayers.get(i).highCard.getnumber() == highCard.getnumber()) {
					secondaryTie.add(tiedPlayers.get(i));
				} else if (tiedPlayers.get(i).highCard.getnumber() > highCard.getnumber()){
					highCard = tiedPlayers.get(i).highCard;
					winningPl = tiedPlayers.get(i);
				}
				if (secondaryTie.size() > 0) {
					splitPot(secondaryTie);
				} else {
					win(winningPl);
				}
			}
		} else {
			win(winningPl);
		}
		
		
	}
	
	public void splitPot(ArrayList<Player> players) {
		int winnings = pot / players.size();
		System.out.println("\nThe following players have tied with a " + players.get(0).highCard.cardToString());
		System.out.println("They split the pot between them.");
		for (Player player : players) {
			System.out.println(player.name);
		}
		gameWon = true;
	}
	
	//server side (send this message to all players )
	public void win(Player player) {
		player.howmuchworth += pot;
		System.out.println("\n" + player.name + " won the pot with a " + player.handType);
		System.out.println(player.name + "'s hand: " + player.getHand()); //maybe make method that tells what everyone else's hand was
		System.out.println(player.name + "'s money: $" + player.howmuchworth);
		gameWon = true;
	}
	
	//client side
    public void whatdo(){
    	System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("Raise, Call, Check, Fold?");
        System.out.print(": ");
    }
    
    //client side
    public int todo(String choice, Scanner reader){
        int amount;
        
        if(choice.toLowerCase().equals("raise")){
            System.out.println("How much would you like to raise by, no decimals");
            amount = Integer.parseInt(reader.nextLine()); 
            return amount;
        }
        else if (choice.toLowerCase().equals("call")){
            return getCurrBet();
        }
        else {
        	return 0;
        }
    }
    
    public int getPot() {
		return pot;
	}

	public void raisePot(int amount) {
		this.pot += amount;
	}

	public int getCurrBet() {
		return currBet;
	}

	public void raiseCurrBet(int amount) {
		this.currBet += amount;
	}
    

}
