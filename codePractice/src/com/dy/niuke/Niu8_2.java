package com.dy.niuke;

public class Niu8_2 {
	public int coins1(int[] arr, int aim) {
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

	public int coins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		//map�Ǵ洢�÷����Ƿ�������ֵΪ-1��ʾ���������Ϊ�ô˷�������ֵΪ0��mapֵΪ0����ʾû�м��㣬
		int[][] map = new int[arr.length + 1][aim + 1];
		return f2(arr, 0, aim, map);
	}
	public static int f2(int[] a, int index, int aim, int[][] map){
		int res = 0;
		if(index == a.length){
			if(aim == 0){
				return 1;
			}
		}else{
			int maxVal = 0;
			for (int i = 0; a[index] * i <= aim; i++) {
				//�����
				if(map[index][aim-a[index] * i]!=0){
					//������ҷ���ֵΪ0
					if(map[index][aim-a[index] * i] == -1){
						res ++;
					}else{
						res += f2(a, index + 1, aim - a[index] * i, map);
					}
				}else{
					res += f2(a, index + 1, aim - a[index] * i, map);
					map[index][aim-a[index] * i]= res==1?-1:maxVal;
				}
				
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 10, 25, 1 };
		int aim = 15;
		// System.out.println(f1(arr, 0, aim));
		String string = "fdsafdsa";
		System.out.println(string.charAt(0));
	}

}
