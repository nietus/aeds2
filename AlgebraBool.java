import java.util.*;

// This code was made for a very specific kind of boolean algebra input

public class AlgebraBool {
        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            if (stringEq(input,"0")) break;
            else{
                //System.out.println(cleanString(charToBool(rmvSpaces(input))));
                System.out.println(evaluate(cleanString(charToBool(rmvSpaces(input)))));
            }
        }
        scanner.close();
    }

    // Simplify the string as much as possible
    public static String cleanString(String s) {
        int numVars = Character.getNumericValue(s.charAt(0)), ignorarProximo = 0;
        String nova = "";
        for (int i = numVars + 1; i < s.length(); i++) {
            if(s.charAt(i) == ',' || s.charAt(i) == '(' || s.charAt(i) == ')'){
                continue;
            }
            if(ignorarProximo == 0){
                if(s.charAt(i) == 'o'){
                    ignorarProximo = 1;
                }
                if(s.charAt(i) == 'n'){
                    ignorarProximo = 2;
                }
                if(s.charAt(i) == 'a'){
                    ignorarProximo = 2;
                }
                nova += s.charAt(i);
            }
            else
                ignorarProximo--;
        }
        return nova;
    }

    public static int posUltimaLetra(String s) {
        int letra = -1;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                    letra = i;
            }
        }
        return letra;
    }

    public static char evaluate(String s) {
        if (s.length() == 1) {
            return s.charAt(0);
        }
    
        int letra = posUltimaLetra(s);
        String new_s = "";
    
        for (int k = 0; k < letra; k++) {
            new_s += s.charAt(k);
        }
    
        for (int i = letra; i < s.length(); i++) {
                if (s.charAt(i) == 'n') {
                    if (s.charAt(i) == 'n') {
                        if (s.charAt(i + 1) == '0') {
                            new_s += "1";
                        }
                        else if (s.charAt(i + 1) == '1') {
                            new_s += "0";
                        }
                        for (int j = letra + 1; j < s.length() && j + 1 < s.length(); j++) {
                            new_s += s.charAt(j + 1);
                        }
                    }
                }
                if (s.charAt(i) == 'o') {
                    String resp = "";
                        resp = "0";
                        if (s.charAt(i + 1) == '1') {
                            resp = "0";
                        }
                        for (int k = letra + 1; k < s.length() && k + 1 < s.length(); k++) {
                            if (s.charAt(k + 1) == '1'){
                                    resp = "1";
                                }
                        }
                    new_s += resp;
                }
                if (s.charAt(i) == 'a') {
                    String resp = "";
                        resp = "1";
                        if (s.charAt(i + 1) == '0') {
                            resp = "0";
                        }
                        for (int k = letra + 1; k < s.length() && k + 1 < s.length(); k++) {
                            if (s.charAt(k + 1) == '0'){
                                resp = "0";
                            }
                        }
                    new_s += resp;
                }
        }
        //System.out.println(new_s);
        return evaluate(new_s);
    }
    
    // Trade A,B,C,etc for their binary values (0,1)
    public static String charToBool(String s) {
        String nova = "";
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                int offset = currentChar - 'A';
                int index = offset + 1;
                nova += s.charAt(index);
            } else {
                nova += currentChar;
            }
        }
        return nova;
    }

    public static String rmvSpaces(String s) {
        String returned = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
            } else {
                returned += s.charAt(i);
            }
        }
        return returned;
    }

    public static boolean stringEq(String testado, String testando) {
        if (testado.length() != testando.length()) {
            return false;
        }
        for (int i = 0; i < testado.length(); i++) {
            if (testado.charAt(i) != testando.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}