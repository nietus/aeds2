public class Selecao {
    public void sort(int[] arr, int n){
        for(int i = 0; i < (n - 1);i++){
            int menor = 0;
            for(int j = i + 1;j < n; j++){
                if(arr[menor] > arr[j]){
                    menor = j;
                }
            }
            Swap.swap(arr, i, menor);
        }
    }
}
