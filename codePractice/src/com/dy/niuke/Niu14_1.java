package com.dy.niuke;

import java.util.HashSet;

public class Niu14_1 {


	public static int getLen(int[] a){
		HashSet<Integer> set = new HashSet<Integer>();
		int min = 0;
		int max = 0;
		int len = 0;
		for(int i = 0; i < a.length; i++){
			//遍历结束以i开头的子数组，要清空
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for(int j = i; j < a.length; j++){
				//重复，后面的子数组也会重复
				if(set.contains(a[j])){
					break;
				}
				set.add(a[j]);
				max = Math.max(max, a[j]);
				min = Math.min(min, a[j]);
				//最大值 - 最小值 + 1 = 子数组长度 = j-i+1
				if(j - i == max - min){
					len = Math.max(len, j - i + 1);
				}
			}
			//遍历结束以i开头的子数组，要清空
			set.clear();
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] a = {5,5,3,2,6,4,3};
		System.out.println(getLen(a));
	}
}
