package com.dy.niuke;

import java.util.HashMap;


public class Niu14_5 {
	
	public static int getKLen(int[] arr, int k){
		//key表示和的数值，value表示第一次出现该值的位置
		//该map只记录第一次出现的情况
		HashMap<Integer, Integer> mymap = new HashMap<Integer, Integer>();
		//因为和0的记录是所有值都不取，不会被自动加入
		//因此这里添加，表示从开头是选
		mymap.put(0, -1);
		int sum = 0;
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			//以i结尾的和
			sum += arr[i];
			//只记录第一次出现的和，因为这样才能最长
			if(!mymap.containsKey(sum)){
				mymap.put(sum, i);
			}
			//如果有sum[j]+k=sum[i]，说明(j,i]的部分和是k
			if(mymap.containsKey(sum - k)){
				int j = mymap.get(sum - k);
				//以j结尾的和和以i结尾的和，期间的长度是i-j
				len = Math.max(len, i - j);
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] a = {-5,-5,3,2,6,-4,3};
		int k = -10;
		System.out.println(getKLen(a,k));
	}
}
