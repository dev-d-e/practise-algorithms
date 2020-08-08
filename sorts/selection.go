package sorts

//SelectionSort []int
func SelectionSort(arr []int) {
	n := len(arr)
	for i := 0; i < n; i++ {
		min := i
		for j := i; j < n; j++ {
			if arr[j] < arr[min] {
				min = j
			}
		}
		arr[i], arr[min] = arr[min], arr[i]
	}
}
