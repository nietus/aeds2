import java.util.Scanner;

public class IsRec {
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

    public static boolean stringEq(String testado, String testando) { // checa se duas strings sao iguais
        for (int i = 0; i < testado.length(); i++) {
            if (testado.charAt(i) != testando.charAt(i))
                return false;
        }
        return true;
    }public static boolean isVogal(String s) {
        String vogais = "aeiou ";
        return isVogalRec(s.toLowerCase(), vogais, 0);
    }
    
    private static boolean isVogalRec(String s, String vogais, int index) {
        if (index == s.length()) {
            return true;
        }
        char currentChar = s.charAt(index);
        if (contemDiferente(vogais, currentChar) == -1) {
            return false;
        }
        return isVogalRec(s, vogais, index + 1);
    }
    
    public static boolean isConsoante(String s) {
        String consoantes = "bcdfghjklmnpqrstvwxyz ";
        return isConsoanteRec(s.toLowerCase(), consoantes, 0);
    }
    
    private static boolean isConsoanteRec(String s, String consoantes, int index) {
        if (index == s.length()) {
            return true;
        }
        char currentChar = s.charAt(index);
        if (contemDiferente(consoantes, currentChar) == -1) {
            return false;
        }
        return isConsoanteRec(s, consoantes, index + 1);
    }
    
    public static boolean isInt(String s) {
        String inteiro = "1234567890 ";
        return isIntRec(s.toLowerCase(), inteiro, 0);
    }
    
    private static boolean isIntRec(String s, String inteiro, int index) {
        if (index == s.length()) {
            return true;
        }
        char currentChar = s.charAt(index);
        if (contemDiferente(inteiro, currentChar) == -1) {
            return false;
        }
        return isIntRec(s, inteiro, index + 1);
    }
    
    public static boolean isReal(String s) {
        String delimitadores = "1234567890., ";
        return isRealRec(s.toLowerCase(), delimitadores, 0,0);
    }
    
    private static boolean isRealRec(String s, String delimitadores, int index, int cont) {
        int repetiu = 0;
        if (index == s.length()) {
            return true;
        }
        char currentChar = s.charAt(index);
        if (contemDiferente(delimitadores, currentChar) == -1 || cont > 1) {
            return false;
        }
        if(s.charAt(index) == '.' || s.charAt(index) == ','){//se tiver . ou , mais de uma vez, ele desconsidera q eh real
            repetiu++;
        }
        return isRealRec(s, delimitadores, index + 1, cont + repetiu);
    }
    

    public static int contemDiferente(String str, char target) { // checa se contem um charactere diferente do target na string
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target) {
                return i; 
            }
        }
        return -1; 
    }
}