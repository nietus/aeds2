import java.util.Random;
import java.util.Scanner;

public class RandomSwap {
    public static void main(String[] args) {
        Random gerador = new Random();
        gerador.setSeed(4);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        
        while (!stringEq(input, "FIM")) {
            char firstLetter = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            char secondLetter = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
            String result = trocaLetra(input, firstLetter, secondLetter);
            System.out.println(result);
            
            input = scan.nextLine();
        }
        
        scan.close();
    }

    public static String trocaLetra(String s, char first, char second) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == first) {
                result += second;
            } else {
                result += s.charAt(i);
            }
        }
        return result;
    }

    public static boolean stringEq(String testado, String testando) {
        for (int i = 0; i < testado.length(); i++) {
            if (testado.charAt(i) != testando.charAt(i))
                return false;
        }
        return true;
    }
}