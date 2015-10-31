package com.dy.niuke;


/**
 * @author dy
 *	给定一个无序数组arr，求出需要排序的最短子数组长度
 */
public class Niu2_3 {
	
	public int Find(int []a){
		
		if(a.length == 1){
			return 0;
		}
		//当前需要移动的位置
		int maxIndex = 0;
		//当前最大值
		int max = a[0];
		//从前面遍历，找到最大值和需要移动的位置
		for(int i = 1 ; i < a.length; i++){
			//如果当前值小于最大值，说明要移动，因为最大值的后面不应该出现比他大的
			if(max > a[i]){
				maxIndex = i;
			}else{
				//出现比当前最大值大的，更新最大值
				max = a[i];
			}
		}
		//当前需要移动的位置
		int minIndex = a.length - 1;
		//当前最小值
		int min = a[a.length-1];
		for(int i = a.length-2 ; i >= 0; i--){
			if( min < a[i]){
				minIndex = i;
			}else{
				min = a[i];
			}
		}
		
		return maxIndex - minIndex + 1;
	}
	
	
}
