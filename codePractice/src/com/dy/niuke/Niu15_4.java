package com.dy.niuke;

import java.util.Stack;

public class Niu15_4 {
	public static int getMaxArea(int[][] map){
		int[] height = new int[map[0].length];
		int res = 0;
		//����height
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < height.length; j++){
				//height��¼����������1�ĸ���
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			res = Math.max(res, fArea(height));
		}
		return res;
	}
	
	public static int fArea(int[] a){
		//ջ�д�����ֵ��±�
		Stack<Integer> stack = new Stack<Integer>();
		int maxArea = 0;
		//ʵ��һ�α����������
		for(int i = 0; i < a.length; i++){
			//����ջ����ʱ����ǰԪ��С�ڵ���ջ��ʱ��Ҫ��ջ��
			while(!stack.empty() && a[i] <= a[stack.peek()]){
				//ջ����ջ���õ����εĿ�a[k]
				int k = stack.pop();
				//��ߵ�һ����a[k]С��ֵ
				int left = stack.empty()? -1: stack.peek();
				//�ұߵ�һ����a[k]С��ֵ��ͬʱע�������ȵ�״��
				int right = a[k] == a[i] ? i+1 : i;
				//���������ҿ�
				int tmp = (right - left - 1)* a[k];
				maxArea = Math.max(tmp, maxArea);
			}
			stack.push(i);
		}
		//������������Ȼ����Ԫ��
		while(!stack.empty()){
			//ջ����ջ���õ����εĿ�a[k]
			int k = stack.pop();
			//ջ��ʱ
			int right = stack.empty()? a.length: k + 1;
			int left = stack.empty()? -1: stack.peek();
			//���������ҿ�
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
