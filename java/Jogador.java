import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

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
        this.universidade = universidade.replace("\"", "").trim();
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

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String path = "/tmp/players.csv";
        path = "../src/Players.csv";
        ArrayList<Jogador> jogadores = new ArrayList<>();
        String line = "";
        boolean jump_header = true;

        // Creation of the main data structure

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
                    if (j.getId() == 919) { // Missing player
                        j.setNome("Curtis Rowe");
                        j.setAltura(201);
                        j.setPeso(102);
                        j.setUniversidade("University of California - Los Angeles");
                        j.setAnoNascimento(1949);
                        j.setCidadeNascimento("Bessemer");
                        j.setEstadoNascimento("Alabama");
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
        // partialSelectionSort(jogadores, 10);
        // quick(jogadores, 10);
        // countingSort(jogadores);
        // mergesort(jogadores);

        String s = "";

        // Process the insertion of specific jogadores into another data structure

        Arvore dataStructure = new Arvore();

        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                dataStructure.inserir(getById(Integer.parseInt(s), jogadores));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                System.out.println(s + " " + dataStructure.pesquisar(s));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sc.close();
    }

    public static Jogador getByName(String name, ArrayList<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            if (jogador.getNome().equals(name)) {
                return jogador;
            }
        }
        return new Jogador();
    }

    public static Jogador getById(int id, ArrayList<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == id) {
                return jogador;
            }
        }
        return new Jogador();
    }

    public static void quick(ArrayList<Jogador> jogadores, int k) {
        int siz = jogadores.size();
        partialQuickSort(jogadores, k, 0, siz - 1);
        for (int i = 0; i < 10; i++) {
            System.out.println(jogadores.get(i).toString());
        }
    }

    private static void partialQuickSort(ArrayList<Jogador> jogadores, int k, int esq, int dir) {
        int i = esq, j = dir;
        Jogador pivo = jogadores.get((esq + dir) / 2);

        while (i <= j) {
            while (jogadores.get(i).getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0 ||
                    (jogadores.get(i).getEstadoNascimento().equals(pivo.getEstadoNascimento()) &&
                            jogadores.get(i).getNome().compareTo(pivo.getNome()) < 0))
                i++;
            while (jogadores.get(j).getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0 ||
                    (jogadores.get(j).getEstadoNascimento().equals(pivo.getEstadoNascimento()) &&
                            jogadores.get(j).getNome().compareTo(pivo.getNome()) > 0))
                j--;
            if (i <= j) {
                Jogador temp = jogadores.get(i);
                jogadores.set(i, jogadores.get(j));
                jogadores.set(j, temp);
                i++;
                j--;
            }
        }

        if (esq < j)
            partialQuickSort(jogadores, k, esq, j);
        if (i < k && i < dir)
            partialQuickSort(jogadores, k, i, dir);
    }

    public static void partialSelectionSort(ArrayList<Jogador> jogadores, int k) {

        int siz = jogadores.size();

        for (int i = 0; i < k; i++) {
            int menor = i;

            for (int j = i + 1; j < siz; j++) {
                if (jogadores.get(menor).getNome().compareTo(jogadores.get(j).getNome()) > 0) {
                    menor = j;
                }
            }

            Jogador temp = jogadores.get(i);
            jogadores.set(i, jogadores.get(menor));
            jogadores.set(menor, temp);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(jogadores.get(i).toString());
        }
    }

    public static void mergesort(ArrayList<Jogador> jogadores) {
        mergeSort(jogadores, 0, jogadores.size() - 1);
        for (Jogador c : jogadores) {
            System.out.println(c.toString());
        }
    }

    private static void mergeSort(ArrayList<Jogador> jogadores, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(jogadores, left, mid);
            mergeSort(jogadores, mid + 1, right);

            merge(jogadores, left, mid, right);
        }
    }

    private static void merge(ArrayList<Jogador> jogadores, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        ArrayList<Jogador> leftArr = new ArrayList<>();
        ArrayList<Jogador> rightArr = new ArrayList<>();

        for (int i = 0; i < n1; ++i)
            leftArr.add(jogadores.get(left + i));

        for (int j = 0; j < n2; ++j)
            rightArr.add(jogadores.get(mid + 1 + j));

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArr.get(i).getUniversidade().compareTo(rightArr.get(j).getUniversidade()) < 0
                    || (leftArr.get(i).getUniversidade().equals(rightArr.get(j).getUniversidade())
                            && leftArr.get(i).getNome().compareTo(rightArr.get(j).getNome()) < 0)) {
                jogadores.set(k, leftArr.get(i));
                i++;
            } else {
                jogadores.set(k, rightArr.get(j));
                j++;
            }
            k++;
        }

        while (i < n1) {
            jogadores.set(k, leftArr.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            jogadores.set(k, rightArr.get(j));
            j++;
            k++;
        }
    }

    public static void countingSort(ArrayList<Jogador> jogadores) {

        // Find the maximum height to determine the range for counting
        int maxAltura = findMaxAltura(jogadores);

        // Create an array to store the count of each height
        int[] count = new int[maxAltura + 1];

        // Count the occurrences of each height
        for (Jogador jogador : jogadores) {
            int altura = jogador.getAltura();
            count[altura]++;
        }

        // Create a 2D array to store players for each height
        @SuppressWarnings("unchecked")
        ArrayList<Jogador>[] heightGroups = new ArrayList[maxAltura + 1];
        for (int i = 0; i <= maxAltura; i++) {
            heightGroups[i] = new ArrayList<>();
        }

        // Assign players to their respective height groups
        for (Jogador jogador : jogadores) {
            int altura = jogador.getAltura();
            heightGroups[altura].add(jogador);
        }

        // Sort players within each height group by their names
        for (int i = 0; i <= maxAltura; i++) {
            heightGroups[i].sort(Comparator.comparing(Jogador::getNome));
        }

        // Print the sorted players by height and name
        for (int i = 0; i <= maxAltura; i++) {
            for (Jogador jogador : heightGroups[i]) {
                System.out.println(jogador.toString());
            }
        }
    }

    private static int findMaxAltura(ArrayList<Jogador> jogadores) {
        int maxAltura = Integer.MIN_VALUE;
        for (Jogador jogador : jogadores) {
            int altura = jogador.getAltura();
            if (altura > maxAltura) {
                maxAltura = altura;
            }
        }
        return maxAltura;
    }

    public static void heapSort(ArrayList<Jogador> jogadores) {

        long start = System.currentTimeMillis();
        int[] counts = { 0, 0 }; // number of movimentations and comparisons
        int size = jogadores.size();
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(jogadores, size, i, counts);

        for (int i = size - 1; i >= 0; i--) {
            Jogador temp = jogadores.get(0);
            jogadores.set(0, jogadores.get(i));
            jogadores.set(i, temp);
            counts[0] += 2;
            heapify(jogadores, i, 0, counts);
        }

        long end = System.currentTimeMillis();

        for (Jogador jogador : jogadores) {
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

        int tam = jogadores.size();
        int mov = 0, com = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; i < tam; i++) {
            Jogador tmp = jogadores.get(i);
            int j = i - 1;
            while (j >= 0 && (jogadores.get(j).getAnoNascimento() > tmp.getAnoNascimento()
                    || (jogadores.get(j).getAnoNascimento() == tmp.getAnoNascimento()
                            && jogadores.get(j).getNome().compareTo(tmp.getNome()) > 0))) {
                com++;
                jogadores.set(j + 1, jogadores.get(j));
                mov++;
                j--;
            }
            jogadores.set(j + 1, tmp);
            mov++;
        }
        long end = System.currentTimeMillis();
        for (Jogador j : jogadores) {
            System.out.println(j.toString());
        }
        log("_insercao.txt", end - start, mov, com);
    }

    public static void selectionSort(ArrayList<Jogador> jogadores) {

        int tam = jogadores.size();
        long start = System.currentTimeMillis();
        int mov = 0, com = 0;
        for (int i = 0; i < tam - 1; i++) {
            int m = i;
            for (int j = i + 1; j < tam; j++) {
                com++;
                if (jogadores.get(j).getNome().compareTo(jogadores.get(m).getNome()) < 0) {
                    m = j;
                }
            }
            Jogador temp = jogadores.get(m);
            jogadores.set(m, jogadores.get(i));
            jogadores.set(i, temp);
            mov += 2;
        }
        long end = System.currentTimeMillis();
        for (Jogador j : jogadores) {
            System.out.println(j.toString());
        }
        log("_selecao.txt", end - start, mov, com);
    }

    public static void sequentialSearch(ArrayList<Jogador> jogadores) {

        String s = "";
        long start = System.currentTimeMillis();
        int contador = 0;
        while (!(s = MyIO.readLine()).equals("FIM")) {
            boolean encontrado = false;
            for (Jogador n : jogadores) {
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

    public static ListaSequencial filteredSeqList(ArrayList<Jogador> jogadores) throws Exception {
        ListaSequencial k = new ListaSequencial(1000);
        String s = "";
        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                k.inserirFim(getById(Integer.parseInt(s), jogadores));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        int size = MyIO.readInt();

        for (int i = 0; i < size; i++) {
            String command = MyIO.readLine();

            String[] parts = command.split(" ");

            String operation = parts[0];

            switch (operation) {
                case "II":
                    String fileName = parts[1];
                    Jogador inicio = jogadores.get(Integer.parseInt(fileName));
                    k.inserirInicio(inicio);
                    break;
                case "I*":
                    int position = Integer.parseInt(parts[1]);
                    Jogador inserir = jogadores.get(Integer.parseInt(parts[2]));
                    k.inserir(inserir, position);
                    break;
                case "IF":
                    fileName = parts[1];
                    Jogador fim = jogadores.get(Integer.parseInt(fileName));
                    k.inserirFim(fim);
                    break;
                case "RI":
                    System.out.println("(R) " + k.removerInicio().getNome());
                    break;
                case "R*":
                    int removePosition = Integer.parseInt(parts[1]);
                    System.out.println("(R) " + k.remover(removePosition).getNome());
                    break;
                case "RF":
                    System.out.println("(R) " + k.removerFim().getNome());
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        return k;
    }

    public static PilhaSequencial filteredSeqStack(ArrayList<Jogador> jogadores) throws Exception {
        PilhaSequencial k = new PilhaSequencial(1000);
        String s = "";
        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                k.inserir(getById(Integer.parseInt(s), jogadores));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        int size = MyIO.readInt();

        for (int i = 0; i < size; i++) {
            String command = MyIO.readLine();

            String[] parts = command.split(" ");

            String operation = parts[0];

            switch (operation) {
                case "I":
                    String fileName = parts[1];
                    Jogador empilhar = jogadores.get(Integer.parseInt(fileName));
                    k.inserir(empilhar);
                    break;
                case "R":
                    System.out.println("(R) " + k.remover().getNome().replace('*', ' '));
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        return k;
    }

    public static ListaFlexivel filteredFlexList(ArrayList<Jogador> jogadores) throws Exception {
        ListaFlexivel k = new ListaFlexivel();
        String s = "";
        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                k.inserirFim(getById(Integer.parseInt(s), jogadores));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        int size = MyIO.readInt();
        // System.out.println(size);

        for (int i = 0; i < size; i++) {
            String command = MyIO.readLine();

            String[] parts = command.split(" ");

            String operation = parts[0];

            switch (operation) {
                case "II":
                    String fileName = parts[1];
                    Jogador inicio = jogadores.get(Integer.parseInt(fileName));
                    k.inserirInicio(inicio);
                    break;
                case "I*":
                    int position = Integer.parseInt(parts[1]);
                    Jogador inserir = jogadores.get(Integer.parseInt(parts[2]));
                    k.inserir(inserir, position);
                    break;
                case "IF":
                    fileName = parts[1];
                    Jogador fim = jogadores.get(Integer.parseInt(fileName));
                    k.inserirFim(fim);
                    break;
                case "RI":
                    System.out.println("(R) " + k.removerInicio().getNome());
                    break;
                case "R*":
                    int removePosition = Integer.parseInt(parts[1]);
                    System.out.println("(R) " + k.remover(removePosition).getNome());
                    break;
                case "RF":
                    System.out.println("(R) " + k.removerFim().getNome());
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
        return k;
    }

    public static PilhaFlexivel filteredFlexStack(ArrayList<Jogador> jogadores) throws Exception {
        String s = "";
        PilhaFlexivel k = new PilhaFlexivel();

        while (!(s = MyIO.readLine()).equals("FIM")) {
            try {
                k.empilhar(getById(Integer.parseInt(s), jogadores));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        int size = Integer.parseInt(MyIO.readLine());

        for (int i = 0; i < size - 1; i++) {

            String command = MyIO.readLine();

            String[] parts = command.split(" ");

            String operation = parts[0];

            switch (operation) {
                case "I":
                    String idx = parts[1];
                    Jogador jogador = jogadores.get(Integer.parseInt(idx));
                    k.empilhar(jogador);
                    break;
                case "R":
                    System.out.println("(R) " + k.getJogadorTopo().getNome().replace('*', ' '));
                    k.desempilhar();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }

        System.out.println("(R) " + k.getJogadorTopo().getNome().replace('*', ' '));
        k.desempilhar();

        return k;
    }

    public String customString(int i) {
        return "[" + i + "]" + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso +
                " ## " + this.anoNascimento + " ## " + this.universidade + " ## " +
                this.cidadeNascimento + " ## " + this.estadoNascimento + " ##";
    }

    @Override
    public String toString() {
        return "[" + this.id + " ## " + this.nome + " ## " + this.altura + " ## " + this.peso +
                " ## " + this.anoNascimento + " ## " + this.universidade + " ## " +
                this.cidadeNascimento + " ## " + this.estadoNascimento + "]";
    }
}

class ListaSequencial {

    private Jogador[] lista;
    private int tamanho;

    public ListaSequencial(int capacidade) {
        lista = new Jogador[capacidade];
        tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Jogador getJogador(int x) {
        return lista[x];
    }

    public void inserirInicio(Jogador j) throws Exception {
        if (tamanho >= lista.length) {
            throw new Exception("List is full");
        }

        for (int i = tamanho; i > 0; i--) {
            lista[i] = lista[i - 1];
        }
        lista[0] = j;
        tamanho++;
    }

    public void inserirFim(Jogador j) throws Exception {
        if (tamanho >= lista.length) {
            throw new Exception("List is full");
        }
        lista[tamanho] = j;
        tamanho++;
    }

    public void inserir(Jogador j, int x) throws Exception {
        if (tamanho >= lista.length) {
            throw new Exception("List is full");
        }
        if (x < 0 || x >= tamanho) {
            throw new Exception("Invalid position");
        }
        for (int i = tamanho; i > x; i--) {
            lista[i] = lista[i - 1];
        }
        lista[x] = j;
        tamanho++;
    }

    public Jogador removerInicio() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }
        Jogador removido = lista[0];
        for (int i = 0; i < tamanho - 1; i++) {
            lista[i] = lista[i + 1];
        }
        tamanho--;
        return removido;
    }

    public Jogador removerFim() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }
        Jogador removido = lista[tamanho - 1];
        tamanho--;
        return removido;
    }

    public Jogador remover(int x) throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }
        if (x < 0 || x >= tamanho) {
            throw new Exception("Invalid Position");
        }
        Jogador removido = lista[x];

        for (int i = x; i < tamanho - 1; i++) {
            lista[i] = lista[i + 1];
        }
        tamanho--;

        return removido;
    }
}

class PilhaSequencial {

    private Jogador[] pilha;
    int tamanho;

    PilhaSequencial(int capacidade) {
        pilha = new Jogador[capacidade];
        this.tamanho = 0;
    }

    public void inserir(Jogador j) throws Exception {
        if (tamanho >= pilha.length) {
            throw new Exception("Stack is full");
        }
        pilha[tamanho] = j;
        tamanho++;
    }

    public Jogador remover() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("Stack is empty");
        }
        Jogador removido = pilha[tamanho - 1];
        tamanho--;
        return removido;
    }

    public Jogador getJogador(int x) {
        return pilha[x];
    }

    public int getTamanho() {
        return tamanho;
    }
}

class ListaFlexivel {

    private Celula primeiro, ultimo;
    private int tamanho;

    public ListaFlexivel() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Jogador getJogador(int x) throws Exception {
        if (x < 0 || x >= tamanho) {
            throw new Exception("Invalid position");
        }

        Celula atual = primeiro;
        for (int i = 0; i < x; i++) {
            atual = atual.prox;
        }

        return atual.j;
    }

    public void inserirInicio(Jogador j) {
        Celula novaCelula = new Celula(j);
        if (primeiro == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            novaCelula.prox = primeiro;
            primeiro = novaCelula;
        }
        tamanho++;
    }

    public void inserirFim(Jogador j) {
        Celula novaCelula = new Celula(j);
        if (ultimo == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            ultimo.prox = novaCelula;
            ultimo = novaCelula;
        }
        tamanho++;
    }

    public void inserir(Jogador j, int x) throws Exception {
        if (x < 0 || x > tamanho) {
            throw new Exception("Invalid position");
        }

        if (x == 0) {
            inserirInicio(j);
        } else if (x == tamanho) {
            inserirFim(j);
        } else {
            Celula novaCelula = new Celula(j);
            Celula atual = primeiro;

            for (int i = 0; i < x - 1; i++) {
                atual = atual.prox;
            }

            novaCelula.prox = atual.prox;
            atual.prox = novaCelula;
            tamanho++;
        }
    }

    public Jogador removerInicio() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }

        Jogador removido = primeiro.j;
        primeiro = primeiro.prox;
        tamanho--;

        if (tamanho == 0) {
            ultimo = null;
        }

        return removido;
    }

    public Jogador removerFim() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }

        Jogador removido;
        if (tamanho == 1) {
            removido = primeiro.j;
            primeiro = null;
            ultimo = null;
        } else {
            Celula atual = primeiro;
            while (atual.prox != ultimo) {
                atual = atual.prox;
            }

            removido = ultimo.j;
            ultimo = atual;
            ultimo.prox = null;
        }

        tamanho--;
        return removido;
    }

    public Jogador remover(int x) throws Exception {
        if (tamanho <= 0 || x < 0 || x >= tamanho) {
            throw new Exception("Invalid position or list is empty");
        }

        Jogador removido;
        if (x == 0) {
            removido = removerInicio();
        } else if (x == tamanho - 1) {
            removido = removerFim();
        } else {
            Celula atual = primeiro;
            for (int i = 0; i < x - 1; i++) {
                atual = atual.prox;
            }

            removido = atual.prox.j;
            atual.prox = atual.prox.prox;
            tamanho--;
        }

        return removido;
    }
}

