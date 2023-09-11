import java.util.Scanner;

public class Palindromo {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //MyIO dando problema
        String s = scanner.nextLine();
        
        while (!s.equals("FIM")) {
            if (isPalindromo(s)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            s = scanner.nextLine();
        }
        
        scanner.close();
    }
    
    public static boolean isPalindromo(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
