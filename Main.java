import java.util.*;


class Main {

	public static void printMenu() {
		System.out.println("\n\nWelcome to the Main Menu");
		System.out.println("0: Exit");
		System.out.println("1: Test CardsUtil Functions");
		System.out.println("2: Play Greedy War!");
		System.out.print("\nYour selection: ");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
		int mainSelection;

		do {
			printMenu();

			mainSelection = Integer.parseInt(in.nextLine().trim());
			if (mainSelection == 1) {
				CardsUtil.testCardsUtil();
				System.out.println();
			} else if (mainSelection == 2) {
				// creating the standard deck
				LinkedList<Card> deck = CardsUtil.createStandardDeck();
        
        // dealing the cards (dealCards shuffles before dealing)
        LinkedList<Card> computerHand = (LinkedList<Card>) CardsUtil.dealCards(deck, 26);
        LinkedList<Card> userHand = (LinkedList<Card>) CardsUtil.dealCards(deck, 26);
        String playAnother = "y";
        int roundCount = 1;
        do{
          System.out.println("========================= Round #" + roundCount +  " =========================");
          System.out.println("Computer Hand:");
          Card compPlay = GreedGameUtil.playHand(computerHand);
          System.out.println("Your Hand:");
          Card userPlay = GreedGameUtil.playHand(userHand);

          //comparing ranks during match
          if (compPlay.getRank() == 1 && userPlay.getRank() != 1){
            System.out.println("You've got no chance against the " + compPlay + ". You LOSE this round!!!");
            computerHand.addFirst(compPlay);
            computerHand.addFirst(userPlay);
          } 
          //user has ace but computer does not
          else if (compPlay.getRank() != 1 && userPlay.getRank() == 1) {
            System.out.println("You WIN this round!!!!!!!!");
            userHand.addFirst(compPlay);
            userHand.addFirst(userPlay);
            
          } //computer has stronger hand
          else if (compPlay.getRank() > userPlay.getRank()){
            System.out.println("You're not as strong as the " + compPlay + ". \nDo you want to play greedy? Y/N:");
            String response = input.nextLine();
            while (!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")) {
              System.out.println("Invalid input. Please enter Y/N: ");
              response = in.nextLine();
            }

            if(response.equals("y")){ //user chooses to be greedy
              System.out.println("Your GREED Hand:");
              Card userGreed = GreedGameUtil.playHand(userHand);
              if (userGreed.getRank() + userPlay.getRank() <= compPlay.getRank()){
                int sum = userGreed.getRank() + userPlay.getRank();
                System.out.println("The sum of your ranks: " + sum);
                System.out.println("You were greedy but it payed off. YOU WIN!!!");
                userHand.addFirst(userGreed);
                userHand.addFirst(compPlay);
                userHand.addFirst(userPlay);
              }
              else {
                System.out.println("You were too greedy. YOU LOSE!!!");
                computerHand.addFirst(userGreed);
                computerHand.addFirst(compPlay);
                computerHand.addFirst(userPlay);      
              }
            }
            else if (response.equals("n")){ //user does not choose greedy
              System.out.println("You accept defeat...");
              computerHand.addFirst(compPlay);
              computerHand.addFirst(userPlay);
            }
          }
          else if (compPlay.getRank() < userPlay.getRank()) {
            System.out.println("You WIN this round!!!!!!!!");
            userHand.addFirst(compPlay);
            userHand.addFirst(userPlay);
          }
          else { //war round
            System.out.println("This means WAR!!!");
            System.out.println("Computer's WAR hand:");
            Deque<Card> compWar = GreedGameUtil.playWarHand(computerHand);

            System.out.println("Your WAR hand:");
            Deque<Card> userWar = GreedGameUtil.playWarHand(userHand);

            //computer has an ace
            if (compWar.peekFirst().getRank() == 1) {
              System.out.println("\n\nYou LOST THE WAR round.");
              computerHand.addFirst(compPlay);
              computerHand.addFirst(userPlay);
              CardsUtil.addCardsTo(computerHand, compWar);
              CardsUtil.addCardsTo(computerHand, userWar);
            }

            //user has an ace
            else if (userWar.peekFirst().getRank() == 1) {
              System.out.println("\n\nYou WON THE WAR round.");
              userHand.addFirst(compPlay);
              userHand.addFirst(userPlay);
              CardsUtil.addCardsTo(userHand, compWar);
              CardsUtil.addCardsTo(userHand, userWar);
            }

            //computer has stronger war hand
            else if (compWar.peekFirst().getRank() > userWar.peekFirst().getRank()){
              System.out.println("\n\nYou LOST THE WAR round.");
              computerHand.addFirst(compPlay);
              computerHand.addFirst(userPlay);
              CardsUtil.addCardsTo(computerHand, compWar);
              CardsUtil.addCardsTo(computerHand, userWar);
            }

            //user has stronger war hand or war ends in tie
            else if (compWar.peekFirst().getRank() <= userWar.peekFirst().getRank()) {
              System.out.println("\nYou WON THE WAR round.");
              userHand.addFirst(compPlay);
              userHand.addFirst(userPlay);
              CardsUtil.addCardsTo(userHand, compWar);
              CardsUtil.addCardsTo(userHand, userWar);
            }
          }
          
          System.out.println("\nYour deck size: " + userHand.size());
          System.out.println("Computer's deck size: " + computerHand.size());
          roundCount++;
          
          //prompt for next round if both players still have cards
          if (userHand.size() > 0 && computerHand.size() > 0) {
            System.out.println("\nWould you like to play another round? Y/N: ");
            playAnother = in.nextLine();
            while (!playAnother.equalsIgnoreCase("y") && !playAnother.equalsIgnoreCase("n")) {
              System.out.println("Invalid input. Please enter Y/N: ");
              playAnother = in.nextLine();
            }
          }
          else { //one player has 0 cards
            if (userHand.size() > computerHand.size()) {
              System.out.println("CONGRATS! You won Greedy War!!!!");
            }
            else {
              System.out.println("You lost all your cards. Better luck next time");
            }
          }
        } while (!playAnother.equalsIgnoreCase("n") && userHand.size() > 0 && computerHand.size() > 0);
        

			} else if (mainSelection != 0){ 
        
				System.out.println("Your selection is invalid.  You will be taken back to the main menu.");
			}

		} while (mainSelection != 0);

		in.close();

	}
}