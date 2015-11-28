package com.dy.niuke;

import java.util.LinkedList;

public class Niu13_1 {
	public static int[] getMaxWindow(int[] arr, int w) {
		if (arr == null || w < 1 || arr.length < w) {
			return null;
		}
		int[] res = new int[arr.length - w + 1];
		//������������ֵ���ţ�����ű�ʾ������˳������ǴӴ�С
		LinkedList<Integer> qMax = new LinkedList<Integer>();
		//res��λ��
		int index = 0;
		for(int i = 0; i < arr.length; i++){
			//���Ԫ��
			//����qmax�����ݣ���������˳�򣬼�С�����һ��Ԫ�ص�ֱ�ӷ��룬����һֱ�޳����һ�������ܻ�ʹ˫�˶��б�գ�ֱ������Ҫ��
			while(!qMax.isEmpty() && arr[qMax.getLast()] <= arr[i]){
				qMax.removeLast();
			}
			qMax.add(i);
			//����res
			if(i >= w - 1){
				//����qmax�ǰ��մ�С�����˳�����У�������ֵһ���ǵ�һ�������ǿ��ܳ���һ�������ڵ�ǰ���ڵķ�Χ֮��
				//���qmax�������Ƿ���Ч�����ڴ���֮��
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
