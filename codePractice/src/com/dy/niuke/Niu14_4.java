package com.dy.niuke;

public class Niu14_4 {
	public static int getMaxtrixSum(int[][] a) {
		
		int maxNum = Integer.MIN_VALUE;
		//�ָ��i
		for(int i = 0; i < a.length; i++){
			//��Ÿ����������еĺͣ���tmp[i]��ʾi���ڸ��е�����ֵ�ĺ�
			int[] tmp = new int[a[0].length];
			//�ɱ�����012��12��2��
			for(int k = i; k < a.length; k++){
				//�����������͵Ĺ���
				int curSum = 0;
				for(int j = 0; j < a[0].length; j++){
					//�ȸ���tmp��Ȼ�������
					tmp[j] += a[k][j];
					if(curSum < 0){
						curSum = tmp[j];
					}else{
						curSum += tmp[j];
					}
					maxNum = Math.max(curSum, maxNum);
				}
			}
		}
		return maxNum;

	}

	public static void main(String[] args) {
		int a1[][] = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
		int a2[][] = { { -1, -1, -1 }, { -1, 2, 2 }, { -1, -1, -1 } };
		System.out.println(getMaxtrixSum(a1));
		System.out.println(getMaxtrixSum(a2));
	}
}
