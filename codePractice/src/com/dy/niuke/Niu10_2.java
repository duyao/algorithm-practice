package com.dy.niuke;

public class Niu10_2 {
	
	// 先拿时，[i,j]段获得钱数
	public static int f(int[] arr, int i, int j) {
		//对于i=j，即只有一个元素，作为先拿者，一定会拿走
		if (i == j) {
			return arr[i];
		}
		// 面对[i,j]，先拿着只能取两端的值，然后后拿剩余的值
		// 因此先拿arr[i]后拿剩余i+1的部分s(arr, i + 1, j)
		// 先拿着一定会选取最大的拿走
		return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
	}

	// 后拿时，[i,j]段获得钱数
	public static int s(int[] arr, int i, int j) {
		//对于i=j，即只有一个元素，作为后拿者，一定拿不到，所以返回0
		if (i == j) {
			return 0;
		}
		//面对[i,j]，后拿着只能先拿除了两端以外的值，是先拿，因此是f(arr, i + 1, j)
		//但是在本次，拿不到两端值，因为先拿者已经拿走，故无arr[i]+f(arr, i + 1, j)
		//也没有arr[i+1]+f(arr, i + 1, j)，因为面对的区间是[i,j]要么拿边界，要么不拿
		//由于先拿的人选择是最大的，而总和是一定的，因此，后者只能是最小min
		return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	}
	
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//先拿或者后拿的最大值
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}
	
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//f[i][j] 表示先拿的[i,j]的位置可以获得的最大钱数
		int[][] f = new int[arr.length][arr.length];
		int[][] s = new int[arr.length][arr.length];
		
		for (int j = 0; j < arr.length; j++) {
			//初始化，先拿者面对arr[j]一定会全部拿走，而后拿者只能拿走0
			f[j][j] = arr[j];
			s[j][j] = 0;
			//i=j-1而j必须以0开始，但不会报错，因为i >= 0
			for(int i = j-1; i >= 0; i--){
				f[i][j] = Math.max(arr[i] + s[i+1][j], arr[j] + s[i][j-1]);
				s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
			}
			
		}
		return Math.max(f[0][arr.length-1], s[0][arr.length-1]);
	}
	public static void main(String[] args) {
		int[] arr = { 1, 9, 1 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));

	}

}
