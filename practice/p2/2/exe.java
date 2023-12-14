import java.util.*;

public class exe {

    public static void main(String[] args) {
        int T = MyIO.readInt();

        while (T-- != 0) {
            int n = MyIO.readInt();
            int m = MyIO.readInt();
            Onibus o = new Onibus(m);
            Passageiro p = new Passageiro(n);

            System.out.println(m + " " + n);

            for (int i = 0; i < m; i++) {
                String s = MyIO.readLine();
                o.partidas[i] = s;
                System.out.println(s);
            }

            for (int j = 0; j < n; j++) {
                String s = MyIO.readLine();
                String[] parts = s.split(" ");
                String newString = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
                p.horarios[j] = newString;
                System.out.println(newString);
            }

            int min = min(o, p);
            System.out.println(min);
        }
    }

    private static int min(Onibus o, Passageiro p) {

        // Falta implementar

        return 1;
    }
}

class Passageiro {
    String horarios[];

    public Passageiro(int n) {
        horarios = new String[n];
    }
}

class Onibus {
    String partidas[];

    public Onibus(int m) {
        partidas = new String[m];
    }
}