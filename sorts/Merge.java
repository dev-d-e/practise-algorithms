package sorts;

public class Merge {

	private static int[] arr2;

	public static void sort(int[] arr) {
		arr2 = new int[arr.length];
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int m, int n) {
		if (m < n) {
			int i = m + (n - m) / 2;
			sort(arr, m, i);
			sort(arr, i + 1, n);
			merge(arr, m, i, n);
		}
	}

	private static void merge(int[] arr, int m, int i, int n) {
		int j = m, k = i + 1;
		for (int t = m; t <= n; t++) {
			arr2[t] = arr[t];
		}
		for (int t = m; t <= n; t++) {
			if (j > i) {
				arr[t] = arr2[k];
				k++;
			} else if (k > n) {
				arr[t] = arr2[j];
				j++;
			} else if (arr2[j] > arr2[k]) {
				arr[t] = arr2[k];
				k++;
			} else {
				arr[t] = arr2[j];
				j++;
			}
		}
	}

}
