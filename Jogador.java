import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Jogador {

    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        this.id = -1;
        this.nome = "nao informado";
        this.altura = -1;
        this.peso = -1;
        this.universidade = "nao informado";
        this.anoNascimento = -1;
        this.cidadeNascimento = "nao informado";
        this.estadoNascimento = "nao informado";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade,
            int anoNascimento, String cidadeNascimento, String estadoNascimento) {
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUniversidade() {
        return universidade;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento() {
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = "/tmp/players.csv";
        path = "src/Players.csv";
        ArrayList<Jogador> jogadores = new ArrayList<>();
        String line = "";
        boolean jump_header = true;
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                while ((line = br.readLine()) != null) {
                    if (jump_header) {
                        jump_header = false;
                        continue;
                    }
                    String[] values = line.split(",");
                    Jogador j = new Jogador();
                    if (values.length > 0 && !values[0].trim().isEmpty()) {
                        j.setId(Integer.parseInt(values[0].trim()));
                    }
                    if (values.length > 1 && !values[1].trim().isEmpty()) {
                        j.setNome(values[1].trim());
                    }
                    if (values.length > 2 && !values[2].trim().isEmpty()) {
                        j.setAltura(Integer.parseInt(values[2].trim()));
                    }
                    if (values.length > 3 && !values[3].trim().isEmpty()) {
                        j.setPeso(Integer.parseInt(values[3].trim()));
                    }
                    if (values.length > 4 && !values[4].trim().isEmpty()) {
                        j.setUniversidade(values[4].trim());
                    }
                    if (values.length > 5 && !values[5].trim().isEmpty()) {
                        try {
                            j.setAnoNascimento(Integer.parseInt(values[5].trim()));
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing year of birth for player: " + values[1]);
                            System.err.println("Invalid input: " + values[5].trim());
                            e.printStackTrace();
                        }
                    }
                    if (values.length > 6 && !values[6].trim().isEmpty()) {
                        j.setCidadeNascimento(values[6].trim());
                    }
                    if (values.length > 7 && !values[7].trim().isEmpty()) {
                        j.setEstadoNascimento(values[7].trim());
                    }
                    jogadores.add(j);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        // searchFromIdInput(jogadores);
        // sequentialSearch(jogadores);
        // selectionSort(jogadores);
        // insertionSort(jogadores);
        // heapSort(jogadores);
        sc.close();
    }

    public static void heapSort(ArrayList<Jogador> jogadores) {
        // Montando o array com os numeros dados
        ArrayList<Jogador> k = filteredList(jogadores);

        // Algoritmo
        long start = System.currentTimeMillis();
        int[] counts = { 0, 0 }; // mov and comp
        int size = k.size();
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(k, size, i, counts);

        for (int i = size - 1; i >= 0; i--) {
            Jogador temp = k.get(0);
            k.set(0, k.get(i));
            k.set(i, temp);
            counts[0] += 2;
            heapify(k, i, 0, counts);
        }

        long end = System.currentTimeMillis();

        for (Jogador jogador : k) {
            System.out.println(jogador.toString());
        }

        log("_heapsort.txt", end - start, counts[0], counts[1]);
    }

    private static void heapify(ArrayList<Jogador> jogadores, int n, int i, int[] counts) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < n &&
                (jogadores.get(leftChild).getAltura() > jogadores.get(largest).getAltura() ||
                        (jogadores.get(leftChild).getAltura() == jogadores.get(largest).getAltura() &&
                                jogadores.get(leftChild).getNome().compareTo(jogadores.get(largest).getNome()) > 0))) {
            largest = leftChild;
            counts[0]++;
            counts[1]++;
        }

        if (rightChild < n &&
                (jogadores.get(rightChild).getAltura() > jogadores.get(largest).getAltura() ||
                        (jogadores.get(rightChild).getAltura() == jogadores.get(largest).getAltura() &&
                                jogadores.get(rightChild).getNome().compareTo(jogadores.get(largest).getNome()) > 0))) {
            largest = rightChild;
            counts[0]++;
            counts[1] += 2;
        }

        if (largest != i) {
            Jogador swap = jogadores.get(i);
            jogadores.set(i, jogadores.get(largest));
            jogadores.set(largest, swap);
            counts[0] += 2;
            counts[1] += 3;
            heapify(jogadores, n, largest, counts);
        }
    }

    public static void insertionSort(ArrayList<Jogador> jogadores) {
        // Montando o array com os numeros dados
        ArrayList<Jogador> k = filteredList(jogadores);
        
        // Algoritmo
        int tam = k.size();
        int mov = 0, com = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < tam; i++) {
            Jogador tmp = k.get(i);
            int j = i - 1;
            while (j >= 0 && (k.get(j).getAnoNascimento() > tmp.getAnoNascimento()
                    || (k.get(j).getAnoNascimento() == tmp.getAnoNascimento()
                            && k.get(j).getNome().compareTo(tmp.getNome()) > 0))) {
                com++;
                k.set(j + 1, k.get(j));
                mov++;
                j--;
            }
            k.set(j + 1, tmp);
            mov++;
        }
        long end = System.currentTimeMillis();
        for (Jogador j : k) {
            System.out.println(j.toString());
        }
        log("_insercao.txt", end - start, mov, com);
    }

    public static void selectionSort(ArrayList<Jogador> jogadores) {
        // Montando o array com os numeros dados
        ArrayList<Jogador> k = filteredList(jogadores);
        
        // Algoritmo
        int tam = k.size();
        long start = System.currentTimeMillis();
        int mov = 0, com = 0;
        for (int i = 0; i < tam - 1; i++) {
            int m = i;
            for (int j = i + 1; j < tam; j++) {
                com++;
                if (k.get(j).getNome().compareTo(k.get(m).getNome()) < 0) {
                    m = j;
                }
            }
            Jogador temp = k.get(m);
            k.set(m, k.get(i));
            k.set(i, temp);
            mov += 2;
        }
        long end = System.currentTimeMillis();
        for (Jogador j : k) {
            System.out.println(j.toString());
        }
        log("_selecao.txt", end - start, mov, com);
    }

    public static void sequentialSearch(ArrayList<Jogador> jogadores) {
        // Montando o array com os numeros dados
        ArrayList<Jogador> k = filteredList(jogadores);

        // Algoritmo
        String s = "";
        long start = System.currentTimeMillis();
        int contador = 0;
        while (!(s = MyIO.readLine()).equals("FIM")) {
            boolean encontrado = false;
            for (Jogador n : k) {
                contador++;
                if (n.getNome().equals(s)) {
                    System.out.println("SIM");
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado)
                System.out.println("NAO");
        }
        long end = System.currentTimeMillis();
        log("_sequencial.txt", end - start, contador);
    }

    public static void searchFromIdInput(ArrayList<Jogador> jogadores) {
        ArrayList<Integer> search_num = new ArrayList<>();
        String s = "";
        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                search_num.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        for (int num : search_num) {
            for (Jogador j : jogadores) {
                if (j.getId() == num)
                    System.out.println(j.toString());
            }
        }
    }

    public static void log(String nome_arq, double run_time, int comparations) {
        String matricula = "1368777";
        nome_arq = matricula + nome_arq;
        Arq.openWrite(nome_arq);
        Arq.println(matricula + " \t" + run_time + " \t" + comparations);
        Arq.close();
    }

    public static void log(String nome_arq, double run_time, int comparations, int movimentations) {
        String matricula = "1368777";
        nome_arq = matricula + nome_arq;
        Arq.openWrite(nome_arq);
        Arq.println(matricula + " \t" + run_time + " \t" + comparations + " \t" + movimentations);
        Arq.close();
    }
    
    public static ArrayList<Jogador> filteredList(ArrayList<Jogador> jogadores){
        ArrayList<Integer> n = new ArrayList<>();
        String s = "";
        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                n.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ArrayList<Jogador> k = new ArrayList<>();
        for (int num : n) {
            for (Jogador j : jogadores) {
                if (j.getId() == num) {
                    k.add(j);
                }
            }
        }
        return k;
    }
    
    @Override
    public String toString() {
        return "[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso +
                " ## " + this.anoNascimento + " ## " + this.universidade + " ## " +
                this.cidadeNascimento + " ## " + this.estadoNascimento + "]";
    }
}

