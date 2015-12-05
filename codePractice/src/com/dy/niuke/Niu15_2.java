package com.dy.niuke;

public class Niu15_2 {

	public static int maxLength(int[] arr, int k) {
		//记录当前位置出现的最大和，是一个递增数组
		int[] h = new int[arr.length + 1];
		int sum = 0;
		//加入和是0的记录
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		//结果
		int res = 0;
		//满足区间的左边界
		int pre = 0;
		//长度，右边界-左边界+1
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			//当前所有的和
			sum += arr[i];
			//k+x=sum
			//求小于等于k的区间，那么就要找到大于等于x即(sum-k)的值
			//即求出第一个大于x的位置
			pre = getIndex(h, sum - k);
			//可能找不到
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	// 第一个大于x的位置R
	public static int getIndex(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		//有可能出现找不到的情况
		int res = -1;
		// 区间是[0,n)
		// 分支中出现了l=mid 或者r=mid，循环条件要写成left < right，否则写成left <= right
		// 避免出现left=right-1出现死循环
		while (left < right) {
			mid = (left + right) / 2;
			// 第一个大于x的元素，没有等号
			if (a[mid] > x) {
				//找不到的情况是x比任何数都大，因此如果存在都会进左区间
				//右区间找不到情况下是不进入的
				//用res来记录，如果找到不进入此，不改变值，返回-1
				res = mid;
				// 在区间[left,mid)找
				right = mid;
			} else {
				// 在区间[mid+1,right)找
				left = mid + 1;
			}
		}
		// 找不到
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		 int[] arr = { 3, -2, -4, 0, 6 };
		 int k = -2;
		 System.out.println(maxLength(arr, k));

		int[] a1 = generateRandomArray(5, 15);
		printArray(a1);
		System.out.println(maxLength(a1, 3));
	}

}
