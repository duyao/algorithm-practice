package com.dy.niuke;

public class Niu4_1 {
	//���ַ������
	public char [] changeToManacher(String s){
		char [] c = s.toCharArray();
		char [] res = new char[c.length * 2 + 1];
		for(int i = 0;i <res.length; i++){
			if(i % 2 == 0){
				res[i] = '#';
			}else{
				res[i] = c[i/2];
			}
		}
		return res;
	}
	
	public String Manacher(String s){
		char [] t = changeToManacher(s);
		int [] p = new int[t.length];
		int index = 0, r = 0;
		//��һ�������һ����p����0
		for (int i = 1; i < t.length -1 ; i++){
			int i_mirro = 2*index - i;//i�Ĺ���index�ĶԳƵ� = index + i - index
			
			
			if(r < i){
				//��i��r֮��,�����Լ��Գ���p��0
				p[i] = 0;
			}else{
				//i��r�ڲ�������r��λ��
				//��r��λ��ʱ��pΪ0
				p[i] = Math.min(p[i_mirro], r-i);
			}
			
			//�������ⲿ������rλ�õ�״����ֻ�ܱ��������Ƿ�Ϊ����
			while(i + p[i] +1 < t.length - 1 && i - p[i] -1 > -1){
				if(t[i + p[i] +1] == t[i - p[i] -1]){
					p[i]++;
				}else{
					break;
				}
				
			}
			
			//����index��r
			//r������İ뾶����λ�õ�����
			if(i + p[i] > r){
				r = i + p[i];
				index = i;
			}
			
		}
		
		//�ҵ���Ļ��ĳ���
		int maxP = 0;
		int center = 0;
		for (int i = 1; i < t.length - 1; i++){
			if(p[i] > maxP){
				maxP = p[i];
				center = i;
			}
		}
		
		return s.substring((center - maxP)/2, maxP);
		
	}

}
