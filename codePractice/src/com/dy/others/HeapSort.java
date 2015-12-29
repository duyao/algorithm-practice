package com.dy.others;


public class HeapSort {
	public static void maxHeapy(int[] a, int start, int end){
		//dad��ͷ��������end/2���������ܱ��ֺ�����Ƕѣ���Ȼÿ�ζ�ֻ���ж����һ��
		int dad = start;
		//���ӣ���Ϊ��0��ʼ
		int son = dad * 2 + 1;
		while(dad < son && son <= end){
			//ȡ���Һ����нϴ��һ��
			if(son + 1 <= end && a[son+1] > a[son]){
				son = son + 1;
			}
			if(a[son] > a[dad]){
				swap(a, dad, son);
				//���������Ƶ�����λ�ã������������Ա�֤��������Ȼ�Ƕ�
				dad = son;
				son = 2 * son + 1;
			}else{
				return;
			}
		}
	}
	public static void heapSort(int[] a){
		//��Ϊ��0��ʼ�����е�һ����Ҷ����(a.length-1)/2
		for(int i = (a.length-1)/2; i >= 0; i--){
			maxHeapy(a, i, a.length-1);
		}
		//��һ�ν����󶥶����
		for(int i = a.length - 1; i > 0; i--){
			//�����Ѷ������һ�������Ѷ������ֵ���������ֵ�������
			swap(a, 0, i);
			//�ٴ����г���ĩβ���������
			maxHeapy(a, 0, i-1);
		}
	}
	
	public static void swap(int[] a, int x, int y){
		int tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	public static void main(String[] args) {
		int[] a = {0,11,4,22,5,7,6,9};
		heapSort(a);
		for (int i = 1; i < a.length; i++) {
			System.out.println(a[i]+" ");
		}
	}


}
