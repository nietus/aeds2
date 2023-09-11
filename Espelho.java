import java.util.Scanner;
public class Espelho {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        while(scan.hasNext()){
            String s = "";
            int ini,fim;
            ini = scan.nextInt();
            fim = scan.nextInt();
            for(int j = ini - 1; j < fim;j++){
                s += j + 1;
            }
            s += inverte(s);
            System.out.println(s);
        }
        scan.close();
    }
    public static String inverte(String s){
        String new_s = "";
        for(int i = 0; i < s.length();i++){
            new_s += s.charAt(s.length() - i - 1);
        }
        return new_s;
    }
}
