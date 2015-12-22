package com.ccti.dy.chapter1;

/**
 * @author dy
 *	Implement an algorithm to determine if a string has all unique characters.What
if you cannot use additional data structures?
 */
public class Q1_1 {

	public static boolean isUnique(String string){
		//ASCII
		if(string.length() > 128){
			return false;
		}
		boolean[] flag = new boolean[128];
		for(int i = 0; i < string.length(); i++){
			int val = string.charAt(i);
			if(flag[val]){
				return false;
			}else{
				flag[val] = true;
			}
			
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			System.out.println(word + ": " + isUnique(word));
		}
	}
}
