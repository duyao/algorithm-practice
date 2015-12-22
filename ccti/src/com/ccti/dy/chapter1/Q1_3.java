package com.ccti.dy.chapter1;

/**
 * @author dy
 *
 *Given two strings, write a method to decide if one is a permutation(排列) of the other.
 */
public class Q1_3 {
	public static boolean isPermutation(String string1, String string2){
		if(string1.length() != string2.length() || string1 == null || string2 == null){
			return false;
		}
		//记录出现的频度
		int[] flag = new int[128];
		for(int i = 0; i < string1.length(); i++){
			flag[string1.charAt(i)]++;
		}
		for(int i = 0; i < string2.length(); i++){
			flag[string2.charAt(i)]--;
			if(flag[string2.charAt(i)] < 0){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram = isPermutation(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram);
			//System.out.println(anagram(word1, word2));
		}
	}

}
