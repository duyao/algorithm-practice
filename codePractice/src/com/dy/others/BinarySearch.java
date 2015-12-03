package com.dy.others;

public class BinarySearch {
	// 查找数字x是否在有序数组中,无重复
	public static int f1(int[] a, int x) {
		int left = 0;
		int right = a.length - 1;
		int mid = 0;
		// 区间是[0,n-1]
		while (left <= right) {
			mid = (left + right) / 2;
			if (a[mid] == x) {
				return mid;
			} else if (a[mid] > x) {
				// 在区间[left,mid-1]找
				right = mid - 1;
			} else {
				// 在区间[mid+1,right]找
				left = mid + 1;
			}
		}
		// 找不到
		return -1;
	}

	//序列的第一个x的位置
	public static int f2(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		// 区间是[0,n)
		// 分支中出现了l=mid 或者r=mid，循环条件要写成left < right，否则写成left <= right
		//避免出现left=right-1出现死循环
		while (left < right) {
			mid = (left + right) / 2;
			if (a[mid] >= x) {
				// 在区间[left,mid)找
				//因为是左闭右开区间，所以right=mid
				right = mid;
			} else {
				// 在区间[mid+1,right)找
				left = mid + 1;
			}
		}
		//返回左
		return left;
	}

	//第一个大于x的位置R
	public static int f3(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		// 区间是[0,n)
		// 分支中出现了l=mid 或者r=mid，循环条件要写成left < right，否则写成left <= right
		//避免出现left=right-1出现死循环
		while (left < right) {
			mid = (left + right) / 2;
			//第一个大于x的元素，没有等号
			if (a[mid] > x) {
				// 在区间[left,mid)找
				right = mid;
			} else {
				// 在区间[mid+1,right)找
				left = mid + 1;
			}
		}
		// 找不到
		return left;
	}

	public static void main(String[] args) {
		int[] a1 ={0,1,1,1,2,2,2,3,3,3};
		System.out.println(f1(a1,0));
		System.out.println(f2(a1,2));
		System.out.println(f3(a1,2));
		System.out.println(f2(a1,3));
		System.out.println(f3(a1,3));
	}
}
