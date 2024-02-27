import java.util.Scanner;

public class BadSubscriptCaught {
    public static void main(String[] args) {
        String[] firstNames = {"Alice", "Bob", "Charlie", "David", "Adam", "Eve", "Frankie", "George", "Hannah", "Isaac"};
        boolean caught = false;
        int i = 1;

        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.println("Enter an index to display the corresponding first name");
                int index = sc.nextInt();
                System.out.println("First name at index " + index + ": " + firstNames[index]);
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Error: Index out of bounds, please only enter an integer 0-9");
                caught = true;
            }
        }while(!caught);

    }
}