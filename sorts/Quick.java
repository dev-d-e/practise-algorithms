package sorts;

public class Quick {

	public static void sort(int[] arr) {
		sort(arr, 0, arr.length - 1);
	}

	private static void sort(int[] arr, int m, int n) {
		if (m < n) {
			int j = partition(arr, m, n);
			sort(arr, m, j - 1);
			sort(arr, j + 1, n);
		}
	}

	private static int partition(int[] arr, int m, int n) {
		int i = m + 1;
		int j = n;
		int o = arr[m];
		while (true) {
			while (i <= n && arr[i] < o) {
				i++;
			}
			while (j >= m && arr[j] > o) {
				j--;
			}
			if (i >= j) {
				break;
			}
			int t = arr[i];
			arr[i] = arr[j];
			arr[j] = t;
		}
		arr[m] = arr[j];
		arr[j] = o;
		return j;
	}

}
