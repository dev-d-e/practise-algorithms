package sorts;

public class Selection {

	public static void sort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			int o = arr[i];
			arr[i] = arr[min];
			arr[min] = o;
		}
	}

}
