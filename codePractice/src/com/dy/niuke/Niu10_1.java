package com.dy.niuke;

public class Niu10_1 {
	//�ж��ַ����Ƿ�Ϸ�
	public  static boolean isValid(String string){
		//- ���ȱ���Ϊż��
		if(string.length() % 2 == 0){
			return false;
		}
		char[] exp = string.toCharArray();
		//- ����λ����0����1
		for(int i = 0; i < exp.length; i += 2){
			if(exp[i] != '1' && exp[i] != '0'){
				return false;
			}
		}
		//- ż��λ����^|&
		for(int i = 1;i < exp.length; i += 2){
			if(exp[i] != '|' && exp[i] !='^' && exp[i] != '&'){
				return false;
			}
		}
		
		return true;
	}
	
	//�ж�l��r�ϵı��ʽ�ж���������desired�ķ���
	public static int p(String express, boolean desired, int l, int r){
		char[] exp = express.toCharArray();
		if(l == r){
			//ֻʣһ��Ԫ��
			if(exp[l] == '1'){
				return desired ? 1 : 0;
			}else{
				return desired ? 0 : 1;
			}
		}
		int res = 0;
		//��λ������ָ���ʽ���ֱ�������ֵ
		//�ܵĿ�����Ϊ��߿����������ұ߿�����
		for(int i = l+1; i < r; i += 2){
			if(desired){
				switch (exp[i]) {
				//desiredΪ1���������&,���ұ��붼��true
				case '&':
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					break;
				//desiredΪ1���������|,��1��0,��0��1����1��1
				case '|':
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					break;
				//desiredΪ1���������^,��1��0,��0��1
				case '^':
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					break;

				}
			}else{
				switch (exp[i]) {
				case '&':
					res += p(express, false, l, i-1)*p(express, true, i+1, r);
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, false, i+1, r);
					break;
				case '|':
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					break;
				case '^':
					res += p(express, false, l, i-1)*p(express, false, i+1, r);
					res += p(express, true, l, i-1)*p(express, true, i+1, r);
					break;

				}
				
			}
		}
		return res;
	}
	
	//�����ݹ�
	public static int num1(String express, boolean desired) {
		if(express == null || express.equals("")){
			return 0;
		}
		
		if(!isValid(express)){
			return 0;
		}
		return p(express, desired, 0, express.length()-1);
	}
	
	//�Ż��ݹ飬��¼�������ֵ
	//p(char[] exp, boolean desired, int l, int r)�ݹ�����3������������ʹ��2�Ŷ�ά��Ϳ���
	//һ�����¼�����true����һ����¼�����false
	public static int num2(String express, boolean desired){
		if(express == null || express.equals("")){
			return 0;
		}
		
		if(!isValid(express)){
			return 0;
		}
		char[] exp = express.toCharArray();
		//t[i][j]��ʾ��i��j��exp�н��Ϊtrue�Ľ����
		int[][] t = new int[exp.length][exp.length];
		//f[i][j]��ʾ��i��j��exp�н��Ϊfalse�Ľ����
		int[][] f = new int[exp.length][exp.length];
		t[0][0] = exp[0]=='1' ? 1 : 0;
		f[0][0] = exp[0]=='0' ? 1 : 0;
		//��iΪβ��jΪͷ����t[i][j]��f[i][j]
		//������������ɢ������
		for(int i = 2; i < exp.length; i += 2){
			//t[i][i]��ʾ��i��λ��Ϊtrue�Ľ����
			//���ֻҪ����t[i][i]��ֵ��ֵ1����1��ֵ0����0
			t[i][i] = exp[i]=='1' ? 1 : 0;
			f[i][i] = exp[i]=='0' ? 1 : 0;
			for(int j = i-2; j >= 0; j -= 2){
				//��k+1Ϊ�ֽ磬�ж�ǰ��ͺ��������Ŀ
				for(int k = j+1; k < i; k += 2){
					switch (exp[k]) {
					
					case '&':
						//k+1��&�������true�Ŀ�����ǰ1��1
						t[j][i] += t[j][k-1] * t[k+1][i];
						//k+1��&�������false�Ŀ�����ǰ1��0��ǰ0��1��ǰ0��0
						f[j][i] += f[j][k-1] * f[k+1][i] + f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						break;
					case '|':
						t[j][i] += t[j][k-1] * t[k+1][i] + f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						f[j][i] += f[j][k-1] * f[k+1][i];
						break;
					case '^':
						t[j][i] += f[j][k-1] * t[k+1][i] + t[j][k-1] * f[k+1][i];
						f[j][i] += t[j][k-1] * t[k+1][i] + f[j][k-1] * f[k+1][i];
						break;
					}
				}
				
			}
		}
		
		return desired ? t[0][exp.length-1] : f[0][exp.length-1];
	}

	public static void main(String[] args) {
		String express = "1^0|0|1";
		boolean desired = false;
		//String express = "1^0&0|1&1^0&0^1|0|1&1";
		//boolean desired = true;
		System.out.println(num1(express, desired));
		System.out.println(num2(express, desired));

	}
}
