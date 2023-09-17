public class Bubble {
    public void sort(int[] arr, int n){
        for(int i = (n-1); i>0; i--){
            for(int j = 0; j < i; j++){
                if(arr[j] > arr[j + 1]){
                    Swap.swap(arr,j,j+1);
                }
            }
        }
    }
}