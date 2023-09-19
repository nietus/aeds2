public class MergeSort {

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    private void mergeSort(int[] arr, int esq, int dir) {
        if(esq < dir){
            int meio = (esq + dir) / 2;
            mergeSort(arr, esq, meio);
            mergeSort(arr, meio + 1, dir);
            intercalar(arr,esq,meio,dir);
        }   
    }
    void intercalar(int[] arr,int esq, int meio, int dir){
        int nEsq = (meio+1)-esq, nDir = dir - meio, iEsq, iDir, i;
        int[] arrEsq = new int[nEsq+1];
        int[] arrDir = new int[nDir+1];
        arrEsq[nEsq] = arrDir[nDir] = 0x7FFFFFFF; // sentinel marks the end of each subarray (greatest possible number)
        for (iEsq = 0; iEsq < nEsq; iEsq++){
           arrEsq[iEsq] = arr[esq+iEsq];
        }
        for (iDir = 0; iDir < nDir; iDir++){
           arrDir[iDir] = arr[(meio+1)+iDir];
        }
        for (iEsq = iDir = 0, i = esq; i <= dir; i++){
           arr[i] = (arrEsq[iEsq] <= arrDir[iDir]) ? arrEsq[iEsq++] : arrDir[iDir++];
        }
   }
}