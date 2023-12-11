import java.util.*;

class exe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int q;

        while ((q = sc.nextInt()) != 0) {
            sc.nextLine();

            Estudante[] estudantes = new Estudante[q];

            for (int i = 0; i < q; i++) {
                String nome = sc.nextLine();
                String[] camisetaInfo = sc.nextLine().split(" ");
                String cor = camisetaInfo[0];
                String tam = camisetaInfo[1];
                Estudante e = new Estudante(nome, cor, tam);
                estudantes[i] = e;
            }

            Arrays.sort(estudantes, Comparator
                    .comparing(Estudante::getCor)
                    .thenComparing(Comparator.comparing(Estudante::getTam).reversed())
                    .thenComparing(Estudante::getNome)
            );

            for (Estudante e : estudantes) {
                System.out.println(e.getCor() + " " + e.getTam() + " " + e.getNome());
            }

            System.out.println();
        }

        sc.close();
    }
}

class Estudante {
    String nome;
    Camiseta m;

    public Estudante(String nome, String cor, String tam) {
        this.nome = nome;
        this.m = new Camiseta(cor, tam);
    }

    public String getNome() {
        return this.nome;
    }

    public String getCor() {
        return this.m.getCor();
    }

    public String getTam() {
        return this.m.getTam();
    }
}

class Camiseta {
    String cor;
    String tam;

    public Camiseta(String cor, String tam) {
        this.cor = cor;
        this.tam = tam;
    }

    public String getCor() {
        return cor;
    }

    public String getTam() {
        return tam;
    }
}
