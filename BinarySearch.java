public class BinarySearch {
	public static int binarySearch(int[] array, int alvo) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == alvo) {
				return mid;
			} else if (array[mid] < alvo)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return -1;
	}
}