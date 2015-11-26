package com.dy.niuke;

import java.util.Stack;

public class Niu11_1 {
	
	//反转栈
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			//栈空返回
			return;
		}
		//取出栈底元素
		//依次为1，2，3，4，5
		int i = getAndRemoveLastElement(stack);
		//递归调用，使得栈空
		reverse(stack);
		//压入栈底元素i
		//因此5，4，3，2，1被压入
		stack.push(i);
	}

	//取得栈底元素，并且其余元素与原始状态相同
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		//result取得栈顶
		int result = stack.pop();
		if (stack.isEmpty()) {
			//栈空返回栈顶，那么最后应该返回1
			return result;
		} else {
			//得到栈空的返回值1
			int last = getAndRemoveLastElement(stack);
			//此时栈是空的，压入上一次的res
			//依次为2，3，4，5
			stack.push(result);
			//返回last即1
			//由此可见每次返回的都是last1
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		for(int i = 1; i <= 5; i++){
			test.push(i);
		}
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}

	}

}
