import java.util.Scanner;

public class FastInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Using java FastInput < pub.in > t.out
            System.out.println("Read: " + line);
        }

        scanner.close();
    }
}
/*
2 lines
import java.util.Scanner;

public class FastInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line1 = scanner.nextLine(); // Read the first line
            if (!scanner.hasNextLine()) {
                break; // Break if there are no more lines
            }
            String line2 = scanner.nextLine(); // Read the second line

            // Using java FastInput.java < pub.in > t.out
            System.out.println("Read Line 1: " + line1);
            System.out.println("Read Line 2: " + line2);
        }

        scanner.close();
    }
}
*/