package com.dy.niuke;

public class Niu14_4 {
	public static int getMaxtrixSum(int[][] a){
		int[][] sum = new int[a.length][a[0].length];
		for(int i = 0; i < a.length; i++){
			int curSum = 0;
			int maxNum = Integer.MIN_VALUE;
			for(int j = 0; j < a[0].length; j++){
				if(curSum < 0){
					curSum = a[i][j];
				}else{
					curSum += a[i][j];
				}
				maxNum = Math.max(maxNum, curSum);
				sum[i][j] = maxNum;
			}
		}
		
		int res = Integer.MIN_VALUE;
		for(int i = 0; i < a.length; i++){
			int curSum = 0;
			for(int j = 0; j < a[0].length; j++){
				for(int k = i; k < a.length; k++){
					curSum += a[k][j];
					res = Math.max(res, curSum);
				}
			}
		}
		
		return res;
		
	}

	
	public static void main(String[] args) {
		
	}
}
