package sorts;

public class Heap {

	public static void sort(int[] arr) {
		int n = arr.length - 1;
		for (int i = n / 2; i >= 0; i--) {
			sink(arr, i, n);
		}
		while (n > 0) {
			int t = arr[0];
			arr[0] = arr[n];
			arr[n] = t;
			n--;
			sink(arr, 0, n);
		}
	}

	private static void sink(int[] arr, int i, int n) {
		for (int j = 2 * (i + 1) - 1; j <= n; j = 2 * (i + 1) - 1) {
			if (j < n && arr[j] < arr[j + 1])
				j++;
			if (arr[i] > arr[j])
				break;
			int t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
			i = j;
		}
	}

}
