package com.ccti.dy.chapter1;

/**
 * @author dy
 *Implement a method to perform basic string compression using the counts
of repeated characters. For example, the string aabcccccaaa would become
a2blc5a3. If the "compressed"string would not become smaller than the original string, your method should return the original string.
 */
public class Q1_5 {
	public static String zipString(String initString) {
		StringBuffer sBuffer = new StringBuffer();
		char pre = initString.charAt(0);
		int count = 1;
		for (int i = 1; i < initString.length(); i++) {
			if(initString.charAt(i) == pre){
				count ++;
			}else{
				sBuffer.append(pre);
				sBuffer.append(Integer.valueOf(count));
				pre = initString.charAt(i);
				count = 1;
			}
		}
		sBuffer.append(pre);
		sBuffer.append(Integer.valueOf(count));
		return sBuffer.toString();
    }
	public static void main(String[] args) {
		String str = "abbccccccde";
		System.out.println(zipString(str));
	}
}
