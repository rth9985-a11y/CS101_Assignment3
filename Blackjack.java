package edu.nyu.cs.assignment3;

import java.util.Scanner;
import java.util.Random;
import java.util.Vector;

public class Blackjack {
	
    public static Random initRandom(String[] args) {
        if (args.length >= 1) {
            return new Random(Long.parseLong(args[0]));
        } else {
            return new Random();
        }
        
    }
    
    public static String showCards(Vector<Integer> vec) {
    	
    	String vectorString = "";
    	
    	for (int i = 0; i < vec.size(); i++) {
    		vectorString += vec.get(i);
    		if (i < vec.size() - 1) {
    			vectorString += ", ";
    		}
    	}
    	return vectorString;
    }
    
    /*
     * NAME: sumOfVector
     * ARGS: Vector (dealer or player)
     * RETURN: int
     * FUNCTION: Adds all values in a vector and returns the sum
     */
    
    public static int sumVector(Vector<Integer> vec) {
    	int sum = 0;
    	for (int i = 0; i < vec.size(); i++) {
    		sum += vec.get(i);
    	}
    	return sum;
    }
    
    /*
     * @param dealers cards vector
     * @param random number generator
     * @return void
     * 
     * FUNTION: Play the dealers hand by using a random boolean generator (print this out)
     * 			if true --> add new random card to dealer's hand
     * 				Check if busted --> if yes, go to end game
     * 			if false -->  go to end game
     */
    
    public static void dealerTurn(Random r, Vector<Integer> dealer, Vector<Integer> player) {
    	while (true) {
    		
    		if (sumVector(dealer) > 21) {					// if dealer busts
				System.out.println("The dealer has bust!");	
				endGame(player, dealer);					// go to end game to terminate
				return;
    		}
    		
    		boolean dealerChoice = r.nextBoolean();				// Generates random boolean
  
    		if (dealerChoice) {									// if true (dealer hits)
    			System.out.println("The dealer hits.");			
    			dealer.add(r.nextInt(10) + 2);
    		}   	
    		
    		else {												// dealer stands
    			System.out.println("The dealer stands.");		
    			endGame(player, dealer);						// go to end game to determine winner
    			return;
    		}
    		
    	}
    }

    /*
     * @param dealer (Vector)
     * @param player (Vector)
     * @return void
     * 
     * FUNCTION: Show both players cards, announce winner using conditionals, 
     */
    
    public static void endGame(Vector<Integer> player, Vector<Integer> dealer) {		
    	
    	System.out.println("Your cards are: " + showCards(player)); 				// Show both players cards
    	System.out.println("The dealer's cards are: " + showCards(dealer)); 
    	
    	if (sumVector(dealer) > sumVector(player) && sumVector(dealer) <= 21) {		//Announce winner 
    		System.out.println("Dealer wins!");
    	}
    	else if (sumVector(player) > sumVector(dealer) && sumVector(player) <= 21) {
    		System.out.println("You win!");
    	}
    	else if (sumVector(player) == sumVector(dealer)) {
    		System.out.println("Tie!");
    	}
    	else if (sumVector(player) > 21) {
    		System.out.println("Dealer wins!");
    	}
    	else if (sumVector(dealer) > 21) {
    		System.out.println("You win!");
    	}
    }
    
    public static void main(String[] args) {
    	Random r = initRandom(args);
    	Scanner scn = new Scanner(System.in);
    	
    	Vector<Integer> player = new Vector<>();
    	Vector<Integer> dealer = new Vector<>();
    	
    	System.out.println("Welcome to Blackjack!");		//FORMAT
    	
    	player.add(r.nextInt(10) + 2);						// Give the user two cards
    	player.add(r.nextInt(10) + 2);
    	dealer.add(r.nextInt(10) + 2);						// Give the dealer two cards
    	dealer.add(r.nextInt(10) + 2);
    	
    	while (true) {
    		System.out.println("Your cards are: " + showCards(player));		// Show the user their cards
    		System.out.println("Would you like to hit or stand?");			//Ask user to "hit" or "stand"
    		String hitStay = scn.nextLine();
    		
    		if (hitStay.equals("stand")) {					// If user stands break from while loop and go to dealers turn
    	    	break;
    		}
    		
    		else if (hitStay.equals("hit")) {				// If user hits
    			player.add((r.nextInt(10) + 2));			// Add a new card to their hand
    			if (sumVector(player) > 21) {				// if user busts do this 
    				System.out.println("You have bust!");
    				endGame(player, dealer);				// Go to end game
    				return;
    			}
    		}  	
    		
    	} // End user while loop
    
		dealerTurn(r, dealer, player);	

    	
    	
    } // End main
    
    

    
}

