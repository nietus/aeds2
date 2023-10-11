public class Heap {
    public static void heapsort(int[] arr) {
        int n = arr.length;
        // build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // heapsort
        for (int i = n - 1; i >= 0; i--) {
            Swap.swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void heapify(int[] arr, int heapSize, int i) {
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if (leftChild < heapSize && arr[leftChild] > arr[largest]) {
            largest = leftChild;
        }

        if (rightChild < heapSize && arr[rightChild] > arr[largest]) {
            largest = rightChild;
        }

        if (largest != i) {
            Swap.swap(arr, i, largest);
            heapify(arr, heapSize, largest);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 3, 1, 2, 4, 1};
        heapsort(arr);
        for (int c : arr) {
            System.out.print(c + " ");
        }
    }
}