package com.dy.niuke;

import java.util.LinkedList;


public class Niu13_2 {
	public static int getNum(int[] arr, int num){
		if(arr == null || arr.length == 0){
			return 0;
		}
		LinkedList<Integer> qmax = new LinkedList<Integer>();
		LinkedList<Integer> qmin = new LinkedList<Integer>();
		int i = 0;
		int j = 0;
		int res = 0;
		while(i < arr.length){
			while(j < arr.length){
				//����qmax
				while(!qmax.isEmpty() && arr[j] >= arr[qmax.getLast()]){
					qmax.removeLast();
				}
				qmax.add(j);
				//����qmin
				while(!qmin.isEmpty() && arr[j] <= arr[qmin.getLast()]){
					qmin.removeLast();
				}
				qmin.add(j);
				//�ж��Ƿ񲻷��������������������Ķ��������
				if(arr[qmax.getFirst()] - arr[qmin.getFirst()] <= num){
					//���ﲻ�ܸ���res����Ϊ��i���ӵ�ʱ��jһֱ��������󣬲�����뵽��ѭ���ڣ�������ȷ�ļ���
					//res ++;
				}else{
					break;
				}
				j++;
			}
			//���´��ڣ���Ϊi��仯������Ҫ��ԭ����pop
			if(qmax.getFirst() <= i){
				qmax.removeFirst();
			}
			if(qmin.getFirst() <= i){
				qmin.removeFirst();
			}
			//�����ʾ[i,i+1],[i,i+2],...,[i,j-1][i,j]���Ƿ���������
			//һ����j-i��
			res += j-i;
			i++;
		}
		return res;
	}
	
	
	
	public static void main(String[] args) {
		int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
		int num = 3;
		int res = getNum(arr, num);
		System.out.println("res="+res);
		
				
		
	}

}
