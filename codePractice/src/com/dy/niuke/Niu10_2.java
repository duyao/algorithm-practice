package com.dy.niuke;

public class Niu10_2 {
	
	// ����ʱ��[i,j]�λ��Ǯ��
	public static int f(int[] arr, int i, int j) {
		//����i=j����ֻ��һ��Ԫ�أ���Ϊ�����ߣ�һ��������
		if (i == j) {
			return arr[i];
		}
		// ���[i,j]��������ֻ��ȡ���˵�ֵ��Ȼ�����ʣ���ֵ
		// �������arr[i]����ʣ��i+1�Ĳ���s(arr, i + 1, j)
		// ������һ����ѡȡ��������
		return Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1));
	}

	// ����ʱ��[i,j]�λ��Ǯ��
	public static int s(int[] arr, int i, int j) {
		//����i=j����ֻ��һ��Ԫ�أ���Ϊ�����ߣ�һ���ò��������Է���0
		if (i == j) {
			return 0;
		}
		//���[i,j]��������ֻ�����ó������������ֵ�������ã������f(arr, i + 1, j)
		//�����ڱ��Σ��ò�������ֵ����Ϊ�������Ѿ����ߣ�����arr[i]+f(arr, i + 1, j)
		//Ҳû��arr[i+1]+f(arr, i + 1, j)����Ϊ��Ե�������[i,j]Ҫô�ñ߽磬Ҫô����
		//�������õ���ѡ�������ģ����ܺ���һ���ģ���ˣ�����ֻ������Сmin
		return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
	}
	
	public static int win1(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//���û��ߺ��õ����ֵ
		return Math.max(f(arr, 0, arr.length - 1), s(arr, 0, arr.length - 1));
	}
	
	public static int win2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		//f[i][j] ��ʾ���õ�[i,j]��λ�ÿ��Ի�õ����Ǯ��
		int[][] f = new int[arr.length][arr.length];
		int[][] s = new int[arr.length][arr.length];
		
		for (int j = 0; j < arr.length; j++) {
			//��ʼ�������������arr[j]һ����ȫ�����ߣ���������ֻ������0
			f[j][j] = arr[j];
			s[j][j] = 0;
			//i=j-1��j������0��ʼ�������ᱨ����Ϊi >= 0
			for(int i = j-1; i >= 0; i--){
				f[i][j] = Math.max(arr[i] + s[i+1][j], arr[j] + s[i][j-1]);
				s[i][j] = Math.min(f[i+1][j], f[i][j-1]);
			}
			
		}
		return Math.max(f[0][arr.length-1], s[0][arr.length-1]);
	}
	public static void main(String[] args) {
		int[] arr = { 1, 9, 1 };
		System.out.println(win1(arr));
		System.out.println(win2(arr));

	}

}
