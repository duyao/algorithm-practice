package com.dy.niuke;



public class Niu5_1 {

	public int KMP(String string, String match){
		
		char[] cstring = string.toCharArray();
		char[] cmatch = match.toCharArray();
		//����next
		int [] next = initNext(cmatch);
		
		int i = 0, j = 0;
		while(i<string.length() && j<match.length()){
			if(cstring[i] == cmatch[j]){
				i++;
				j++;
			}else if(next[j] == -1){
				i++;
			}else{
				//�´δ�next[j]λ�ñȽϣ�����������j-next[j]��λ�ã���ʱi�����
				j = next[j];
			}
		
		}
		
		if(j == cmatch.length){
			//j����������match��,˵����ȫƥ��
			return i-j;
		}else{
			//û�б�����������ƥ��
			return -1;
		}
		
		
	}
	
	public int[] initNext(char[] cmatch){
		if(cmatch.length == 1){
			return new int[]{-1};
		}
		//next��match�ȳ�
		int[] next = new int[cmatch.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2, cn = 0;
		while(pos < next.length){
			//next��¼���ǵ�ǰλ����ǰ��״��������������ǰλ��
			if(cmatch[pos-1] == cmatch[cn]){
				next[pos++] = ++cn;
			}else {
				if(cn != 0){
					//posû����������Ϊ��Ҫ������ֱ��cn=0Ϊֹ
					cn = next[cn];
				}else{
					next[pos++] = 0;
				}
			}
		}
		
		return next;
	}
}

