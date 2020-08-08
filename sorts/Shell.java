package sorts;

public class Shell {

	public static void sort(int[] arr) {
		int n = arr.length;
		int i = 1;
		while (i < n / 3) {
			i = 3 * i + 1;
		}
		while (i >= 1) {
			for (int j = i; j < n; j++) {
				for (int k = j; k >= i && arr[k] < arr[k - i]; k -= i) {
					int o = arr[k];
					arr[k] = arr[k - i];
					arr[k - i] = o;
				}
			}
			i = i / 3;
		}
	}

}
