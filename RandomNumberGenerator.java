// RandomNumberGenerator.java
import java.util.Random;
import java.util.Scanner;

public class RandomNumberGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the minimum value:");
        int min = scanner.nextInt();
        
        System.out.println("Enter the maximum value:");
        int max = scanner.nextInt();

        if (min > max) {
            System.out.println("Minimum should be less than or equal to maximum.");
            return;
        }

        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        System.out.println("Generated random number: " + randomNumber);
    }
}
