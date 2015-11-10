package com.dy.niuke;

public class Niu6_1 {

	public int missNum(int[] arr) {
		int l = 0;
		int r = arr.length;
		while (l < r) {

			if (arr[l] == l + 1) {
				// ��õ�ʱ����һ��Ҫж����ǰ�棬��������a[0]=1,ƥ�䵽arr[l] == arr[arr[l] - 1]���´���
				l++;
			} else if (arr[l] > r || arr[l] <= l || arr[l] == arr[arr[l] - 1]) {
				// ���߽���ߵõ��ظ�ֵ
				swap(arr, l, --r);
			} else if (arr[l] != arr[arr[l] - 1]) {
				// �õ������ڼ��ֵ������������ֵ l+1����˽�����֪���õ�����ֵ l+1Ϊֹ
				swap(arr, l, arr[l] - 1);
			}
		}
		return l + 1;

		
	}

	public void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
		return;
	}

	public static void main(String[] args) {
		Niu6_1 niu6_1 = new Niu6_1();
		// int[] a = {3,412,23,2,76,47,23,9,23,54,83};
		//int[] a = { 3, 1, 3, 4, 2 };
		int[] a={-1,2,3,4};
		int k = niu6_1.missNum(a);
		System.out.println(k);
	}
}
