package com.dy.niuke;

import java.util.HashSet;

public class Niu14_1 {


	public static int getLen(int[] a){
		HashSet<Integer> set = new HashSet<Integer>();
		int min = 0;
		int max = 0;
		int len = 0;
		for(int i = 0; i < a.length; i++){
			//����������i��ͷ�������飬Ҫ���
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			for(int j = i; j < a.length; j++){
				//�ظ��������������Ҳ���ظ�
				if(set.contains(a[j])){
					break;
				}
				set.add(a[j]);
				max = Math.max(max, a[j]);
				min = Math.min(min, a[j]);
				//���ֵ - ��Сֵ + 1 = �����鳤�� = j-i+1
				if(j - i == max - min){
					len = Math.max(len, j - i + 1);
				}
			}
			//����������i��ͷ�������飬Ҫ���
			set.clear();
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] a = {5,5,3,2,6,4,3};
		System.out.println(getLen(a));
	}
}
