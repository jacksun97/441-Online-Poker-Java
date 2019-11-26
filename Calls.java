import java.util.Scanner;




public class Calls{
    private int ihave;
    
    private Scanner reader;
    private Boolean iffold;
    private int howmuchraise;
    
    public Calls(int howmuchihave, int howmuchraise){
        this.ihave = howmuchihave;
        this.iffold = false;
        this.howmuchraise = howmuchraise;
        reader = new Scanner(System.in);
    }


    public String allcalls(String whatcanido, Player player, Poker game){
        if(whatcanido.toLowerCase().equals("raise")){
            if(checkificanraise(howmuchraise + game.getCurrBet())){
            	player.howmuchworth -= howmuchraise + game.getCurrBet();
            	game.raisePot(howmuchraise);
            	game.raiseCurrBet(howmuchraise);
                return ("You raised this much :" + howmuchraise + " This is how much you have left: $" + player.howmuchworth);
            }
            else{
                return ("You do not have enough money to raise");
            }
        }
        else if(whatcanido.toLowerCase().equals("call")){
            canicall();
            player.howmuchworth -= howmuchraise;
            game.raisePot(howmuchraise);
            return("You have called the bet " + howmuchraise + " and you have this much left: $" + ihave);
            
        }
        else if(whatcanido.toLowerCase().equals("fold")){
            iffold = true;
            return ("You have folded your cards");

        }
        else if(whatcanido.toLowerCase().equals("allin")){
            ihave = 0;
            return ("You have went all in");
        }
        else if(whatcanido.toLowerCase().contentEquals("check")){
        	return ("You checked");
        }
        else{
            return ("try again wrong input");
        }
    }

    public Boolean checkificanraise(int raisedvalue){
        if (raisedvalue <= ihave){
            ihave = ihave - raisedvalue;
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean getiffold(){
        return iffold;
    }

    public void canicall(){
        if(howmuchraise >= ihave){
            ihave = 0;
        }
        else{
            ihave = ihave - howmuchraise;
        }
    }
}