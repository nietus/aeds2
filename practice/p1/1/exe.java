import java.util.Scanner;

public class exe {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int iterations = Integer.parseInt(s.nextLine());

        for (int i = 0; i < iterations; i++) {
            String f = "";
            String um = s.nextLine();
            String dois = s.nextLine();
            for(int j = 0; j < Math.max(um.length(), dois.length()); j+=2){
                if (j < um.length()) {
                    f += um.charAt(j);
                }
                if (j + 1 < um.length()) {
                    f += um.charAt(j + 1);
                }
                if (j < dois.length()) {
                    f += dois.charAt(j);
                }
                if (j + 1 < dois.length()) {
                    f += dois.charAt(j + 1);
                }
            }
            System.out.println(f);
        }

        s.close();

    }
}
