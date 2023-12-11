import java.util.Scanner;

public class exe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String numStr = scanner.nextLine();
            String cutoffStr = scanner.nextLine();

            double num = Double.parseDouble(numStr);
            double cutoff = Double.parseDouble(cutoffStr);

            if (num - Math.floor(num) >= cutoff) {
                System.out.println((int)Math.ceil(num));
            } else {
                System.out.println((int)(Math.floor(num)));
            }
        }
        scanner.close();
    }
}
