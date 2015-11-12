package com.dy.niuke;

public class Niu6_3 {
	
	public static int getSmallSum(int[] a){
		
		if(a.length == 1 || a == null || a.length == 0){
			return 0;
		} 
		
		int ans = merge(a, 0, a.length-1);
		return ans;
	}
	
	public static int merge(int[] a, int low, int high){
		if(low == high){
			return 0;
		}
		
		int mid = (low + high)/2;
		int start1 = low, end1 = mid;
		int start2 = mid+1, end2 = high;
		//�͹鲢������ͬ��������С�Ͳ�������Ҫ���
		return merge(a, start1, end1) + merge(a, start2, end2) + sum(a, low, high);
		
		
		//����Ҫ��С�ͣ�ÿ����ӣ���˲�����鲢�����������Ѻ��������Ҳд����������ֿ�д���������
//		int k = 0;
//		int[] reg = new int[high-low+1];
//		int tmpSum = 0;
//		//�鲢�����У�����Ĳ�����������������С�ͼ�����
//		while(start1 <= end1 && start2 <= end2){
//			//���С�ڵ����ұߣ���С��������
//			if(a[start1] <= a[start2]){
//				tmpSum += a[start1]*(end2-start2+1);
//				reg[k++] = a[start1++];
//			}else{
//				//ֻ���򣬲����
//				reg[k++] = a[start2++];
//			}
//			
//		}
//		while(start1 <= end1){
//			reg[k++] = a[start1++];
//		}
//		while(start2 <= end2){
//			reg[k++] = a[start2++];
//		}
//		for(int i = low, j = 0; i <= high && j<=k; i++,j++){
//			a[i] = reg[j];
//		}
//		return tmpSum;
	}
	
	public static int sum(int[] a, int low, int high){
		
		int mid = (low + high)/2;
		int start1 = low, end1 = mid;
		int start2 = mid+1, end2 = high;
		
		//k��reg�����λ��
		int k = 0;
		//reg����ź����������
		int[] reg = new int[high-low+1];
		//tmpSumС��
		int tmpSum = 0;
		//�鲢�����У�����Ĳ�����������������С�ͼ�����
		while(start1 <= end1 && start2 <= end2){
			//���С�ڵ����ұߣ���С��������
			if(a[start1] <= a[start2]){
				//ע��������С�����ֳ��Ա��������ֵĸ���
				tmpSum += a[start1]*(end2-start2+1);
				//����
				reg[k++] = a[start1++];
			}else{
				//ֻ���򣬲����
				reg[k++] = a[start2++];
			}
			
		}
		//ʣ�������
		while(start1 <= end1){
			reg[k++] = a[start1++];
		}
		while(start2 <= end2){
			reg[k++] = a[start2++];
		}
		
		for(int i = low, j = 0; i <= high && j<=k; i++,j++){
			//���ź�������鸳ֵ��a
			a[i] = reg[j];
		}
		return tmpSum;
	}
	
	public static void main(String[] args) {
		//int[] a = {1,3,5,2,4,6};
		int[] a = {1,2,3,4,5,6};
		int ans = getSmallSum(a);
		System.out.println(ans);
	}

}
