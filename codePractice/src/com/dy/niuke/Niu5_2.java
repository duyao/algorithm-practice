package com.dy.niuke;

public class Niu5_2 {

	//�õ�0-k�������
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

	//�õ���k���������
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

	//����ֵ��int��ѡ��iλ�õ����֣����ؽ��
	public int select(int[] arr, int begin, int end, int i) {
		if (begin == end) {
			return arr[begin];
		}
		//ѡ����λ������λ��
		int pivot = medianOfMedians(arr, begin, end);
		//�ҵ���pivot��ȵ������±�
		int[] pivotRange = partition(arr, begin, end, pivot);
		if (i >= pivotRange[0] && i <= pivotRange[1]) {
			return arr[i];
		} else if (i < pivotRange[0]) {
			//���û���о�ȥ�ұ�pivotС����λ��
			//pivotRange[0]����pivotֵ��ȵ����λ��
			return select(arr, begin, pivotRange[0] - 1, i);
		} else {
			//pivotRange[1]����pivotֵ��ȵ��ұ�λ��
			return select(arr, pivotRange[1] + 1, end, i);
		}
	}

	//����λ������λ��
	public int medianOfMedians(int[] arr, int begin, int end) {
		int num = end - begin + 1;
		//����5�ı�������Ҫ��һ��
		int offset = num % 5 == 0 ? 0 : 1;
		int[] mArr = new int[num / 5 + offset];
		for (int i = 0; i < mArr.length; i++) {
			int beginI = begin + i * 5;
			int endI = beginI + 4;
			//mArr����λ���ļ���
			mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
		}
		//ѡ����λ�����е���λ��
		return select(mArr, 0, mArr.length - 1, mArr.length / 2);
	}

	
	
	/**
	 * @param pivotValue ѡ����м�λ��
	 * partition��Ŀ�������ÿ��Ž�arr���з���
	 * ��߱�pivotValueС���ұ߱�pivotValue���м�����pivotValue��ȵ�
	 * @return ����ֵ��int���飬���ص�����pivotValue��ȵ������±�
	 */
	public int[] partition(int[] arr, int begin, int end, int pivotValue) {
		int small = begin - 1;
		int cur = begin;
		int big = end + 1;
		//����ԭ����arr������
		//����Ǳ�pivotValueС��,�ұ߱�pivotValue���м�ĺ�pivotValue��
		while (cur != big) {
			if (arr[cur] < pivotValue) {
				swap(arr, ++small, cur++);
			} else if (arr[cur] > pivotValue) {
				swap(arr, cur, --big);
			} else {
				cur++;
			}
		}
		//�õ���pivotValue��ȵ�λ�õ����귶Χ
		int[] range = new int[2];
		//�����
		range[0] = small + 1;
		//���ұ�
		range[1] = big - 1;
		return range;
	}

	//�õ��м�λ�õ�����
	public int getMedian(int[] arr, int begin, int end) {
		insertionSort(arr, begin, end);
		int sum = end + begin;
		int mid = (sum / 2) + (sum % 2);
		return arr[mid];
	}

	//��������
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