class Celula {
    public Jogador j;
    public Celula prox;

    public Celula(Jogador jogador) {
        this.j = jogador;
        this.prox = null;
    }
}

class PilhaFlexivel {

    private Celula topo;
    private int tamanho;

    public PilhaFlexivel() {
        topo = null;
        tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public Jogador getJogador(int x) throws Exception {
        if (x < 0 || x >= tamanho) {
            throw new Exception("Invalid position");
        }

        Celula atual = topo;
        for (int i = 0; i < x; i++) {
            atual = atual.prox;
        }

        return atual.j;
    }

    public Celula getTopo() throws Exception {
        if (topo == null) {
            throw new Exception("Stack is empty");
        }
        return topo;
    }

    public Jogador getJogadorTopo() throws Exception {
        if (topo == null) {
            throw new Exception("Stack is empty");
        }
        return topo.j;
    }

    public void empilhar(Jogador j) {
        Celula novaCelula = new Celula(j);
        novaCelula.prox = topo;
        topo = novaCelula;
        tamanho++;
    }

    public Jogador desempilhar() throws Exception {
        if (topo == null) {
            throw new Exception("Stack is empty");
        }

        Jogador removido = topo.j;
        Celula tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tamanho--;

        return removido;
    }

    public void mostrarRecursivo(Celula atual, int cont) {
        if (atual != null) {
            tamanho = tamanho - 1;
            mostrarRecursivo(atual.prox, tamanho);
            System.out.println(atual.j.customString(cont));
        }
    }
}

class CelulaDupla {
    public Jogador j;
    public CelulaDupla ant, prox;

