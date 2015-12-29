package com.dy.others;


public class QuickSort {
	public static int partition(int[] a, int left, int right){
		//��������
		int tmp = a[left];
		while(left < right){
			while(a[right] > tmp && left <right){
				right--;
			}
			a[left] = a[right];
			while(a[left] < tmp && left < right){
				left++;
			}
			a[right] = a[left];
		}
		//Ϊ����ֵ
		a[left] = tmp;
		//����������λ��
		return left;
	}
	
	public static void quickSort(int[] a, int left, int right){
		if(left < right){
			//pos����С������pos�Ҷ�������
			int pos = partition(a, left, right);
			quickSort(a, left, pos - 1);
			quickSort(a, pos + 1, right);
		}
	}
	public static void main(String[] args) {
		int[] a = {11,4,22,5,7,6,9};
		quickSort(a, 0, a.length-1);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]+" ");
		}
	}

}
