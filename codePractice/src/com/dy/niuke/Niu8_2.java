package com.dy.niuke;

public class Niu8_2 {
	public int coins1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return f1(arr, 0, aim);
	}

	// 暴力递归，无优化
	public static int f1(int[] a, int index, int aim) {
		// 方法数
		int res = 0;
		if (index == a.length) {
			// index已经到了数组的边界，且目标钱数已经减为0，说明这个方法是有效的
			if (aim == 0) {
				res = 1;
			}
		} else {
			for (int i = 0; a[index] * i <= aim; i++) {
				// 注意后面是index+1
				// 因此每次是先index增加，然后index的个数i增加，增加之后要将aim减少
				res += f1(a, index + 1, aim - a[index] * i);
			}
		}
		return res;
	}

	// 使用map，减少重复计算
	public int coins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// map是存储该方法是否计算过，值为-1表示计算过，且为用此方法返回值为0，map值为0，表示没有计算，
		int[][] map = new int[arr.length + 1][aim + 1];
		return f2(arr, 0, aim, map);
	}

	public static int f2(int[] a, int index, int aim, int[][] map) {
		int res = 0;
		if (index == a.length) {
			if (aim == 0) {
				return 1;
			}
		} else {

			for (int i = 0; a[index] * i <= aim; i++) {
				// 因为下面要算这个，所以先看看map中是否存储
				int tmp = map[index + 1][aim - a[index] * i];
				if (tmp != 0) {
					// tmp！=0表示计算过
					if (tmp != -1) {
						// tmp=-1表示返回值为0，所以不用加，
						// tmp！=-1有返回值，且不为0
						res += tmp;
					}
				} else {
					// 该值map中还没有算过，因此要计算一下
					res += f2(a, index + 1, aim - a[index] * i, map);
				}
				// 每次算完一个循环，更新map
				map[index + 1][aim - a[index] * i] = res == 0 ? -1 : res;
			}
		}
		return res;
	}

	// 使用dp
	public static int coins3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// dp[i][j]表示使用arr[0..i]货币的情况下，组成钱数j有多少种方法
		int[][] dp = new int[arr.length][aim + 1];
		// 初始化
		for (int i = 0; i < arr.length; i++) {
			// 使用arr[0..i]货币的情况下，组成钱数0有1种
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= aim; j++) {
			// 使用arr[0]货币的情况下，只能组成arr[0]的倍数的钱数
			dp[0][arr[0] * j] = 1;
		}

		int num = 0;
		//计算其他位置的dp
		// dp[i][j]使用dp[i-1][j]即不使用arr[i]的货币，组成j的方法数
		// 当使用arr[i]的货币的时候
		// 那么使用1张arr[i]，有dp[i-1][j-arr[i]]
		// 那么使用2张arr[i]，有dp[i-1][j-arr[i]*2]
		// ...因此当使用k张arr[i]的货币，方法数为dp[i-1][j-arr[i]*k]
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				num = 0;
				//计算dp[i][j]
				for (int k = 0; j - arr[i] * k >= 0; k++) {
					num += dp[i - 1][j - arr[i] * k];
				}
				// 更新dp，相当于更新map
				dp[i][j] = num;
			}
		}
		return dp[arr.length - 1][aim];
	}

	//优化dp
	public static int coins4(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// dp[i][j]表示使用arr[0..i]货币的情况下，组成钱数j有多少种方法
		int[][] dp = new int[arr.length][aim + 1];
		// 初始化
		for (int i = 0; i < arr.length; i++) {
			// 使用arr[0..i]货币的情况下，组成钱数0有1种
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= aim; j++) {
			// 使用arr[0]货币的情况下，只能组成arr[0]的倍数的钱数
			dp[0][arr[0] * j] = 1;
		}
		//计算其余的dp
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				//dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]
				//先要判断j-arr[i]是否越界
				dp[i][j] = dp[i-1][j];
				if(j-arr[i] >= 0){
					dp[i][j] += dp[i-1][j-arr[i]];
				}
			}
		}
		return dp[arr.length - 1][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 25, 1 };
		int aim = 15;
		// System.out.println(f1(arr, 0, aim));
		String string = "fdsafdsa";
		System.out.println(string.charAt(0));
	}

}
