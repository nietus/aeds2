import java.util.Scanner;
public class CaesarRec {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); //MyIO bugando denovo
        int chave = 3;
        String s = scan.nextLine();
        String e = "";
        while(!stringEq(s, "FIM")){
            e = caesarEncrypt(s, chave);
            System.out.println(e);
            s = scan.nextLine();
        }
        scan.close();
        //System.out.println(caesarDecrypt(e, chave));
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

    public static String caesarEncrypt(String s, int chave) {
        return caesarEncryptRecursive(s, chave, 0, s.length());
    }
    
    public static String caesarEncryptRecursive(String s, int chave, int index, int length) {
        //parada, sem concatenar nada
        if (index >= length) {
            return "";
        }
        char ch = s.charAt(index);
        //FFFD - Digito irreconhecivel ignorado
        if (ch != '\uFFFD') {
            ch = (char) (((int) ch + chave) % 128);
        }
        //passa pro charAt(i+1)
        String resto = caesarEncryptRecursive(s, chave, index + 1, length);
        return ch + resto;
    }
    
    public static String caesarDecrypt(String s, int chave) {
        return caesarEncrypt(s, 128 - chave);
    }
}