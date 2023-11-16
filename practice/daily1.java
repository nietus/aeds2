package practice;

class daily1 {

    // Given a list of numbers and a number k, return whether any two numbers from
    // the list add up to k.

    // For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is
    // 17.

    public static void main(String[] args) {

        int arr[] = { 10, 15, 3, 7 };
        int k = 17;
        int aux[] = new int[arr.length];
        int i = 0;

        for (int num : arr) {
            int compare = k - num;
            aux[i++] = compare;
            for(int n: aux){
                if(num == n){
                    System.out.println("Found!");
                }
            }
        }
    }
}
