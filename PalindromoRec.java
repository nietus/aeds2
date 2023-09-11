import java.util.Scanner;

public class PalindromoRec {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //MyIO dando problema
        String s = scanner.nextLine();
        
        while (!s.equals("FIM")) {
            if (isPalindromo(s,0,s.length() - 1)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            s = scanner.nextLine();
        }
        
        scanner.close();
    }
    
    public static boolean isPalindromo(String s, int inicio, int fim) {
        if(inicio >= fim)
            return true;
        if(s.charAt(inicio) != s.charAt(fim))
            return false;
        return isPalindromo(s, inicio + 1, fim - 1);
    }
}