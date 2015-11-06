package com.dy.niuke;

public class Niu5_2 {

	//得到0-k大的数组
	public int[] getMinKNumsByBFPRT(int[] arr, int k) {
		if (k < 1 || k > arr.length) {
			return arr;
		}
		int minKth = getMinKthByBFPRT(arr, k);
		int[] res = new int[k];
		int index = 0;
		for (int i = 0; i != arr.length; i++) {
			if (arr[i] < minKth) {
				res[index++] = arr[i];
			}
		}
		for (; index != res.length; index++) {
			res[index] = minKth;
		}
		return res;
	}

	//得到第k个大的数字
	public int getMinKthByBFPRT(int[] arr, int K) {
		int[] copyArr = copyArray(arr);
		return select(copyArr, 0, copyArr.length - 1, K - 1);
	}

	public int[] copyArray(int[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i != res.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	//返回值是int，选择i位置的数字，返回结果
	public int select(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		//选择中位数的中位数
		int pivot = medianOfMedians(arr, begin, end);
		//找到与pivot相等的数组下标
		int[] pivotRange = partition(arr, begin, end, pivot);
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		} else if (i < pivotRange[0]) {
			//如果没命中就去找比pivot小的中位数
			//pivotRange[0]是与pivot值相等的左边位置
			return select(arr, begin, pivotRange[0] - 1, i);
		} else {
			//pivotRange[1]是与pivot值相等的右边位置
			return select(arr, pivotRange[1] + 1, end, i);
		}
	}

	//找中位数的中位数
	public int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		//不是5的倍数，就要多一组
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			//mArr是中位数的集合
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		//选择中位数组中的中位数
		return select(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	
	
	/**
	 * @param pivotValue 选择的中间位置
	 * partition的目的是利用快排将arr进行分区
	 * 左边比pivotValue小，右边比pivotValue大，中间是与pivotValue相等的
	 * @return 返回值是int数组，返回的是与pivotValue相等的数组下标
	 */
	public int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		//快排原理，将arr分区，
		//左边是比pivotValue小的,右边比pivotValue大，中间的和pivotValue等
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		//得到与pivotValue相等的位置的坐标范围
		int[] range = new int[2];
		//最左边
		range[0] = small + 1;
		//最右边
		range[1] = big - 1;
		return range;
	}

	//得到中间位置的数字
	public int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	//插入排序
	public void insertionSort(int[] arr, int begin, int end) {
		for (int i = begin + 1; i != end + 1; i++) {
			for (int j = i; j != begin; j--) {
				if (arr[j - 1] > arr[j]) {
					swap(arr, j - 1, j);
				} else {
					break;
				}
			}
		}
	}

	public void swap(int[] arr, int index1, int index2) {
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

}
