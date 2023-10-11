import java.util.Scanner;
public class Caesar {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in); //MyIO bugando denovo
        int chave = 3;
        String s = scan.nextLine();
        while(!stringEq(s, "FIM")){
            System.out.println(caesarEncrypt(s, chave));
            s = scan.nextLine();
        }
        scan.close();
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
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch != '\uFFFD') {
                ch = (char) (((int)ch + chave) % 128);
            }
            result += ch;
        }
        return result;
    }

    public static String caesarDecrypt(String s, int chave) {
        return caesarEncrypt(s, 26 - chave);
    }
}