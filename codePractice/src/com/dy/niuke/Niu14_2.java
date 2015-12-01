package com.dy.niuke;

public class Niu14_2 {
	public static int getMaxSum(int[] a) {
		//记录当前的和
		int sum = 0;
		//记录最大值
		int maxNum = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			//如果和小于0，说明加上正数或会变大，但是仍然起副作用
			//因此直接丢掉，使得sum为当前值，注意不是0，因为可能所有数字都是负数
			if (sum < 0) {
				sum = a[i];
			} else {
				//和大于0，可以继续累加
				sum += a[i];
			}
			//每次算完和，要更新max，这样才能记录下最大值
			maxNum = Math.max(maxNum, sum);
		}
		return maxNum;
	}

	public static void main(String[] args) {
		int[] a = {1,-2,3,5,-2,6,-1};
		 int[] data2 = {-2, -8, -1, -5, -9};
	        int[] data3 = {2, 8, 1, 5, 9};
		System.out.println(getMaxSum(a));
		System.out.println(getMaxSum(data3));
		System.out.println(getMaxSum(data2));

	}
}
