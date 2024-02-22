import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double[] numbers = new double[20];

        int i = 0;
        Scanner sc = new Scanner(System.in);

        boolean con = true;
        do{
            System.out.println("Please enter element number " + (i+1) + ": ");
            double value = sc.nextDouble();
            numbers[i] = value;
            System.out.println("If you are done adding numbers type 'yes', if not type 'no': ");
            char condition = sc.next().charAt(0);
            System.out.println(condition);
            if(condition == 'Y' || condition == 'y'){
                con = false;
            }
            i++;
        }while(i<20 && con);
        System.out.println("Your array is ready! Below is your array.");
        System.out.println(Arrays.toString(numbers));
    }
}