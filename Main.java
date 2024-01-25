//Importing all necessary modules
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Initializing Scanner
        Scanner sc = new Scanner(System.in);

        //Introduction message and name capture
        System.out.println("Welcome to the money converter! What is your name?");
        String name = sc.nextLine();

        //Establishing boolean used to continue the do-while loop
        boolean will_continue;

        do {
            //Retrieving dollar amount for exchange
            System.out.println("Hi " + name + ", how much money would you like to convert? ex: 4.55");
            double dollar_amt = sc.nextDouble() * 100;

            //Array will be used in a for loop to calculate the amount of each coin
            List<Integer> coin_values = new ArrayList<>();
            coin_values.add(25); //quarters
            coin_values.add(10); //dimes
            coin_values.add(5); //nickles
            coin_values.add(1); //pennies

            //Updating array after adding all coin values
            List<Integer> coin_amounts = new ArrayList<>();

            //Initializing this character to use to gather user decision if they want to continue converting money
            char decision;

            //For loop is used to calculate the amount of each coin type
            //Takes all the amounts and adds it to an empty array that will be displayed in the terminal
            for (Integer coinValue : coin_values) {
                if (dollar_amt >= coinValue) {
                    int remainder = (int) Math.ceil(dollar_amt % coinValue);
                    System.out.println(remainder);
                        coin_amounts.add((int) (dollar_amt - remainder) / coinValue);
                        dollar_amt = remainder;
                } else {
                    coin_amounts.add(0);
                }

            }

            //System output into terminal displaying change per coin type
            System.out.println("Here is your change!");
            System.out.println("Quarters: " + coin_amounts.getFirst());
            System.out.println("Dimes: " + coin_amounts.get(1));
            System.out.println("Nickles: " + coin_amounts.get(2));
            System.out.println("Pennies: " + coin_amounts.get(3));

            //Decision prompt to ask if the user has more money they would like to convert
            //Takes the first character and runs if-statement to determine if boolean variable is ture or false
            System.out.println("Have a nice day " + name + ". Would you like to convert more money? (yes or no)");
            decision = sc.next().charAt(0);
            will_continue = decision == 'Y' || decision == 'y';
        } while (will_continue); // while statement will trigger do function again if test condition is true
                                // will leave loop if test condition is false

        //when user is done converting money, will print bye statement to terminal
        System.out.println("Bye, come back if you need to convert more money!");
    }
}
