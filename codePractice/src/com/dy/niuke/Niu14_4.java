package com.dy.niuke;

public class Niu14_4 {
	public static int getMaxtrixSum(int[][] a) {
		
		int maxNum = Integer.MIN_VALUE;
		//分割点i
		for(int i = 0; i < a.length; i++){
			//存放该行以上所有的和，即tmp[i]表示i列在该行的所有值的和
			int[] tmp = new int[a[0].length];
			//可遍历第012，12，2行
			for(int k = i; k < a.length; k++){
				//求最大子数组和的过程
				int curSum = 0;
				for(int j = 0; j < a[0].length; j++){
					//先更新tmp，然后求最大
					tmp[j] += a[k][j];
					if(curSum < 0){
						curSum = tmp[j];
					}else{
						curSum += tmp[j];
					}
					maxNum = Math.max(curSum, maxNum);
				}
			}
		}
		return maxNum;

	}

	public static void main(String[] args) {
		int a1[][] = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
		int a2[][] = { { -1, -1, -1 }, { -1, 2, 2 }, { -1, -1, -1 } };
		System.out.println(getMaxtrixSum(a1));
		System.out.println(getMaxtrixSum(a2));
	}
}