    public CelulaDupla(Jogador jogador) {
        this.j = jogador;
        this.ant = null;
        this.prox = null;
    }
}

class ListaDuplamenteEncadeada {

    private CelulaDupla primeiro, ultimo;
    private int tamanho;

    public ListaDuplamenteEncadeada() {
        primeiro = null;
        ultimo = null;
        tamanho = 0;
    }

    public int getTamanho() {
        return tamanho;
    }

    public CelulaDupla getPrimeiro() {
        return primeiro;
    }

    public Jogador getJogador(int x) throws Exception {
        if (x < 0 || x >= tamanho) {
            throw new Exception("Invalid position");
        }

        CelulaDupla atual = primeiro;
        for (int i = 0; i < x; i++) {
            atual = atual.prox;
        }

        return atual.j;
    }

    public void inserirInicio(Jogador j) {
        CelulaDupla novaCelula = new CelulaDupla(j);
        if (primeiro == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            novaCelula.prox = primeiro;
            primeiro.ant = novaCelula;
            primeiro = novaCelula;
        }
        tamanho++;
    }

    public void inserirFim(Jogador j) {
        CelulaDupla novaCelula = new CelulaDupla(j);
        if (ultimo == null) {
            primeiro = novaCelula;
            ultimo = novaCelula;
        } else {
            ultimo.prox = novaCelula;
            novaCelula.ant = ultimo;
            ultimo = novaCelula;
        }
        tamanho++;
    }

