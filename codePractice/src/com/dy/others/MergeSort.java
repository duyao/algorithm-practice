package com.dy.others;

public class MergeSort {
	public static void mergeSort(int[] a, int left, int right){
		if(left < right){
			int mid = (left + right) / 2;
			//����
			mergeSort(a, left, mid);
			mergeSort(a, mid + 1, right);
			//�ϲ�
			merge(a, left, mid, mid+1, right);
		}
		
	}
	public static void merge(int[] a, int l1, int r1, int l2, int r2){
		int i = l1, j = l2;
		int[] temp = new int[a.length];
		int index = 0;
		while(i <= r1 && j <= r2){
			if(a[i] <= a[j]){
				temp[index++] = a[i++];
			}else{
				temp[index++] = a[j++];
			}
		}
		//ʣ��ĺϲ�
		while(i <= r1){
			temp[index++] = a[i++];
		}
		while(j <= r2){
			temp[index++] = a[j++];
		}
		//д�ص�a��
		for(int k = 0; k < index; k++){
			//����ͷ��l1+k
			a[l1+k] = temp[k];
		}
	}
	
	public static void main(String[] args) {
		int[] a = {11,4,22,5,7,6,9};
		mergeSort(a, 0, a.length-1);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]+" ");
		}
	}

}
