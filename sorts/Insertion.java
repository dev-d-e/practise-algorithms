package sorts;

public class Insertion {

	public static void sort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int o = arr[i];
			int j = i - 1;
			while (j >= 0 && arr[j] > o) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = o;
		}
	}

}
