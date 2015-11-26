package com.dy.niuke;

import java.util.Stack;


public class Niu11_2 {
	
	//����ṹ
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static boolean isPalindrome1(Node head) {
		if(head == null || head.next == null){
			//����Ϊ�ջ���ֻ��һ��Ԫ��
			return true;
		}
		//��ָ��
		Node ns = head;
		//��ָ��
		Node nq = head;
		//�����жϵ���������nq.next.next
		//��Ϊ����nq����ĩβ����nq.nextΪ�գ�nq.next.next�ǲ����ڵ�
		//Ҳ������nqΪ����ֵΪ��
		while(nq != null && nq.next != null){
			//��ָ��һ����2������ָ��һ����һ��
			ns = ns.next;
			nq = nq.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		//��ָ��ĺ�����ջ
		while(ns != null){
			stack.push(ns);
			ns = ns.next;
		}
		
		//ջ������������ͷ��ʼ�Ƚ�
		while(!stack.empty()){
			if(head.value != stack.pop().value){
				//Ԫ�ز�����
				return false;
			}
			head = head.next;
		}
		
		return true;
	}
	
	//ֻ�ÿռ临�Ӷ�O(1)
	public static boolean isPalindrome2(Node head) {
		if(head == null || head.next == null){
			//����Ϊ�ջ���ֻ��һ��Ԫ��
			return true;
		}
		//��ָ��
		Node ns = head;
		//��ָ��
		Node nq = head;	
		while(nq != null && nq.next != null){
			ns = ns.next;
			nq = nq.next.next;
		}

		//�����ڵ㵥���ж�
		//��ns���������ת
		
		Node prior = ns;
		Node cur = ns.next;
		Node nextNode = cur.next;
		ns.next = null;
		while(cur != null) {
			cur.next = prior;
			prior = cur;
			cur = nextNode;
			if(cur)//�п�
			nextNode = nextNode.next;
		} 
		
		//������prior����ͷ���
		while(head != null && prior != null){
			if(head.value != prior.value){
				return false;
			}
			head = head.next;
			prior = prior.next;
		}
		return true;
		
		
		
	}
	
	
	
	public static void printLinkedList(Node node) {
		System.out.print("Linked List: ");
		while (node != null) {
			System.out.print(node.value + " ");
			node = node.next;
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) {
		Node head = null;
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(1);
//		printLinkedList(head);
//		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.println(isPalindrome2(head) + " | ");
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(1);
		head.next.next.next.next.next = new Node(1);
		head.next.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next.next = new Node(3);
		head.next.next.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next.next.next = new Node(1);
		printLinkedList(head);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.println(isPalindrome2(head) + " | ");
		System.out.println("=========================");
	}

}