    public void inserir(Jogador j, int x) throws Exception {
        if (x < 0 || x > tamanho) {
            throw new Exception("Invalid position");
        }

        if (x == 0) {
            inserirInicio(j);
        } else if (x == tamanho) {
            inserirFim(j);
        } else {
            CelulaDupla novaCelula = new CelulaDupla(j);
            CelulaDupla atual = primeiro;

            for (int i = 0; i < x - 1; i++) {
                atual = atual.prox;
            }

            novaCelula.prox = atual.prox;
            novaCelula.ant = atual;
            atual.prox.ant = novaCelula;
            atual.prox = novaCelula;
            tamanho++;
        }
    }

    public Jogador removerInicio() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }

        Jogador removido = primeiro.j;
        primeiro = primeiro.prox;

        if (primeiro != null) {
            primeiro.ant = null;
        } else {
            ultimo = null;
        }

        tamanho--;
        return removido;
    }

    public Jogador removerFim() throws Exception {
        if (tamanho <= 0) {
            throw new Exception("List is empty");
        }

        Jogador removido = ultimo.j;
        ultimo = ultimo.ant;

        if (ultimo != null) {
            ultimo.prox = null;
        } else {
            primeiro = null;
        }

        tamanho--;
        return removido;
    }

    public Jogador remover(int x) throws Exception {
        if (tamanho <= 0 || x < 0 || x >= tamanho) {
            throw new Exception("Invalid position or list is empty");
        }

        Jogador removido;
        if (x == 0) {
            removido = removerInicio();
        } else if (x == tamanho - 1) {
            removido = removerFim();
        } else {
            CelulaDupla atual = primeiro;
            for (int i = 0; i < x - 1; i++) {
                atual = atual.prox;
            }

            removido = atual.prox.j;
            atual.prox = atual.prox.prox;
            atual.prox.ant = atual;
            tamanho--;
        }

        return removido;
    }

    private int[] counts = new int[2];

    public void quicksort() {
        long start = System.currentTimeMillis();
        quicksort(this.primeiro, this.ultimo);
        long end = System.currentTimeMillis();
        Jogador.log("_quicksort2.txt", end - start, counts[0], counts[1]);
    }

    private void trocarCelulas(CelulaDupla celula1, CelulaDupla celula2) {
        Jogador temp = celula1.j;
        celula1.j = celula2.j;
        celula2.j = temp;
        counts[1] += 3;
    }

    private CelulaDupla partition(CelulaDupla low, CelulaDupla high) {
        Jogador pivot = high.j;

        CelulaDupla i = low.ant;

        for (CelulaDupla j = low; j != high; j = j.prox) {
            counts[0]++;
            if (compareJogador(j.j, pivot) <= 0) {
                i = (i == null) ? low : i.prox;
                trocarCelulas(i, j);
            }
        }

        i = (i == null) ? low : i.prox;
        trocarCelulas(i, high);

        return i;
    }

    private void quicksort(CelulaDupla low, CelulaDupla high) {
        if (low != null && high != null && low != high && low.ant != high) {
            CelulaDupla pivot = partition(low, high);

            quicksort(low, pivot.ant);
            quicksort(pivot.prox, high);
        }
    }

    private int compareJogador(Jogador j1, Jogador j2) {
        int estadoComparison = j1.getEstadoNascimento().compareTo(j2.getEstadoNascimento());

        if (estadoComparison == 0) {
            return j1.getNome().compareTo(j2.getNome());
        }

        return estadoComparison;
    }

}

