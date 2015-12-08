package com.dy.niuke;

import java.util.Stack;

public class Niu15_4 {
	public static int getMaxArea(int[][] map){
		int[] height = new int[map[0].length];
		int res = 0;
		//更新height
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < height.length; j++){
				//height记录的是连续的1的个数
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			res = Math.max(res, fArea(height));
		}
		return res;
	}
	
	public static int fArea(int[] a){
		//栈中存放数字的下标
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		//实现一次遍历就找面积
		for(int i = 0; i < a.length; i++){
			//对于栈不空时，当前元素小于等于栈顶时都要出栈，
			while(!stack.empty() && a[i] <= a[stack.peek()]){
				//栈顶出栈，得到矩形的宽a[k]
				int k = stack.pop();
				//左边第一个比a[k]小的值
				int left = stack.empty()? -1: stack.peek();
				//右边第一个比a[k]小的值，同时注意存在相等的状况
				int right = a[k] == a[i] ? i+1 : i;
				//区间是左开右开
				int tmp = (right - left - 1)* a[k];
				maxArea = Math.max(tmp, maxArea);
			}
			stack.push(i);
		}
		//遍历结束后仍然存在元素
		while(!stack.empty()){
			//栈顶出栈，得到矩形的宽a[k]
			int k = stack.pop();
			//栈空时
			int right = stack.empty()? a.length: k + 1;
			int left = stack.empty()? -1: stack.peek();
			//区间是左开右开
			int tmp = (right - left - 1)* a[k];
			maxArea = Math.max(tmp, maxArea);
		}
		return maxArea;
	}

	public static void main(String[] args) {
//		int[][] map = { { 1, 1, 1, 1 }, { 1, 0, 1, 0 },  { 1, 1, 0, 1 } };
		int[][] map = { { 1, 0, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 0 }, };
		System.out.println(getMaxArea(map));
	}

}
