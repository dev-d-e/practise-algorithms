package sorts

//HeapSort []int
func HeapSort(arr []int) {
	n := len(arr) - 1
	for i := n / 2; i >= 0; i-- {
		sink(arr, i, n)
	}
	for n > 0 {
		arr[0], arr[n] = arr[n], arr[0]
		n--
		sink(arr, 0, n)
	}
}

func sink(arr []int, i, n int) {
	for j := 2*(i+1) - 1; j <= n; j = 2*(i+1) - 1 {
		if j < n && arr[j] < arr[j+1] {
			j++
		}
		if arr[i] > arr[j] {
			break
		}
		arr[i], arr[j] = arr[j], arr[i]
		i = j
	}
}
