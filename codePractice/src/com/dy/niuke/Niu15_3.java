package com.dy.niuke;

public class Niu15_3 {
	public static void setMap(int[][] m, int[][] down, int[][] right){
		for(int i = m.length - 1; i >= 0; i--){
			//���һ��
			right[i][m[0].length - 1] = m[i][m[0].length - 1] == 1 ? 1 : 0;
			for(int j = m[0].length - 2; j >= 0; j-- ){
				//��Ҫ���ұߵ�
				if(m[i][j+1]== 1){
					right[i][j] = right[i][j+1] + m[i][j];
				}else{
					//��ǰֵ��0����ֻ���Լ���ֵ
					right[i][j] = m[i][j];
				}
			}
		}
		for(int i = m[0].length - 1; i >= 0; i--){
			down[m.length - 1][i] = m[m[0].length - 1][i] == 1 ? 1 : 0;
			for(int j = m.length - 2; j >= 0; j-- ){
				if(m[j+1][i]== 1){
					down[j][i] = down[j+1][i] + m[i][j];
				}else{
					down[j][i] = m[j][i];
				}
			}
		}
		
	}
	
	public static int getLen(int[][] m){
		int[][] down = new int[m.length][m[0].length];
		int[][] right = new int[m.length][m[0].length];
		setMap(m, down, right);
		int len = 0;
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m[0].length; j++){
				//kΪ���ɵ������α߳�
				int x= Math.min(m.length - i, m[0].length - j);
				for(int k = x; k > 0; k--){
					//���Ͻ��º���1�ĸ��������Ͻ��·�1�ĸ��������½��ҷ�1�ĸ���
					if(m[i][j] == 1 && right[i][j] >= k && down[i][j] >= k && 
							right[i+k-1][j] >= k && down[i][j+k-1] >= k){
						len = Math.max(k, len);
					}
				}
				
			}
		}
		return len;
	}
	
	public static int[][] generateRandom01Matrix(int rowSize, int colSize) {
		int[][] res = new int[rowSize][colSize];
		for (int i = 0; i != rowSize; i++) {
			for (int j = 0; j != colSize; j++) {
				res[i][j] = (int) (Math.random() * 2);
			}
		}
		return res;
	}

	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i != matrix.length; i++) {
			for (int j = 0; j != matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int[][] matrix = generateRandom01Matrix(5, 5);
		printMatrix(matrix);
		System.out.println(getLen(matrix));
		
		int[][] map = { { 1, 1, 1, 0 }, { 1, 1, 1, 0 }, { 1, 0, 1, 1 },{ 0, 0, 0, 0 } };
		System.out.println(getLen(map));
	}

}
