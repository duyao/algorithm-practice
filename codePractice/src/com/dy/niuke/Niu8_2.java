package com.dy.niuke;

public class Niu8_2 {
	public static  int coins1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return f1(arr, 0, aim);
	}

	// �����ݹ飬���Ż�
	public static int f1(int[] a, int index, int aim) {
		// ������
		int res = 0;
		if (index == a.length) {
			// index�Ѿ���������ı߽磬��Ŀ��Ǯ���Ѿ���Ϊ0��˵�������������Ч��
			if (aim == 0) {
				res = 1;
			}
		} else {
			for (int i = 0; a[index] * i <= aim; i++) {
				// ע�������index+1
				// ���ÿ������index���ӣ�Ȼ��index�ĸ���i���ӣ�����֮��Ҫ��aim����
				res += f1(a, index + 1, aim - a[index] * i);
			}
		}
		return res;
	}

	// ʹ��map�������ظ�����
	public static int coins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// map�Ǵ洢�÷����Ƿ�������ֵΪ-1��ʾ���������Ϊ�ô˷�������ֵΪ0��mapֵΪ0����ʾû�м��㣬
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
				// ��Ϊ����Ҫ������������ȿ���map���Ƿ�洢
				int tmp = map[index + 1][aim - a[index] * i];
				if (tmp != 0) {
					// tmp��=0��ʾ�����
					if (tmp != -1) {
						// tmp=-1��ʾ����ֵΪ0�����Բ��üӣ�
						// tmp��=-1�з���ֵ���Ҳ�Ϊ0
						res += tmp;
					}
				} else {
					// ��ֵmap�л�û����������Ҫ����һ��
					res += f2(a, index + 1, aim - a[index] * i, map);
				}
				// ÿ������һ��ѭ��������map,���µĴ˹��̵�map����map[index][aim]
				map[index][aim] = res == 0 ? -1 : res;
			}
		}
		return res;
	}

	// ʹ��dp
	public static int coins3(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// dp[i][j]��ʾʹ��arr[0..i]���ҵ�����£����Ǯ��j�ж����ַ���
		int[][] dp = new int[arr.length][aim + 1];
		// ��ʼ��
		for (int i = 0; i < arr.length; i++) {
			// ʹ��arr[0..i]���ҵ�����£����Ǯ��0��1��
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= aim; j++) {
			// ʹ��arr[0]���ҵ�����£�ֻ�����arr[0]�ı�����Ǯ��
			dp[0][arr[0] * j] = 1;
		}

		int num = 0;
		//��������λ�õ�dp
		// dp[i][j]ʹ��dp[i-1][j]����ʹ��arr[i]�Ļ��ң����j�ķ�����
		// ��ʹ��arr[i]�Ļ��ҵ�ʱ��
		// ��ôʹ��1��arr[i]����dp[i-1][j-arr[i]]
		// ��ôʹ��2��arr[i]����dp[i-1][j-arr[i]*2]
		// ...��˵�ʹ��k��arr[i]�Ļ��ң�������Ϊdp[i-1][j-arr[i]*k]
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				num = 0;
				//����dp[i][j]
				for (int k = 0; j - arr[i] * k >= 0; k++) {
					num += dp[i - 1][j - arr[i] * k];
				}
				// ����dp���൱�ڸ���map
				dp[i][j] = num;
			}
		}
		return dp[arr.length - 1][aim];
	}

	//�Ż�dp
	public static int coins4(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		// dp[i][j]��ʾʹ��arr[0..i]���ҵ�����£����Ǯ��j�ж����ַ���
		int[][] dp = new int[arr.length][aim + 1];
		// ��ʼ��
		for (int i = 0; i < arr.length; i++) {
			// ʹ��arr[0..i]���ҵ�����£����Ǯ��0��1��
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0] * j <= aim; j++) {
			// ʹ��arr[0]���ҵ�����£�ֻ�����arr[0]�ı�����Ǯ��
			dp[0][arr[0] * j] = 1;
		}
		//���������dp
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				//dp[i][j] = dp[i-1][j] + dp[i-1][j-arr[i]]
				//��Ҫ�ж�j-arr[i]�Ƿ�Խ��
				dp[i][j] = dp[i-1][j];
				if(j-arr[i] >= 0){
					dp[i][j] += dp[i][j-arr[i]];
				}
			}
		}
		return dp[arr.length - 1][aim];
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 25, 1 };
		int aim = 15;
		System.out.println(coins1(arr, aim));
		System.out.println(coins2(arr, aim));
		System.out.println(coins3(arr, aim));
		System.out.println(coins4(arr, aim));
	}

}
