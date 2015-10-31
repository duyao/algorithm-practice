package com.dy.niuke;

/**
 * @author dy
 * 最大的leftMax与rightMax之差的绝对值
 */
public class Niu2_4 {
	public int ans1(int []a){
		int leftNum = Integer.MIN_VALUE;
		int rightNum = Integer.MIN_VALUE;
		int [] left = new int[a.length];
		int [] right = new int[a.length];
		//找左边的最大值
		for(int i = 0; i < a.length; i++){
			if(a[i] > leftNum ){
				left[i] = a[i];
				leftNum = a[i];
			}else{
				left[i] = leftNum;
			}
		}
		//找右边的最大值
		for(int i = a.length-1; i > -1; i--){
			if(a[i] > rightNum){
				rightNum = a[i];
				right[i] = a[i];
			}else{
				right[i] = rightNum;
			}
		}
		//对左右的最大值进行比较
		int maxRes = Integer.MIN_VALUE;
		for(int i=0; i < a.length-2; i++){
			maxRes = Math.max(maxRes, Math.abs(left[i] - right[i+1]));
		}
		return maxRes;
	}
	
	public int ans2(int []a){
		//找到数组中的最大值
		int maxNum = Integer.MIN_VALUE;
		for(int i=0; i < a.length; i++){
			if(a[i] > maxNum){
				maxNum = a[i];
			}
		}
		//一个最大值是该数组的最大值，另一个是该组的边界的最小值
		return maxNum - Math.min(a[0], a[a.length - 1]);
	}
}
