import java.util.Deque;
import java.util.LinkedList;

public class GreedGameUtil{
  /**
  shuffles the given deck, then displays and returns the playing card of a round in Greedy War!
  Prints all other cards face down.
  @return a Card object that is the playing card of the current round.
  **/
  public static Card playHand(Deque<Card> hand){
    CardsUtil.shuffleDeck(hand);

    for(int i = 1; i < hand.size(); i++) {
    	if(i%10 == 1) System.out.println(); //FIXME what is this
    	System.out.print("X  ");
    }
    System.out.println();
    Card playingCard = hand.removeFirst();
    CardsUtil.printCard(playingCard);
    return playingCard;
  }

  /**
  removes the first 4 cards of the given deck, adds them into a list 
  of cards i.e. the war cards hand, then displays the playing card and returns the list.  
  The last war card is the playing card.
	@return a Deque of the four war Card objects.
  **/
  public static Deque<Card> playWarHand(Deque<Card> hand){
    Deque <Card> warHand = new LinkedList <Card>();
    if (hand.size() >= 4) {
      for (int i = 0; i < 4; i++) {
      warHand.addFirst(hand.removeFirst());
      }
      System.out.println("X  X  X...");
      CardsUtil.printCard(warHand.peekFirst());
    }
    else {
      for (int i = 1; i < hand.size(); i++){
        System.out.print("X  ");
      }
      while (hand.size() > 0) {
        warHand.addFirst(hand.removeFirst());
      }
      System.out.println();
      CardsUtil.printCard(warHand.peekFirst());
    }

    return warHand;

  }
}