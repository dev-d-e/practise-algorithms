package sorts

//ShellSort []int
func ShellSort(arr []int) {
	n := len(arr)
	i := 1
	for i < n/3 {
		i = 3*i + 1
	}
	for i >= 1 {
		for j := i; j < n; j++ {
			for k := j; k >= i && arr[k] < arr[k-i]; k -= i {
				arr[k], arr[k-i] = arr[k-i], arr[k]
			}
		}
		i = i / 3
	}
}
