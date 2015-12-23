package com.ccti.dy.chapter1;

/**
 * @author dy
 *	Write a method to replace all spaces in a string with'%20'. You may assume that
the string has sufficient space at the end of the string to hold the additional
characters, and that you are given the "true" length of the string. (Note: if implementing in Java, please use a character array so that you can perform this operation in place.)
EXAMPLE
Input: "Mr John Smith
Output: "Mr%20Dohn%20Smith"
 */
public class Q1_4 {
	public static String replaceSpace(String iniString, int length) {
		int space = 0;
		for(int i = 0; i < iniString.length(); i++){
			if(iniString.charAt(i) == ' '){
				space++;
			}
		}
		int resLen = iniString.length() + space * 2;
		char[] tmp = new char[resLen];
		for(int i = iniString.length()-1; i >= 0; i--){
			if(iniString.charAt(i) == ' '){
				tmp[--resLen] = '0';
				tmp[--resLen] = '2';
				tmp[--resLen] = '%';
			}else{
				tmp[--resLen] = iniString.charAt(i);
			}
		}

		return String.valueOf(tmp);
    }
	
	public static void main(String[] args) {
		String str = "abc d e f";
		System.out.println(str);
		String res = replaceSpace(str, str.length());
		System.out.println(res);
	}
	

}
