package com.dy.niuke;

public class Niu6_3 {
	
	public static int getSmallSum(int[] a){
		
		if(a.length == 1 || a == null || a.length == 0){
			return 0;
		} 
		
		int ans = merge(a, 0, a.length-1);
		return ans;
	}
	
	public static int merge(int[] a, int low, int high){
		if(low == high){
			return 0;
		}
		
		int mid = (low + high)/2;
		int start1 = low, end1 = mid;
		int start2 = mid+1, end2 = high;
		//和归并排序相同，就是算小和不仅排序还要算和
		return merge(a, start1, end1) + merge(a, start2, end2) + sum(a, low, high);
		
		
		//由于要算小和，每次相加，因此不能像归并排序那样，把后面的排序也写进来，必须分开写，才能算和
//		int k = 0;
//		int[] reg = new int[high-low+1];
//		int tmpSum = 0;
//		//归并排序中，下面的步骤是排序，这里是算小和加排序
//		while(start1 <= end1 && start2 <= end2){
//			//左边小于等于右边，算小和且排序
//			if(a[start1] <= a[start2]){
//				tmpSum += a[start1]*(end2-start2+1);
//				reg[k++] = a[start1++];
//			}else{
//				//只排序，不算和
//				reg[k++] = a[start2++];
//			}
//			
//		}
//		while(start1 <= end1){
//			reg[k++] = a[start1++];
//		}
//		while(start2 <= end2){
//			reg[k++] = a[start2++];
//		}
//		for(int i = low, j = 0; i <= high && j<=k; i++,j++){
//			a[i] = reg[j];
//		}
//		return tmpSum;
	}
	
	public static int sum(int[] a, int low, int high){
		
		int mid = (low + high)/2;
		int start1 = low, end1 = mid;
		int start2 = mid+1, end2 = high;
		
		//k是reg数组的位置
		int k = 0;
		//reg存放排好序的新数组
		int[] reg = new int[high-low+1];
		//tmpSum小和
		int tmpSum = 0;
		//归并排序中，下面的步骤是排序，这里是算小和加排序
		while(start1 <= end1 && start2 <= end2){
			//左边小于等于右边，算小和且排序
			if(a[start1] <= a[start2]){
				//注意这里是小的数字乘以比其大的数字的个数
				tmpSum += a[start1]*(end2-start2+1);
				//排序
				reg[k++] = a[start1++];
			}else{
				//只排序，不算和
				reg[k++] = a[start2++];
			}
			
		}
		//剩余的排序
		while(start1 <= end1){
			reg[k++] = a[start1++];
		}
		while(start2 <= end2){
			reg[k++] = a[start2++];
		}
		
		for(int i = low, j = 0; i <= high && j<=k; i++,j++){
			//把排好序的数组赋值给a
			a[i] = reg[j];
		}
		return tmpSum;
	}
	
	public static void main(String[] args) {
		//int[] a = {1,3,5,2,4,6};
		int[] a = {1,2,3,4,5,6};
		int ans = getSmallSum(a);
		System.out.println(ans);
	}

}
