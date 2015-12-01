package com.dy.niuke;

public class Niu14_2 {
	public static int getMaxSum(int[] a) {
		//��¼��ǰ�ĺ�
		int sum = 0;
		//��¼���ֵ
		int maxNum = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			//�����С��0��˵��������������󣬵�����Ȼ������
			//���ֱ�Ӷ�����ʹ��sumΪ��ǰֵ��ע�ⲻ��0����Ϊ�����������ֶ��Ǹ���
			if (sum < 0) {
				sum = a[i];
			} else {
				//�ʹ���0�����Լ����ۼ�
				sum += a[i];
			}
			//ÿ������ͣ�Ҫ����max���������ܼ�¼�����ֵ
			maxNum = Math.max(maxNum, sum);
		}
		return maxNum;
	}

	public static void main(String[] args) {
		int[] a = {1,-2,3,5,-2,6,-1};
		 int[] data2 = {-2, -8, -1, -5, -9};
	        int[] data3 = {2, 8, 1, 5, 9};
		System.out.println(getMaxSum(a));
		System.out.println(getMaxSum(data3));
		System.out.println(getMaxSum(data2));

	}
}
