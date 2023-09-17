public class ShellSort {
    public void sort(int[] arr, int n){
        int h = 1; // Space between each color's element;
        do{
            h = (h * 3) + 1; // Knuth's recommendation
        } while(h < n);
        do{
            h /= 3;
            for(int cor = 0; cor < h; cor++){
                colorInsertion(arr, cor, h, n);
            }
        }while(h != 1);
    }
    public void colorInsertion(int[] arr,int cor, int h, int n){
        for(int i = h + cor;i < n; i++){
            int tmp = arr[i];
            int j = i - h;
            while(j >= 0 && arr[j] > tmp){
                arr[j + h] = arr[j];
                j -= h;
            }
            arr[j + h] = tmp;
        }
    }
}
