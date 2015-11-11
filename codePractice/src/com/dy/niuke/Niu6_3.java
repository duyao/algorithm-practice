package com.dy.niuke;

public class Niu6_3 {
	public static int smallSum = 0;
	public static int getSmallSum(int[] a){
		
		if(a.length == 1 || a == null || a.length == 0){
			return 0;
		} 
		
		merge(a, 0, a.length-1);
		return smallSum;
	}
	
	public static int merge(int[] a, int low, int high){
		if(low == high){
			return 0;
		}
		int mid = (low + high)/2;
		int start1 = low, end1 = mid;
		int start2 = mid+1, end2 = high;
		
		smallSum += merge(a, start1, end1);
		smallSum += merge(a, start2, end2);
		
		int k = 0;
		int[] reg = new int[high-low+1];
		int tmpSum = 0;
		while(start1 <= end1 && start2 <= end2){
			//左边小于等于右边，算小和且排序
			if(a[start1] <= a[start2]){
				tmpSum += a[start1]*(end2-start2+1);
				reg[k++] = a[start1++];
			}else{
				//只排序，不算和
				reg[k++] = a[start2++];
			}
			
		}
		while(start1 <= end1){
			reg[k++] = a[start1++];
		}
		while(start2 <= end2){
			reg[k++] = a[start2++];
		}
		for(int i = low, j = 0; i <= high && j<=k; i++,j++){
			a[i] = reg[j];
		}
		return tmpSum;
	}
	
	public static void main(String[] args) {
		int[] a = {1,3,5,2,4,6};
		//int[] a = {1,2,3,4,5,6};
		int ans = getSmallSum(a);
		System.out.println(ans);
	}

}
