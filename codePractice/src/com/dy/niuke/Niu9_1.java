package com.dy.niuke;

public class Niu9_1 {
	public static int[] getdp1(int[] arr) {
		// dp[i]表示以i结尾的最长的子序列长度
		int[] dp = new int[arr.length];
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			if (arr[i] > arr[i - 1]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				for (int j = i - 1; j >= 0; j--) {
					if (arr[j] <= arr[i]) {
						dp[i] = dp[j] + 1;
						break;
					}
				}
				if (dp[i] == 0) {
					dp[i] = 1;
				}
			}
		}
		return dp;
	}

	public static int[] generateLIS(int[] arr, int[] dp) {
		// dp中的最大值
		int index = 0;
		// LIS的长度
		int len = 1;
		for (int i = 1; i < dp.length; i++) {
			if (dp[index] < dp[i]) {
				index = i;
				len++;
			}
		}
		int[] res = new int[len];
		res[--len] = arr[index];
		for (int i = index - 1; i >= 0; i--) {
			if (arr[i] < arr[index] && dp[i] + 1 == dp[index]) {
				res[--len] = arr[i];
				index = i;
			}
		}
		return res;
	}

	public static int[] lis1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp1(arr);
		return generateLIS(arr, dp);
	}

	public static int[] getdp2(int[] arr) {
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		dp[0] = 1;
		int right = 0;
		// 二分中的左中右
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < arr.length; i++) {
			l = 0;
			r = right;
			// 二分找到arr[i]应该在该数列的位置
			while (l <= r) {
				m = (l + r) / 2;
				if (arr[i] > ends[m]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// 如果arr[i]比ends[right]还大，那么其位置l是right+1
			// 更新right，即ends的有效区域
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}

	public static int[] lis2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return null;
		}
		int[] dp = getdp2(arr);
		return generateLIS(arr, dp);
	}

	public static void main(String[] args) {
		int[] arr = { 2, 1, 5, 3, 6, 4, 8, 9, 7 };
		int[] list1 = lis1(arr);
		for (int i = 0; i < list1.length; i++) {
			System.out.println(list1[i]);
		}
		int[] list2 = lis2(arr);
		for (int i = 0; i < list1.length; i++) {
			System.out.println(list2[i]);
		}
	}

}
