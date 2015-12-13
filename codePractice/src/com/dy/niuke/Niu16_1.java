package com.dy.niuke;

public class Niu16_1 {

	public static int getRandom(int i){
		//返回(0,i]的整数，即[1,i]
		return (int)(Math.random()*i)+1;
	}
	
	public static int[] getNum(int k, int n){
		//蓄水池
		int[] reservoir = new int[Math.min(k, n)];
		//前k个数字直接进入蓄水池
		for (int i = 0; i < reservoir.length; i++) {
			reservoir[i] = i;
		}	
		for(int i = k+1; i < n ; i++){
			//决定i是否进池
			//产生[1,i+1],减1后得到[0,i]
			int cur = getRandom(i+1)-1;
			if(cur <= k - 1){
				//在k之内，就会进入池中，则将池中的任意一个元素拿出
				//产生出[1,k],减1后得到[0,k-1]
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
