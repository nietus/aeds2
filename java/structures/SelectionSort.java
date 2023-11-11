package structures;

public class SelectionSort {
    public void sort(int[] arr, int n) {
        for (int i = 0; i < (n - 1); i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[menor] > arr[j]) {
                    menor = j;
                }
            }
            int aux = arr[i];
            arr[i] = arr[menor];
            arr[menor] = aux;
        }
    }
}
