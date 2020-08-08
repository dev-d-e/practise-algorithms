package sorts

//QuickSort []int
func QuickSort(arr []int) {
	quickSort(arr, 0, len(arr)-1)
}

func quickSort(arr []int, m, n int) {
	if m < n {
		i := partition(arr, m, n)
		quickSort(arr, m, i-1)
		quickSort(arr, i+1, n)
	}
}

func partition(arr []int, m, n int) int {
	i, j := m+1, n
	for {
		for ; arr[i] < arr[m] && i <= n; i++ {

		}
		for ; arr[j] > arr[m] && j >= m; j-- {

		}
		if i >= j {
			break
		}
		arr[i], arr[j] = arr[j], arr[i]
	}
	arr[m], arr[j] = arr[j], arr[m]
	return j
}
