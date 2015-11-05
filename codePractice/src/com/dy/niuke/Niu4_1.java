package com.dy.niuke;

public class Niu4_1 {
	//对字符串添加
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
		//第一个和最后一个的p都是0
		for (int i = 1; i < t.length -1 ; i++){
			int i_mirro = 2*index - i;//i的关于index的对称点 = index + i - index
			
			
			if(r < i){
				//当i在r之外,关于自己对称是p是0
				p[i] = 0;
			}else{
				//i在r内部，或在r的位置
				//在r的位置时，p为0
				p[i] = Math.min(p[i_mirro], r-i);
			}
			
			//对于在外部或者在r位置的状况，只能遍历两侧是否为回文
			while(i + p[i] +1 < t.length - 1 && i - p[i] -1 > -1){
				if(t[i + p[i] +1] == t[i - p[i] -1]){
					p[i]++;
				}else{
					break;
				}
				
			}
			
			//更新index和r
			//r是最长回文半径所在位置的坐标
			if(i + p[i] > r){
				r = i + p[i];
				index = i;
			}
			
		}
		
		//找到最长的回文长度
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