class No {
    Jogador j;
    No esq;
    No dir;

    public No(Jogador j) {
        this.j = j;
        this.esq = null;
        this.dir = null;
    }
}

class Arvore {
    No raiz;

    public Arvore() {
        this.raiz = null;
    }

    public void inserir(Jogador j) throws Exception {
        raiz = inserir(j, raiz);
    }

    public No inserir(Jogador x, No i) throws Exception {
        if (i == null) {
            i = new No(x);
        } else if (x.getNome().compareTo(i.j.getNome()) < 0) {
            i.esq = inserir(x, i.esq);
        } else if (x.getNome().compareTo(i.j.getNome()) > 0) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Jogador with name '" + x.getNome() + "' already exists!");
        }

        return i;
    }

    public String pesquisar(String nome) {
        return pesquisar(raiz, nome);
    }

    private String pesquisar(No no, String nome) {
        if (no == null) {
            return "NAO ";
        }

        int comparacao = nome.compareTo(no.j.getNome());

        if (comparacao < 0) {
            return "esq " + pesquisar(no.esq, nome);
        } else if (comparacao > 0) {
            return "dir " + pesquisar(no.dir, nome);
        } else {
            return "SIM ";
        }
    }
}

class HashDireta {
    int area_principal = 21;
    int area_reserva = 9;

