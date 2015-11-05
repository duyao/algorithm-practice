package com.dy.niuke;

public class Niu3_4 {
	
	/**
	 * @param a ����a
	 * @param as ����a��һ�������±�
	 * @param ae ����a���һ�������±�
	 * @param b ����b
	 * @param bs ����b��һ�������±�
	 * @param be ����b���һ�������±�
	 * @return �ϲ�����λ��
	 */
	public int find (int []a, int as, int ae, int []b, int bs, int be){
		int len = ae - as + 1;
		int amid = as + len/2;
		int bmid = bs + len/2;
		
		if(len == 1){
			return Math.min(a[amid], b[bmid]);
		}
		
		//��ż��־λ
		int flag = 0;
		if(len%2 == 0){
			amid -= 1;
			bmid -= 1;
			flag = 1;
		}
		
		if(a[amid] > b[bmid]){
			return find(a, as, amid, b, bmid + flag, be);
		}else if(a[amid] < b[bmid]){
			return find(a, amid + flag, ae, b, bs, bmid);
		}else{
			return a[amid];
		}
		
//		if(len%2 == 1){
//			amid -= 1;
//			bmid -= 1;
//			if(a[amid] > b[bmid]){
//				return find(a, as, amid, b, bmid, be);
//			}else if(a[amid] < b[bmid]){
//				return find(a, amid, ae, b, bs, bmid);
//			}else{
//				return a[amid];
//			}
//		}else{
//			
//			if(a[amid] > b[bmid]){
//				return find(a, as, amid, b, bmid + 1, be);
//			}else if(a[amid] < b[bmid]){
//				return find(a, amid + 1, ae, b, bs, bmid);
//			}else{
//				return a[amid];
//			}
//		}

		
	}

}
