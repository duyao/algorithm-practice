package com.dy.niuke;

public class Niu9_2 {

	public static int[][] getdp(char[] str1, char[] str2) {
		int[][] dp = new int[str1.length][str2.length];
		//��ʼ����һ��
		for(int i = 0; i < str1.length; i++){
			if(str1[i]==str2[0]){
				dp[i][0] = 1;
				for(int j = i+1; j < str1.length; j++){
					dp[j][0] = 1;
				}
				break;
			}else{
				dp[i][0] = 0;
			}
			
		}
		//��ʼ����һ��
		for(int i = 1; i < str2.length; i++){
			if(str1[0]==str2[i]){
				dp[0][i] = 1;
				for(int j = i+1; j < str2.length; j++){
					dp[0][j] = 1;
				}
				break;
			}else{
				dp[0][i] = 0;
			}
		}
		
		for(int i = 1; i < str1.length; i++){
			for(int j = 1;j < str2.length; j++){
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(str1[i] == str2[j]){
					dp[i][j] = dp[i-1][j-1]+1;
				}
			}
		}
		
		return dp;
 	}
	
	public static char[] getAns(String str1, String str2){
		
		int m = str1.length()-1;
		int n = str2.length()-1;
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();

		int[][] dp = getdp(char1, char2);
		int len = dp[m][n];
		char[] str = new char[len];
		while(len > 0){
			
			//m>0 ��n>0������ǰ�棬��Ϊ�п��ܹ��絼�º��� dp[m][n] == dp[m][n-1]���жϲ��ܽ���
			if(n>0 && dp[m][n] == dp[m][n-1]){
				n--;
			}else if(m>0 && dp[m][n] == dp[m-1][n]){
				m--;
			}else{
				//dp[m][n] == dp[m-1][n-1]+1Ӧ����һ���жϷ�֧�����ǲ���д�����ж�����
				//��Ϊ�п��ܵ��˵�һ�У�m=0,�޷�����m-1
				str[--len] = char1[m];
				m--;
				n--;
			}
		}
 		
		return str; 
		
	}
	
	public static void main(String[] args) {
		String str1="1A2C3D4B56";
		String str2="B1D2CA45B6A";
		String ans = String.valueOf(getAns(str1, str2));
		System.out.println(ans);
	}
}
