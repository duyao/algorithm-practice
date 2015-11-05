package com.dy.niuke;

import org.junit.Test;

public class TestNiu {

	@Test
	public void test2_2() {
		Niu2_3 niu2_2 = new Niu2_3();
		int a[] = {1,5,3,4,2,6,7};
		int a1[] = {1};
		int ans = niu2_2.Find(a1);
		System.out.println(ans);
	}
	
	@Test
	public void test2_4_1(){
		Niu2_4 niu2_4 = new Niu2_4();
		int a[] = {2,3,1,1,7};
		int ans = niu2_4.ans1(a);
		System.out.println(ans);
	}
	
	@Test
	public void test2_4_2(){
		Niu2_4 niu2_4 = new Niu2_4();
		int a[] = {2,3,1,1,7};
		int ans = niu2_4.ans2(a);
		System.out.println(ans);
	}
	
	@Test
	public void test3_1(){
		Niu3_1 niu3_1 = new Niu3_1();
		int a[] = {3,2,5,6,7};
		int ans = niu3_1.find(a);
		System.out.println(ans);
		int a1 = niu3_1.nn(a);
		System.out.println(a1);
	}
	
	@Test
	public void test3_4(){
		Niu3_4 niu3_4 = new Niu3_4();
		int a[] = {1,2,3,4};
		int b[] = {3,4,5,6};
//		int a[] = {0,1,2};
//		int b[] = {3,4,5};
		int ans = niu3_4.find(a, 0, a.length-1, b, 0, b.length-1);
		
		System.out.println(ans);
	}
	
	@Test
	public void test4_1(){
		Niu4_1 niu4_1 = new Niu4_1();
		String string = "abc123321ab";
		//String string = "abc1234321ab";
		String reString = niu4_1.Manacher(string);
		System.out.println(reString);
		
	}
	
	@Test
	public void test5_1(){
		Niu5_1 niu5_1 = new Niu5_1();
		String string = "ABC ABCDAB ABCDABCDABDE";
		String match = "ABCDABD";
		int res = niu5_1.KMP(string, match);
		System.out.println(res);
		
	}

}
