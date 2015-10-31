package com.dy.niuke;

public class Niu3_1 {
	public int find(int []a){
		if(a[0] < a[1] || a.length == 1){
			return 0;
		}else if(a[a.length-2] > a[a.length-1]){
			return a.length-1;
		}else{
			int mid = 0;
			int left = 1;
			int right = a.length-2;
			while(left < right){
				mid = (left + right)/2;
				if(a[mid-1] < a[mid]){
					right = mid-1;
				}else if(a[mid] > a[mid+1]){
					left = mid+1;
				}else{
					//m-1>=m<=m+1,显然m是局部最小
					return mid;
				}
			}
			return mid;
		}
	}
	
	
	public int nn(int []arr){
		if (arr == null || arr.length == 0) {
			return -1; // no exist
		}
		if (arr.length == 1 || arr[0] < arr[1]) {
			return 0;
		}
		if (arr[arr.length - 1] < arr[arr.length - 2]) {
			return arr.length - 1;
		}
		int left = 1;
		int right = arr.length - 2;
		int mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) {
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return left;
	}
}
