package com.dy.niuke;
public class Niu14_3 {
	public static int getUnconnetedSum(int[] a){
		int[] left = new int[a.length];
		int[] right = new int[a.length];
		int sum = 0;
		int maxNum = Integer.MIN_VALUE;
		//�������������ͷ�����ʼ��left
		for (int i = 0; i < a.length; i++) {
			if(sum < 0){
				sum = a[i];
			}else{
				sum += a[i];
			}
			maxNum = Math.max(maxNum, sum);
			left[i] = maxNum;
		}
		sum = 0;
		maxNum = Integer.MIN_VALUE;
		//�������������ͷ�����ʼ��right
		for(int i = a.length-1; i >= 0; i--){
			if(sum < 0){
				sum = a[i];
			}else{
				sum += a[i];
			}
			maxNum = Math.max(maxNum, sum);
			right[i] = maxNum;
		}
		
		int res = Integer.MIN_VALUE;
		//��iΪ�ֽ�㣬�������ֵ
		for (int i = 0; i < a.length-1; i++) {
			res = Math.max(res, left[i]+right[i+1]);
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		int[] a1={1,-1,0,-2,3,5,-2,8,7,-4};
		int[] a2={3,-1,0,-2,3,5,-2,8,7,-4};
		System.out.println(getUnconnetedSum(a1));
		System.out.println(getUnconnetedSum(a2));
	}

}
