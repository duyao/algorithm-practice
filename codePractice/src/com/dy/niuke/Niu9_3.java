package com.dy.niuke;


public class Niu9_3 {

	public static int minCost(String str1, String str2, int ic, int dc, int rc) {
		if (str1 == null || str2 == null) {
			return 0;
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		//多设置一行一列的空值，方便计算
		int row = chs1.length + 1;
		int col = chs2.length + 1;
		
		//dp[0][0]是0，因为空变成空不需要代价
		int[][] dp = new int[row][col];
		for(int i = 1; i < col; i++){
			//把空改为str2的代价是添加
			dp[0][i] = ic*i;
		}
		for(int j = 1; j < row; j++){
			dp[j][0] = dc*j;
		}
		for(int i = 1; i < row; i++){
			for(int j= 1; j < col; j++){
				//在3中取值找最小
				//判断
				if(chs1[i-1] == chs2[j-1]){
					//最后一个相同，不用代价
					dp[i][j] = dp[i-1][j-1];
				}else{
					//不同就修改最后一个
					dp[i][j] = dp[i-1][j-1]+rc;
 				}
				//先0,i-1变为0,j-2然后加上j-1
				dp[i][j]=Math.min(dp[i][j], dp[i][j-1]+ic);
				//先0,i-1删除i-1得到0,i-2变为0,j-1
				dp[i][j]=Math.min(dp[i][j], dp[i-1][j]+dc);
			}
		}
		return dp[row-1][col-1];
		
	}
	
	public static void main(String[] args) {
		String str1="ab12cd3";
		String str2="abcdf";
		int ic=5, dc=3, rc=2;
		int ans = minCost(str1,str2,ic,dc,rc);
		System.out.println(ans);
	}

}
