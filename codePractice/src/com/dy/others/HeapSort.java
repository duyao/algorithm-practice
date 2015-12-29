package com.dy.others;


public class HeapSort {
	public static void maxHeapy(int[] a, int start, int end){
		//dad是头，而不是end/2，这样才能保持后面的是堆，不然每次都只是判断最后一组
		int dad = start;
		//左孩子，因为从0开始
		int son = dad * 2 + 1;
		while(dad < son && son <= end){
			//取左右孩子中较大的一个
			if(son + 1 <= end && a[son+1] > a[son]){
				son = son + 1;
			}
			if(a[son] > a[dad]){
				swap(a, dad, son);
				//将父亲下移到儿子位置，继续操作，以保证交换后仍然是堆
				dad = son;
				son = 2 * son + 1;
			}else{
				return;
			}
		}
	}
	public static void heapSort(int[] a){
		//因为从0开始，所有第一个非叶子是(a.length-1)/2
		for(int i = (a.length-1)/2; i >= 0; i--){
			maxHeapy(a, i, a.length-1);
		}
		//第一次建立大顶堆完成
		for(int i = a.length - 1; i > 0; i--){
			//交换堆顶和最后一个数，堆顶是最大值，即将最大值放在最后
			swap(a, 0, i);
			//再次排列除了末尾以外的数字
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