    Jogador tam[];
    Jogador reserva[];

    int tam_reserva;

    public HashDireta() {
        this.tam = new Jogador[area_principal];
        this.reserva = new Jogador[area_principal];
        this.tam_reserva = 0;
    }

    public int hash(int i) {
        return Math.abs(i) % 21;
    }

    public void inserir(Jogador j) {
        int chave = hash(j.getAltura());
        if (tam[chave] == null) {
            tam[chave] = j;
        } else if (tam[chave] != null) {
            if (tam_reserva < area_reserva) {
                reserva[tam_reserva++] = j;
            }
        }
    }

    public boolean pesquisar(int i, String s) {
        int chave = hash(i);
        if (tam[chave] == null) {
            return false;
        } else if (tam[chave].getNome().compareTo(s) == 0) {
            return true;
        } else {
            for (int j = 0; j < area_reserva; j++) {
                if (reserva[j].getNome().compareTo(s) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

class HashRehash {
    int area_principal = 25;

    Jogador tam[];

    public HashRehash() {
        this.tam = new Jogador[area_principal];
    }

    public int hash(int i) {
        return Math.abs(i) % 25;
    }

    public int rehash(int i) {
        return Math.abs(i + 1) % 25;
    }

    public void inserir(Jogador j) {
        int chave = hash(j.getAltura());
        if (tam[chave] == null) {
            tam[chave] = j;
        } else if (tam[chave] != null) {
            int chave_rehash = rehash(j.getAltura());
            if (tam[chave_rehash] == null) {
                tam[chave_rehash] = j;
            }
        }
    }

    public boolean pesquisar(int i, String s) {
        int chave = hash(i);
        if (tam[chave] == null) {
            return false;
        } else if (tam[chave].getNome().compareTo(s) == 0) {
            return true;
        } else {
            int chave_rehash = rehash(i);
            if (tam[chave_rehash] == null) {
                return false;
            } else if (tam[chave_rehash].getNome().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }
}

class NoAN {
    public boolean cor;
    public Jogador jogador;
    public NoAN esq, dir;

    public NoAN() {
        this(new Jogador());
    }

    public NoAN(Jogador j) {
        this(j, false, null, null);
    }

    public NoAN(Jogador j, boolean cor) {
        this(j, cor, null, null);
    }

    public NoAN(Jogador j, boolean cor, NoAN esq, NoAN dir) {
        this.cor = cor;
        this.jogador = j;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra {
    private NoAN raiz;

    public Alvinegra() {
        raiz = null;
    }

    public String pesquisar(Jogador j) {
        return pesquisar(j, raiz);
    }

    private String pesquisar(Jogador j, NoAN i) {
        if (i == null) {
            return "NAO";
        } else if (j.getNome().compareTo(i.jogador.getNome()) == 0) {
            return "SIM";
        } else if (j.getNome().compareTo(i.jogador.getNome()) < 0) {
            return "esq " + pesquisar(j, i.esq);
        } else {
            return "dir " + pesquisar(j, i.dir);
        }
    }

    public void inserir(Jogador j) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(j);

            // Senao, se a arvore tiver um jogador
        } else if (raiz.esq == null && raiz.dir == null) {
            if (j.getAltura() < raiz.jogador.getAltura()) {
                raiz.esq = new NoAN(j);
            } else {
                raiz.dir = new NoAN(j);
            }

            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (j.getAltura() < raiz.jogador.getAltura()) {
                raiz.esq = new NoAN(j);

            } else if (j.getAltura() < raiz.dir.jogador.getAltura()) {
                raiz.esq = new NoAN(raiz.jogador);
                raiz.jogador = j;

            } else {
                raiz.esq = new NoAN(raiz.jogador);
                raiz.jogador = raiz.dir.jogador;
                raiz.dir.jogador = j;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (j.getAltura() > raiz.jogador.getAltura()) {
                raiz.dir = new NoAN(j);

            } else if (j.getAltura() > raiz.esq.jogador.getAltura()) {
                raiz.dir = new NoAN(raiz.jogador);
                raiz.jogador = j;

            } else {
                raiz.dir = new NoAN(raiz.jogador);
                raiz.jogador = raiz.esq.jogador;
                raiz.esq.jogador = j;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } else {
            inserir(j, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.jogador.getAltura() > avo.jogador.getAltura()) { // rotacao a esquerda ou direita-esquerda
                if (i.jogador.getAltura() > pai.jogador.getAltura()) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.jogador.getAltura() < pai.jogador.getAltura()) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.jogador.getAltura() < bisavo.jogador.getAltura()) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        }
    }

    private void inserir(Jogador j, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        if (i == null) {
            if (j.getAltura() < pai.jogador.getAltura()) {
                i = pai.esq = new NoAN(j, true);
            } else {
                i = pai.dir = new NoAN(j, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (j.getAltura() < i.jogador.getAltura()) {
                inserir(j, avo, pai, i, i.esq);
            } else if (j.getAltura() > i.jogador.getAltura()) {
                inserir(j, avo, pai, i, i.dir);
            }
        }
    }

    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}