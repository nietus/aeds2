import java.util.Scanner;

public class MenorMaior {
    public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int[] dezNumeros = new int[10];
    int maior = 0;
    int menor = 0;
    for(int i = 0; i < 10; i++){
        System.out.print("Num " + (i+1) + ": ");
        dezNumeros[i] = scan.nextInt();
    }
    for(int j = 1; j < 10;j++){
        if(dezNumeros[j] > (dezNumeros[j] - 1)) maior = dezNumeros[j];
        if(dezNumeros[j] < (dezNumeros[j] - 1)) menor = dezNumeros[j];
    }
    System.out.println("Maior: " + maior + " / " + "Menor: " + menor);
    scan.close();
 }
}