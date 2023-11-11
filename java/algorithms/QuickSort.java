package algorithms;

public class QuickSort {
    public static void quickSort(int arr[], int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivo = arr[(i + j) / 2];
        while (i <= j) {
            while (arr[i] < pivo) {
                i++;
            }
            while (arr[j] > pivo) {
                j--;
            }
            if (i <= j) {
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux;
                i++;
                j--;
            }
        }
        if (esq < j) {
            quickSort(arr, esq, j);
        }
        if (i < dir) {
            quickSort(arr, i, dir);
        }
    }

    public static void display(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " | ");
        }
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 6, 3, 1, 2, 4, 5, 2, 6, 7 };
        quickSort(array, 0, array.length - 1);
        display(array);
    }
}
