package com.dy.niuke;



public class Niu5_1 {

	public int KMP(String string, String match){
		
		char[] cstring = string.toCharArray();
		char[] cmatch = match.toCharArray();
		//生成next
		int [] next = initNext(cmatch);
		
		int i = 0, j = 0;
		while(i<string.length() && j<match.length()){
			if(cstring[i] == cmatch[j]){
				i++;
				j++;
			}else if(next[j] == -1){
				i++;
			}else{
				//下次从next[j]位置比较，就是跳过了j-next[j]个位置，此时i不变的
				j = next[j];
			}
		
		}
		
		if(j == cmatch.length){
			//j遍历完整的match串,说明完全匹配
			return i-j;
		}else{
			//没有遍历完整，无匹配
			return -1;
		}
		
		
	}
	
	public int[] initNext(char[] cmatch){
		if(cmatch.length == 1){
			return new int[]{-1};
		}
		//next与match等长
		int[] next = new int[cmatch.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2, cn = 0;
		while(pos < next.length){
			//next记录的是当前位置以前的状况，即不包括当前位置
			if(cmatch[pos-1] == cmatch[cn]){
				next[pos++] = ++cn;
			}else {
				if(cn != 0){
					//pos没有自增，因为还要继续，直到cn=0为止
					cn = next[cn];
				}else{
					next[pos++] = 0;
				}
			}
		}
		
		return next;
	}
}

