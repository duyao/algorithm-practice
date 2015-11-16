package com.dy.niuke;

public class Niu8_2 {
	public int coins1(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		return f1(arr, 0, aim);
	}

	// 暴力递归，无优化
	public static int f1(int[] a, int index, int aim) {
		// 方法数
		int res = 0;
		if (index == a.length) {
			// index已经到了数组的边界，且目标钱数已经减为0，说明这个方法是有效的
			if (aim == 0) {
				res = 1;
			}
		} else {
			for (int i = 0; a[index] * i <= aim; i++) {
				// 注意后面是index+1
				// 因此每次是先index增加，然后index的个数i增加，增加之后要将aim减少
				res += f1(a, index + 1, aim - a[index] * i);
			}
		}
		return res;
	}

	public int coins2(int[] arr, int aim) {
		if (arr == null || arr.length == 0 || aim < 0) {
			return 0;
		}
		//map是存储该方法是否计算过，值为-1表示计算过，且为用此方法返回值为0，map值为0，表示没有计算，
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
				//算过了
				if(map[index][aim-a[index] * i]!=0){
					//算过，且返回值为0
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
