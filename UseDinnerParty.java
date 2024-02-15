//Sai and Tanner Partner Project
import java.util.Scanner;

public class UseDinnerParty {
    public static void main(String[] args){
        System.out.println("Enter number of guests for the party: ");
        Scanner sc = new Scanner(System.in);
        int guests = sc.nextInt();
        Party party1 = new Party();
        party1.setGuests(guests);
        System.out.println("The party has " + party1.getGuests() + " guests.");
        party1.invitation();

        int choices;
        System.out.println("Enter number of guests for the dinner party: ");
        int dinnerGuests = sc.nextInt();
        DinnerParty dinnerParty1 = new DinnerParty();
        dinnerParty1.setGuests(dinnerGuests);
        System.out.println("Enter the menu option -- 1 for chicken or 2 for vegi: ");
        int dinnerChoice = sc.nextInt();
        dinnerParty1.setDinnerChoice(dinnerChoice);
        System.out.println("Menu option " + dinnerParty1.getDinnerChoice() + " will be served");
        dinnerParty1.invitation();

    }

}
