package com.dy.niuke;

import java.util.Stack;

public class Niu11_1 {
	
	//��תջ
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			//ջ�շ���
			return;
		}
		//ȡ��ջ��Ԫ��
		//����Ϊ1��2��3��4��5
		int i = getAndRemoveLastElement(stack);
		//�ݹ���ã�ʹ��ջ��
		reverse(stack);
		//ѹ��ջ��Ԫ��i
		//���5��4��3��2��1��ѹ��
		stack.push(i);
	}

	//ȡ��ջ��Ԫ�أ���������Ԫ����ԭʼ״̬��ͬ
	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		//resultȡ��ջ��
		int result = stack.pop();
		if (stack.isEmpty()) {
			//ջ�շ���ջ������ô���Ӧ�÷���1
			return result;
		} else {
			//�õ�ջ�յķ���ֵ1
			int last = getAndRemoveLastElement(stack);
			//��ʱջ�ǿյģ�ѹ����һ�ε�res
			//����Ϊ2��3��4��5
			stack.push(result);
			//����last��1
			//�ɴ˿ɼ�ÿ�η��صĶ���last1
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
