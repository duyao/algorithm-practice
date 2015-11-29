package com.dy.niuke;

import java.util.HashMap;


public class Niu14_5 {
	
	public static int getKLen(int[] arr, int k){
		//key��ʾ�͵���ֵ��value��ʾ��һ�γ��ָ�ֵ��λ��
		//��mapֻ��¼��һ�γ��ֵ����
		HashMap<Integer, Integer> mymap = new HashMap<Integer, Integer>();
		//��Ϊ��0�ļ�¼������ֵ����ȡ�����ᱻ�Զ�����
		//���������ӣ���ʾ�ӿ�ͷ��ѡ
		mymap.put(0, -1);
		int sum = 0;
		int len = 0;
		for (int i = 0; i < arr.length; i++) {
			//��i��β�ĺ�
			sum += arr[i];
			//ֻ��¼��һ�γ��ֵĺͣ���Ϊ���������
			if(!mymap.containsKey(sum)){
				mymap.put(sum, i);
			}
			//�����sum[j]+k=sum[i]��˵��(j,i]�Ĳ��ֺ���k
			if(mymap.containsKey(sum - k)){
				int j = mymap.get(sum - k);
				//��j��β�ĺͺ���i��β�ĺͣ��ڼ�ĳ�����i-j
				len = Math.max(len, i - j);
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		int[] a = {5,5,3,2,6,4,3};
		int k = 15;
		System.out.println(getKLen(a,k));
	}
}
