package sorts

//InsertionSort []int
func InsertionSort(arr []int) {
	for i := 1; i < len(arr); i++ {
		o, j := arr[i], i-1
		for j >= 0 && arr[j] > o {
			arr[j+1] = arr[j]
			j--
		}
		arr[j+1] = o
	}
}
