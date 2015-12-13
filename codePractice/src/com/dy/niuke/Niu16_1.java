package com.dy.niuke;

public class Niu16_1 {

	public static int getRandom(int i){
		//����(0,i]����������[1,i]
		return (int)(Math.random()*i)+1;
	}
	
	public static int[] getNum(int k, int n){
		//��ˮ��
		int[] reservoir = new int[Math.min(k, n)];
		//ǰk������ֱ�ӽ�����ˮ��
		for (int i = 0; i < reservoir.length; i++) {
			reservoir[i] = i;
		}	
		for(int i = k+1; i < n ; i++){
			//����i�Ƿ����
			//����[1,i+1],��1��õ�[0,i]
			int cur = getRandom(i+1)-1;
			if(cur <= k - 1){
				//��k֮�ڣ��ͻ������У��򽫳��е�����һ��Ԫ���ó�
				//������[1,k],��1��õ�[0,k-1]
				int repalced = getRandom(k)-1;
				reservoir[repalced] = i;
			}
		}
		return reservoir;
	}
	public static void printArray(int[] a){
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]+" ");
		}
	}
	public static void main(String[] args) {
		int[] reservior = getNum(10, 200);
		printArray(reservior);
	}
	
	
	
}
