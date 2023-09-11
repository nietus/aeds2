import java.io.*;

public class Arquivo {
    public static void main(String[] args) {
        try {
            // Leitura do tam
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(br.readLine());

            // Abertura do arquivo para escrita
            RandomAccessFile arquivo = new RandomAccessFile("valores.txt", "rw");

            // Leitura dos valores reais e escrita no arquivo
            for (int i = 0; i < n; i++) {
                double valor = Double.parseDouble(br.readLine());
                arquivo.writeDouble(valor);
            }

            // Fechar o arquivo
            arquivo.close();

            // Reabrir o arquivo para leitura de trás para frente
            arquivo = new RandomAccessFile("valores.txt", "r");

            // Ler e mostrar os valores de trás para frente
            for (int i = n - 1; i >= 0; i--) {
                arquivo.seek(i * 8); // Tamanho de um double em bytes
                double valor = arquivo.readDouble();
                // Verificar se o valor é um número inteiro
                if (valor == (int) valor) {
                    System.out.println((int) valor); // Imprimir como inteiro
                } else {
                    System.out.println(valor); // Caso contrário, imprimir com a parte decimal
                }
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Erro na leitura de valores reais.");
        }
    }
}
