public class Evaluate {
	
	private Player pl;
	
	public int isPair(Cards[] hand) {
		int numOfPairs = 0;
		for (int i = 0; i < hand.length - 1; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getnumber() == hand[j].getnumber()) {
					if (hand[i].getnumber() == 1) {
						pl.highCard = hand[i];
					} else if ((hand[i].getnumber() > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
						pl.highCard = hand[i];
					}
					numOfPairs++;
					break;
				}
			}
		} 
		return numOfPairs;
	}
	
	public boolean isTriple(Cards[] hand) {
		int numOfDups = 1;
		int tripVal = 0;
		for (int i = 0; i < hand.length - 2; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getnumber() == hand[j].getnumber()) {
					tripVal = hand[i].getnumber();
					numOfDups++;
				}
			}
			if (numOfDups == 3) {
				if (tripVal == 1) {
					pl.highCard = hand[i];
				} else if ((tripVal > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
					pl.highCard = hand[i];
				}
				break;
			}
			tripVal = 0;
			numOfDups = 1;
		} 
		return numOfDups == 3;
	}
	
	public boolean isStraight(Cards[] hand) {
		int numInSeq = 1;
		Cards lastCardInStr = null;
		for (int i = 0; i < 3; i++) {
			for (int j = i +1; j < hand.length; j++) {
				if (hand[j].getnumber() == hand[j - 1].getnumber()) {
					continue;
				} else if (hand[j].getnumber() == hand[j - 1].getnumber() + 1) {
					numInSeq++;
					if (numInSeq == 5) lastCardInStr = hand[j]; 
				} else {
				    break;
				}
			}
			if (numInSeq == 5) {
				if (lastCardInStr.getnumber() == 1) {
					pl.highCard = lastCardInStr;
				} else if ((lastCardInStr.getnumber() > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
					pl.highCard = lastCardInStr;
				}
				return true;
			}
			numInSeq = 1;
		}
		return false;
	}
	
	public boolean isFlush(Cards[] hand) {
		int numInFlush = 1;
		Cards highestCardinFlush = new Cards(0, "Hearts");
		for (int i = 0; i < 3; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getsuit().equals(hand[j].getsuit())) {
					numInFlush++;
					if (hand[j].getnumber() == 1) {
						highestCardinFlush = hand[j];
					} else if (hand[j].getnumber() > highestCardinFlush.getnumber() && highestCardinFlush.getnumber() != 1) {
						highestCardinFlush = hand[j];
					}
					if (numInFlush == 5) {
						pl.highCard = highestCardinFlush;
						return true;
					}
				}
			}
			numInFlush = 1;
		}
		return false;
	}
	
	public boolean isFullHouse(Cards[] hand) {
		int tripleVal = 0;
		boolean hasTriple = false;
		boolean hasPair = false;
		int numOfDups = 1;
		//check for three of a kind
		for (int i = 0; i < hand.length - 2; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getnumber() == hand[j].getnumber()) numOfDups++;
			}
			if (numOfDups == 3) {
				if (hand[i].getnumber() == 1) {
					pl.highCard = hand[i];
				} else if ((hand[i].getnumber() > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
					pl.highCard = hand[i];
				}
				tripleVal = hand[i].getnumber();
				hasTriple = true;
				break;
			}
			numOfDups = 1;
		}
		if (hasTriple) {
			//check for pair of a different value
			for (int i = 0; i < hand.length - 1; i++) {
				for (int j = i + 1; j < hand.length; j++) {
					if (hand[i].getnumber() == hand[j].getnumber() && hand[i].getnumber() != tripleVal) {
						hasPair = true;
						break;
					}
				}
				if (hasPair) break;
			} 
		}
		return hasPair && hasTriple;
	}
	
	public boolean isQuad(Cards[] hand) {
		int numOfDups = 1;
		for (int i = 0; i < hand.length - 1; i++) {
			for (int j = i + 1; j < hand.length; j++) {
				if (hand[i].getnumber() == hand[j].getnumber()) numOfDups++;
			}
			if (numOfDups == 4)  {
				if (hand[i].getnumber() == 1) {
					pl.highCard = hand[i];
				} else if ((hand[i].getnumber() > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
					pl.highCard = hand[i];
				}
				break;
			}
			numOfDups = 1;
		} 
		return numOfDups == 4;
	}
	
	public boolean isStrFlush(Cards[] hand) {
		int numInSeq = 1;
		Cards lastCardInStr = null;
		for (int i = 0; i < 3; i++) {
			for (int j = i +1; j < hand.length; j++) {
				if (hand[j].getnumber() == hand[j - 1].getnumber()) {
					continue;
				} else if (hand[j].getnumber() == hand[j - 1].getnumber() + 1) {
					if (hand[i].getsuit().equals(hand[j].getsuit()))  {
						numInSeq++;
						if (numInSeq == 5) lastCardInStr = hand[j];
					}
				}
			}
			if (numInSeq == 5) {
				if (lastCardInStr.getnumber() == 1) {
					pl.highCard = lastCardInStr;
				} else if ((lastCardInStr.getnumber() > pl.highCard.getnumber()) && pl.highCard.getnumber() != 1) {
					pl.highCard = lastCardInStr;
				}
				return true;
			}
			numInSeq = 1;
		}
		return false;
	}
	
	public boolean isRoyalFlush(Cards[] hand) {
		String suit = "";
		int numInSeq = 2;
		if (hand[0].getnumber() == 1) {
			suit = hand[0].getsuit();
			if (hand[hand.length - 1].getnumber() == 13 && hand[hand.length - 1].getsuit().equals(suit)) {
				for (int i = hand.length - 2; i > 0; i--) {
					if (hand[i].getnumber() == hand[i + 1].getnumber()) {
						continue;
					} else if (hand[i].getnumber() == hand[i + 1].getnumber() - 1){ 
						if (suit.equals(hand[i].getsuit())) numInSeq++;
					}
					if (numInSeq == 5) return true;
				}
			}
			
		}
		return false;
	}
	
	
	public String getHighCard(Cards[] hand) {
		int hcVal = hand[0].getnumber();
		String hc = hcVal + hand[0].getsuit();
		
		for (int i = 1; i < hand.length; i++) {
			if (hand[i].getnumber() > hcVal) {
				hcVal = hand[i].getnumber();
				hc = hcVal + hand[i].getsuit();
			}
		}
		
		return hc;
	}
	public void evalHand(Cards[] hand, Player pl) {
		this.pl = pl;
		int numOfPairs = 0;
		if (isRoyalFlush(hand)) {
			pl.handType = "Royal Flush";
			pl.handVal = 10;
		} else if (isStrFlush(hand)) {
			pl.handType = "Straight flush";
			pl.handVal = 9;
		} else if (isQuad(hand)) {
			pl.handType = "Four of a Kind";
			pl.handVal = 8;
		} else if (isFullHouse(hand)) {
			pl.handType = "Full House";
			pl.handVal = 7;
		} else if (isFlush(hand)) {
			pl.handType = "Flush";
			pl.handVal = 6;
		} else if (isStraight(hand)) {
			pl.handType = "Straight";
			pl.handVal = 5;
		} else  if (isTriple(hand)) {
			pl.handType = "Three of a Kind";
			pl.handVal = 4;
		} else if ((numOfPairs = isPair(hand)) > 0) {
			if (numOfPairs == 2) {
				pl.handType = "Two Pair";
				pl.handVal = 3;
			} else {
				pl.handType = "Pair";
				pl.handVal = 2;
			}
		} else {
			pl.handType = "High card: " + getHighCard(hand);
			pl.handVal = 1;
		}
	}
	
	public static void main(String args[]) {
		Evaluate eval = new Evaluate();
		Cards[] hand = new Cards[7];
		hand[0] = new Cards(1, "Hearts");
		hand[1] = new Cards(9, "Hearts");
		hand[2] = new Cards(11, "Spades");
		hand[3] = new Cards(11, "Hearts");
		hand[4] = new Cards(12, "Spades");
		hand[5] = new Cards(12, "Hearts");
		hand[6] = new Cards(13, "Hearts");
		Player pl = new Player("John", 500, null);
		eval.evalHand(hand, pl);
		System.out.println(pl.handType);
	}
	
	
}