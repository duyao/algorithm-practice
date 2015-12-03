package com.dy.niuke;
public class Niu15_1 {
	
	public static int getMaxKLen(int[] a,int k){
		if(a.length < 0 || k <= 0 || a == null){
			return 0;
		}
		int left = 0;
		int right = 0;
		//这里sum已经设置了，后面要先改区间，再算和
		int sum = a[0];
		int len = 0;
		while(left < a.length && right < a.length) {
			if(sum < k){
				//先改区间，再算和
				right ++;
				if (right == a.length) {
					break;
				}
				sum += a[right];
			}else if(sum > k){
				//先改区间，再算和
				left++;
				if (left == a.length) {
					break;
				}
				sum -= a[left];
			}else{
				//sum== k
				len = Math.max(len, right - left + 1);
				left++;
				sum -= a[left];
				
			}
			
		}
		return len;
	}

	public static int[] generatePositiveArray(int size) {
		int[] result = new int[size];
		for (int i = 0; i != size; i++) {
			result[i] = (int) (Math.random() * 10) + 1;
		}
		return result;
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int len = 20;
		int k = 1;
		int[] arr = generatePositiveArray(len);
		printArray(arr);
		System.out.println(getMaxKLen(arr, k));

	}

}
