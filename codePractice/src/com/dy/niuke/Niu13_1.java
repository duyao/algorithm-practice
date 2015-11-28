package com.dy.niuke;

import java.util.LinkedList;

public class Niu13_1 {
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		//存放数组中数字的序号，且序号表示的数字顺序必须是从大到小
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		//res的位置
		int index = 0;
		for(int i = 0; i < arr.length; i++){
			//添加元素
			//更新qmax的数据，对于满足顺序，即小于最后一个元素的直接放入，否则一直剔除最后一个，可能会使双端队列变空，直到满足要求
			while(!qMax.isEmpty() && arr[qMax.getLast()] <= arr[i]){
				qMax.removeLast();
			}
			qMax.add(i);
			//更新res
			if(i >= w - 1){
				//由于qmax是按照从小到大的顺序排列，因此最大值一定是第一个，但是可能出现一个数不在当前窗口的范围之中
				//检查qmax的数字是否有效，即在窗口之内
				while(qMax.getFirst() < index || qMax.getFirst() > index + w){
					qMax.removeFirst();
				}
				res[index++] = arr[qMax.getFirst()];
			}
			
		}
		return res;
		
	}

	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int w = 3;
		int res[] = getMaxWindow(arr, w);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i]+",");
		}
	}
}
