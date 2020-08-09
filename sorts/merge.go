package sorts

//MergeSort []int
func MergeSort(arr []int) {
	arr2 := make([]int, len(arr))
	mergeSort(arr, 0, len(arr)-1, arr2)
}

func mergeSort(arr []int, m, n int, arr2 []int) {
	if m < n {
		i := m + (n-m)/2
		mergeSort(arr, m, i, arr2)
		mergeSort(arr, i+1, n, arr2)
		merge(arr, m, i, n, arr2)
	}

}

func merge(arr []int, m, i, n int, arr2 []int) {
	j, k := m, i+1
	for t := m; t <= n; t++ {
		arr2[t] = arr[t]
	}
	for t := m; t <= n; t++ {
		if j > i {
			arr[t] = arr2[k]
			k++
		} else if k > n {
			arr[t] = arr2[j]
			j++
		} else if arr2[j] > arr2[k] {
			arr[t] = arr2[k]
			k++
		} else {
			arr[t] = arr2[j]
			j++
		}
	}
}
