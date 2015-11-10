package com.dy.niuke;

public class Niu6_2 {

	public int maxGap(int[] a) {
		if (a.length == 0 || a == null) {
			return 0;
		}
		// 求出最大最小值
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			min = Math.min(a[i], min);
			max = Math.max(a[i], max);
		}
		if (max == min) {
			// 最大值与最小值相等，说明所有数字都相等，差值为0
			return 0;
		}
		
		// 记录每个桶的最大值和最小值，及该桶是否有值
		int[] minb = new int[a.length + 1];
		int[] maxb = new int[a.length + 1];
		boolean[] is = new boolean[a.length + 1];
		
		int len = a.length;
		for (int i = 0; i < len; i++) {
			// 找到该数应该放在哪个桶里
			int bid = bucket(a[i], min, max, len);
			if (is[bid]) {
				minb[bid] = Math.min(a[i], minb[bid]);
				maxb[bid] = Math.max(a[i], maxb[bid]);
			} else {
				minb[bid] = a[i];
				maxb[bid] = a[i];
				is[bid] = true;
			}

		}

		
		int i = 0;
		//计算结果时候的前区间的最大值
		int num = 0;
		//最大差值，即后最小减前最大
		int res = 0;
		//找到第一个不空的区间
		while (i < len) {
			if (is[i]) {
				num = maxb[i];
				i++;
				break;
			}
		}
		//找到后面不空的区间
		for (; i <= len; i++) {
			if (is[i]) {
				res = Math.max(res, minb[i] - num);
				//每次重置前区间的最大值
				num = maxb[i];
			}
			
		}

		return res;

	}

	//找到数字i在哪个哪个区间中！
	//((i - min) * len / (max - min))
	//用long是为了防止溢出
	public int bucket(long i, long min, long max, long len) {
		return (int) ((i - min) * len / (max - min));
	}


	public static void main(String[] args) {
//		int[] a = { 9, 3, 1, 10 };
		int[] a = { 100, -3, 1, 10 };
		Niu6_2 niu6_2 = new Niu6_2();
		int ans = niu6_2.maxGap(a);
		System.out.println(ans);
	}
}
