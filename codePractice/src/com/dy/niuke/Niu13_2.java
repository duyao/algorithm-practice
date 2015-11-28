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
				//更新qmax
				while(!qmax.isEmpty() && arr[j] >= arr[qmax.getLast()]){
					qmax.removeLast();
				}
				qmax.add(j);
				//更新qmin
				while(!qmin.isEmpty() && arr[j] <= arr[qmin.getLast()]){
					qmin.removeLast();
				}
				qmin.add(j);
				//判断是否不符条件，如果不符，后面的都不会符合
				if(arr[qmax.getFirst()] - arr[qmin.getFirst()] <= num){
					//这里不能更新res，因为当i增加的时候j一直都是在最后，不会进入到此循环内，不能正确的计数
					//res ++;
				}else{
					break;
				}
				j++;
			}
			//更新窗口，因为i会变化，所以要将原来的pop
			if(qmax.getFirst() <= i){
				qmax.removeFirst();
			}
			if(qmin.getFirst() <= i){
				qmin.removeFirst();
			}
			//这里表示[i,i+1],[i,i+2],...,[i,j-1][i,j]都是符合条件的
			//一共是j-i组
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
