package com.dy.niuke;

public class Niu8_1 {
	
	//������������
	public  static int power(int x, int n)
	{
		if (n == 0)
			return 1;
		int result = 1;
		while (n != 0)
		{
			//n%2 != 0
			if ((n & 1) != 0)
				result *= x;
			x *= x;
			//n/=2
			n >>= 1;
		}
		return result;
	}
	
	// ��������������
	public static int[][] matrixPower(int[][] m, int p) {
		int[][] res = new int[m.length][m[0].length];
		// ��ʼ���������Ϊ��λ�󣬼��Խ�����1������λ����0���൱�������е�1
		//�൱������res=1
		for (int i = 0; i < m.length; i++) {
			res[i][i] = 1;
		}

		int[][] tmp = m;
		while (p != 0) {
			if (p % 2 != 0) {
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
			p /= 2;

		}
		return res;

	}

	// ������˵�����
	public static int[][] muliMatrix(int[][] m1, int[][] m2) {
		int[][] res = new int[m1.length][m2[0].length];

		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0; k < m2.length; k++) {
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}

		return res;
	}
	
	public static int f(int n) {
		if(n < 1){
			return 0;
		}else if(n == 1 || n == 2){
			return 1;
		}else{
			
			int[][] a = {{1, 1}, {1, 0}};
			int[][] res = matrixPower(a, n-2);
			return res[0][0] + res[1][0];
		}
	}

	public static void main(String[] args) {
		for (int k = 1; k < 20; k++) {
			System.out.println(f(k));
		}
		
	}
}
