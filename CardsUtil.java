import java.util.Deque;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

public class CardsUtil {

  /**
  prints a formatted Card
  @param c - the card to print
  **/
  public static void printCard(Card c){
      String blankCard = "";
      if(c.getRank() == 10){
        blankCard =        "┌─────────┐\n" +
                           "│%s       │\n" +
                           "│         │\n" +
                           "│         │\n" +
                           "│    %s    │\n" +
                           "│         │\n" +
                           "│         │\n" +
                           "│       %s│\n" +
                           "└─────────┘\n";
      }
      else{
        blankCard =        "┌─────────┐\n" +
                           "│%s        │\n" +
                           "│         │\n" +
                           "│         │\n" +
                           "│    %s    │\n" +
                           "│         │\n" +
                           "│         │\n" +
                           "│        %s│\n" +
                           "└─────────┘\n";
      }
      
    
    String card = c.toString();
    String rankSymbol = card.substring(1) ;
    char suitSymbol = card.charAt(0);
		
    System.out.printf(blankCard, rankSymbol, suitSymbol, rankSymbol); 
    System.out.println();

  }

	

  /**
  shuffles a given deck 7 times
  @param deck - the array list of Card objects that must be shuffled
  **/
  public static void shuffleDeck(Deque<Card> deck){
    //HINT: Google the Collections library 

    ArrayList<Card> deckList = new ArrayList<Card>();
    for (Card i : deck) {
      deckList.add(i);
    }
    for (int i = 0; i < 7; i++){
      Collections.shuffle(deckList);
    }

    deck.clear();
    for (Card k : deckList) {
      deck.add(k);
    }
  }

  /**
  shuffles the deck 7 times, then deals the given amount of cards.  The cards must be removed from the top of the deck and added to the top of the hand of cards that will be returned.
  @param deck - the Collection of Cards from which to deal
  @param numCards - an integer specifying the number of cards to deal from the deck
  @return  a list or deque of Card objects of size numCards
  **/
	public static Deque<Card> dealCards(Deque<Card> deck, int numCards) {
		shuffleDeck(deck);
    Deque<Card> hand = new LinkedList<Card>();
    for (int i = 1; i < numCards + 1; i++){
      hand.addFirst(deck.removeFirst());
    }
		return hand;
	}
	
  /**
  adds the cards in the given hand to the bottom of the given deck.  Cards in the hand are added in the order top-to-bottom.
  @param deck - the deck of cards that will be added cards 
  @param hand - the hand of cards that will be added to the bottom of the deck
  **/
  public static void addCardsTo(Deque<Card> deck, Deque<Card> hand){
    int num = hand.size();
    for (int i = 0; i < num; i++){
      deck.addLast(hand.removeFirst());
    }
  }

  /**
  creates a standard deck of 52 cards with suits clubs, diamonds, hearts, and spades
  @return a linked list of 52 Card objects
  **/
	public static LinkedList<Card> createStandardDeck(){
    Suits[] allSuits = {Suits.CLUBS, Suits.DIAMONDS, Suits.HEARTS, Suits.SPADES };
		LinkedList<Card> deck = new LinkedList<Card>();
		
		for(Suits s : allSuits) {
			for(int i = 1; i <= 13; i++) {
				deck.add(new Card(i, s));
			}
		}
		return deck; 
	}
  //takes deck of cards and displays what cards are in deck
  public static void printHand(Deque<Card> deck){
      int i = 1;
      for(Card c : deck){
        System.out.print(c + " ");
        i++;
        if(i % 10 == 1){
          System.out.println();
        }
        
      }
  }

  //tests all above methods
  public static void testCardsUtil(){
    System.out.println("\n\n----------------Testing CardsUtil.createStandardDeck():----------------");
      //testing the function that creates the standard deck
    LinkedList<Card> newDeck = CardsUtil.createStandardDeck();
    System.out.println("\nNew deck: ");
    
    printHand(newDeck);

    System.out.println("\nNumber of cards in original deck: " + newDeck.size());
      

    System.out.println("\n\n----------------Testing CardsUtil.shuffleDeck():----------------");
    //testing the shuffling function 
    CardsUtil.shuffleDeck(newDeck);
    System.out.println("\nShuffled deck: ");
    printHand(newDeck);
    System.out.println("\nNumber of cards in original deck: " + newDeck.size());

    CardsUtil.shuffleDeck(newDeck);
    System.out.println("\nDeck Shuffled Again: ");
    printHand(newDeck);


    System.out.println("\nNumber of cards in original deck: " + newDeck.size());
      
    System.out.println("\n\n----------------Testing CardsUtil.dealCards(newDeck, 26):----------------");

    LinkedList<Card> player1Deck = (LinkedList<Card>) CardsUtil.dealCards(newDeck, 26);
    System.out.println("\nAfter dealing 26 cards to Player 1...");
    System.out.println("\nPlayer 1 deck: ");
    printHand(player1Deck);
    System.out.println("\nNumber of cards in Player 1 hand: " + player1Deck.size());
      
    System.out.println("\nCards left in main deck:");
    printHand(newDeck);

      
    System.out.println("\nNumber of cards left in original deck: " + newDeck.size());

    System.out.println("\n\n----------------Testing CardsUtil.dealCards(newDeck, 10):----------------");
    System.out.println("\nAfter dealing 10 cards from original deck to Player 2...");
    System.out.println("\nPlayer 1 deck: ");
    printHand(player1Deck);
    System.out.println("\nNumber of cards in Player 1 hand: " + player1Deck.size());

    LinkedList<Card> player2Deck = (LinkedList<Card>) CardsUtil.dealCards(newDeck, 10);
    System.out.println("\nPlayer 2 deck: ");
    printHand(player2Deck);
  
    System.out.println("\nNumber of cards in Player 2 hand: " + player2Deck.size());
      
    System.out.println("\nCards left in main deck:");
    printHand(newDeck);

    System.out.println("\nNumber of cards left in original deck: " + newDeck.size());


  
    System.out.println("\n\n----------------Testing CardsUtil.addCards(player2Deck, 5):----------------");
    CardsUtil.addCardsTo(player2Deck, CardsUtil.dealCards(newDeck, 5));

    System.out.println("\nAfter dealing 5 more cards from original deck to Player 2...");

    System.out.println("\nPlayer 1 deck: ");
    printHand(player1Deck);
    System.out.println("\nNumber of cards in Player 1 hand: " + player1Deck.size());


    System.out.println("\nPlayer 2 deck: ");
    printHand(player2Deck);
    System.out.println("\nNumber of cards in Player 2 hand: " + player2Deck.size());
      
    System.out.println("\nCards left in main deck:");
    printHand(newDeck);
    System.out.println("\nNumber of cards left in original deck: " + newDeck.size());
  }
}
