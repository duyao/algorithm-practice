package com.dy.niuke;

import java.util.Stack;

import javax.swing.text.AbstractDocument.BranchElement;


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
		boolean res = true;
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

		
		//��ns���������ת
		Node prior = ns;
		Node cur = ns.next;
		//�����ڵ㵥���ж�
		if(cur == null){
			//˵����������ֻ��2��Ԫ��,ֱ�ӷ��ز���Ҫ�޸�
			return head.value == ns.value ? true: false;
		}
		//ֻ�������ڵ��ʱ����nextNode
		Node nextNode = cur.next;
		//���м�Ľڵ�ĺ����Ϊ��
		ns.next = null;
		while(cur != null) {
			//����ָ
			cur.next = prior;
			prior = cur;
			cur = nextNode;
			//�п�,��ΪcurΪ�գ�nextNode������
			if(cur == null){
				break;
			}
			nextNode = nextNode.next;
		} 
		
		//������prior����ͷ���
		//head��prior�����ƻ���Ϊ��Ҫ�޸�����
		ns = head;
		cur = prior;
		//�ж��Ƿ��ǻ���
		while(ns != null && cur != null){
			if(ns.value != cur.value){
				res = false;
			}
			ns = ns.next;
			cur = cur.next;
		}
		

		//�޸�����
		cur = prior.next;
		//nextNode����Ϊ�գ���ֻ��3��Ԫ�ص�ʱ��������������nextNode���ж�
		nextNode = cur.next;
		prior.next = null;
		while(cur != null){
			cur.next = prior;
			prior = cur;
			cur = nextNode;
			//�п�,��ΪcurΪ�գ�nextNode������
			if(cur == null){
				break;
			}
			nextNode = nextNode.next;
		}
		return res;
		
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
		
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(1);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");
		
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");

		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = new Node(4);
		head.next.next.next.next = new Node(5);
		head.next.next.next.next.next = new Node(5);
		head.next.next.next.next.next.next = new Node(4);
		head.next.next.next.next.next.next.next = new Node(3);
		head.next.next.next.next.next.next.next.next = new Node(2);
		head.next.next.next.next.next.next.next.next.next = new Node(1);
		System.out.print(isPalindrome1(head) + " | ");
		printLinkedList(head);
		System.out.print(isPalindrome2(head) + " | ");
		printLinkedList(head);
		System.out.println("=========================");
	}

}
