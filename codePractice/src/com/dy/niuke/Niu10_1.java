package com.dy.niuke;

public class Niu10_1 {
	//判断字符串是否合法
	public  static boolean isValid(String string){
		//- 长度必须为偶数
		if(string.length() % 2 == 0){
			return false;
		}
		char[] exp = string.toCharArray();
		//- 奇数位上是0或者1
		for(int i = 0; i < exp.length; i += 2){
			if(exp[i] != '1' && exp[i] != '0'){
				return false;
			}
		}
		//- 偶数位上是^|&
		for(int i = 1;i < exp.length; i += 2){
			if(exp[i] != '|' && exp[i] !='^' && exp[i] != '&'){
				return false;
			}
		}
		
		return true;
	}
	
	//判断l到r上的表达式有多少种满足desired的方法
	public static int p(String express, boolean desired, int l, int r){
		char[] exp = express.toCharArray();
		if(l == r){
			//只剩一个元素
			if(exp[l] == '1'){
				return desired ? 1 : 0;
			}else{
				return desired ? 0 : 1;
			}
		}
		int res = 0;
		//以位运算符分割表达式，分别计算表达的值
		//总的可能数为左边可能数乘以右边可能数
		for(int i = l+1; i < r; i += 2){
			if(desired){
				switch (exp[i]) {
				//desired为1，运算符是&,左右必须都是true
				case '&':
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					break;
				//desired为1，运算符是|,左1右0,左0右1，左1右1
				case '|':
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					break;
				//desired为1，运算符是^,左1右0,左0右1
				case '^':
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					break;

				}
			}else{
				switch (exp[i]) {
				case '&':
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					break;
				case '|':
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					break;
				case '^':
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					break;

				}
				
			}
		}
		return res;
	}
	
	//暴力递归
	public static int num1(String express, boolean desired) {
		if(express == null || express.equals("")){
			return 0;
		}
		
		if(!isValid(express)){
			return 0;
		}
		return p(express, desired, 0, express.length()-1);
	}
	
	//优化递归，记录所计算的值
	//p(char[] exp, boolean desired, int l, int r)递归中有3个变量，但是使用2张二维表就可以
	//一个表记录结果是true，另一个记录结果是false
	public static int num2(String express, boolean desired){
		if(express == null || express.equals("")){
			return 0;
		}
		
		if(!isValid(express)){
			return 0;
		}
		char[] exp = express.toCharArray();
		//t[i][j]表示第i到j的exp中结果为true的结果数
		int[][] t = new int[exp.length][exp.length];
		//f[i][j]表示第i到j的exp中结果为false的结果数
		int[][] f = new int[exp.length][exp.length];
		t[0][0] = exp[0]=='1' ? 1 : 0;
		f[0][0] = exp[0]=='0' ? 1 : 0;
		//以i为尾，j为头，算t[i][j]与f[i][j]
		//依次向两边扩散，计算
		for(int i = 2; i < exp.length; i += 2){
			//t[i][i]表示第i个位置为true的结果数
			//因此只要看是t[i][i]的值，值1返回1，值0返回0
			t[i][i] = exp[i]=='1' ? 1 : 0;
			f[i][i] = exp[i]=='0' ? 1 : 0;
			for(int j = i-2; j >= 0; j -= 2){
				//以k+1为分界，判断前面和后后的组成数目
				for(int k = j+1; k < i; k += 2){
					switch (exp[k]) {
					
					case '&':
						//k+1是&，结果是true的可能是前1后1
						t[j][i] += t[j][k-1] * t[k+1][i];
						//k+1是&，结果是false的可能是前1后0，前0后1，前0后0
						f[j][i] += f[j][k-1] * f[k+1][i] + f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						break;
					case '|':
						t[j][i] += t[j][k-1] * t[k+1][i] + f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						f[j][i] += f[j][k-1] * f[k+1][i];
						break;
					case '^':
						t[j][i] += f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						f[j][i] += t[j][k-1] * t[k+1][i] + f[j][k-1] * f[k+1][i];
						break;
					}
				}
				
			}
		}
		
		return desired ? t[0][exp.length-1] : f[0][exp.length-1];
	}

	public static void main(String[] args) {
		String express = "1^0|0|1";
		boolean desired = false;
		//String express = "1^0&0|1&1^0&0^1|0|1&1";
		//boolean desired = true;
		System.out.println(num1(express, desired));
		System.out.println(num2(express, desired));

	}
}
