import java.util.Scanner;

public class Is {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        
        while (!stringEq(s, "FIM")) {
            if (isVogal(s))
                System.out.print("SIM ");
            else
                System.out.print("NAO ");
            
            if (isConsoante(s))
                System.out.print("SIM ");
            else
                System.out.print("NAO ");
            
            if (isInt(s))
                System.out.print("SIM ");
            else
                System.out.print("NAO ");
            
            if (isReal(s))
                System.out.print("SIM");
            else
                System.out.print("NAO");
            
            System.out.println();
            s = scan.nextLine();
        }
        
        scan.close();
    }

    public static boolean stringEq(String testado, String testando) {
        for (int i = 0; i < testado.length(); i++) {
            if (testado.charAt(i) != testando.charAt(i))
                return false;
        }
        return true;
    }

    public static boolean isVogal(String s) {
        String vogais = "aeiou ";
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (contemDiferente(vogais, s.charAt(i)) == -1)
                return false;
        }
        return true;
    }

    public static boolean isConsoante(String s) {
        String consoantes = "bcdfghjklmnpqrstvwxyz ";
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (contemDiferente(consoantes, s.charAt(i)) == -1)
                return false;
        }
        return true;
    }

    public static boolean isInt(String s) {
        String inteiro = "1234567890 ";
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            if (contemDiferente(inteiro, s.charAt(i)) == -1)
                return false;
        }
        return true;
    }

    public static boolean isReal(String s) {
        s = s.toLowerCase();
        String delimitadores = "1234567890., ";
        for (int i = 0; i < s.length(); i++) {
            if (contemDiferente(delimitadores, s.charAt(i)) == -1) 
                return false;
        }
        return true;
    }

    public static int contemDiferente(String str, char target) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                return i; 
            }
        }
        return -1; 
    }
}