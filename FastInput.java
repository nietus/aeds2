import java.util.Scanner;

public class FastInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            // Using java FastInput.java < pub.in > t.out
            System.out.println("Read: " + line);
        }

        scanner.close();
    }
}