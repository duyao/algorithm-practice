package com.dy.niuke;

public class Niu9_4 {

	public static boolean isCross1(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] char1 = str1.toCharArray();
		char[] char2 = str2.toCharArray();
		char[] caim = aim.toCharArray();
		if (char1.length + char2.length != caim.length) {
			return false;
		}
		// 多增加一行和一列，为空值，方便计算
		int row = char1.length + 1;
		int col = char2.length + 1;
		boolean[][] dp = new boolean[row][col];
		dp[0][0] = true;
		for (int i = 1; i < row; i++) {
			// str1[0,i-1]交错aim[0,i-1]
			if(char1[i-1] != caim[i-1]){
				//遇到不等就退出循环，初始化时是false
				break;
			}
			dp[i][0] = true;
		}

		for (int i = 1; i < col; i++) {
			// str2[0,i-1]交错aim[0,i-1]
			if(char2[i-1] != caim[i-1]){
				break;
			}
			dp[0][i] = true;
		}

		
		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (caim[i + j - 1] == char1[i - 1] && dp[i - 1][j]
						|| caim[i + j - 1] == char2[j - 1] && dp[i][j - 1]) {
					dp[i][j] = true;
				}
			}
		}

		return dp[row - 1][col - 1];
	}
	

	//空间减小，只用一行表示一个数组，然后滚动更新
	public static boolean isCross2(String str1, String str2, String aim) {
		if (str1 == null || str2 == null || aim == null) {
			return false;
		}
		char[] ch1 = str1.toCharArray();
		char[] ch2 = str2.toCharArray();
		char[] chaim = aim.toCharArray();
		if (chaim.length != ch1.length + ch2.length) {
			return false;
		}
		//选出长短，长的在外循环，短的在循环内，因为使得不断更新短的数值
		char[] longs = ch1.length >= ch2.length ? ch1 : ch2;
		char[] shorts = ch1.length < ch2.length ? ch1 : ch2;
		boolean[] dp = new boolean[shorts.length + 1];
		dp[0] = true;
		//初始化该行
		for (int i = 1; i <= shorts.length; i++) {
			if (shorts[i - 1] != chaim[i - 1]) {
				break;
			}
			dp[i] = true;
		}
		//滚动更新的过程
		for (int i = 1; i <= longs.length; i++) {
			//更新该行的第一列
			dp[0] = dp[0] && longs[i - 1] == chaim[i - 1];
			for (int j = 1; j <= shorts.length; j++) {
				//此时只更新到j-1，而j还没有更新，表示的是上一行的j值，即要更新位置的上面
				//j-1已经更新，表示的是要更新位置的左边
				if ((longs[i - 1] == chaim[i + j - 1] && dp[j])
						|| (shorts[j - 1] == chaim[i + j - 1] && dp[j - 1])) {
					dp[j] = true;
				} else {
					dp[j] = false;
				}
			}
		}
		return dp[shorts.length];
	}

	public static void main(String[] args) {
		String str1 = "er";
		String str2 = "433";
		String aim = "4333r";
		System.out.println(isCross1(str1, str2, aim));
		System.out.println(isCross2(str1, str2, aim));
	}
}
