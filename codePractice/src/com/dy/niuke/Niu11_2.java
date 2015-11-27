package com.dy.niuke;

import java.util.Stack;

import javax.swing.text.AbstractDocument.BranchElement;


public class Niu11_2 {
	
	//链表结构
	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}
	
	public static boolean isPalindrome1(Node head) {
		if(head == null || head.next == null){
			//链表为空或者只有一个元素
			return true;
		}
		//慢指针
		Node ns = head;
		//快指针
		Node nq = head;
		//这里判断的条件不是nq.next.next
		//因为可能nq到了末尾，而nq.next为空，nq.next.next是不存在的
		//也可能是nq为被赋值为空
		while(nq != null && nq.next != null){
			//快指针一次走2步，慢指针一次走一步
			ns = ns.next;
			nq = nq.next.next;
		}
		Stack<Node> stack = new Stack<Node>();
		//慢指针的后半段入栈
		while(ns != null){
			stack.push(ns);
			ns = ns.next;
		}
		
		//栈中内容与链表头开始比较
		while(!stack.empty()){
			if(head.value != stack.pop().value){
				//元素不符合
				return false;
			}
			head = head.next;
		}
		
		return true;
	}
	
	//只用空间复杂度O(1)
	public static boolean isPalindrome2(Node head) {
		boolean res = true;
		if(head == null || head.next == null){
			//链表为空或者只有一个元素
			return true;
		}
		//慢指针
		Node ns = head;
		//快指针
		Node nq = head;	
		while(nq != null && nq.next != null){
			ns = ns.next;
			nq = nq.next.next;
		}

		
		//将ns后面的链表反转
		Node prior = ns;
		Node cur = ns.next;
		//两个节点单独判断
		if(cur == null){
			//说明此链表中只有2个元素,直接返回不需要修复
			return head.value == ns.value ? true: false;
		}
		//只有两个节点的时候，无nextNode
		Node nextNode = cur.next;
		//将中间的节点的后继设为空
		ns.next = null;
		while(cur != null) {
			//反向指
			cur.next = prior;
			prior = cur;
			cur = nextNode;
			//判空,因为cur为空，nextNode不存在
			if(cur == null){
				break;
			}
			nextNode = nextNode.next;
		} 
		
		//结束后prior就是头结点
		//head和prior不能破坏因为还要修复链表
		ns = head;
		cur = prior;
		//判断是否是回文
		while(ns != null && cur != null){
			if(ns.value != cur.value){
				res = false;
			}
			ns = ns.next;
			cur = cur.next;
		}
		

		//修复链表
		cur = prior.next;
		//nextNode可能为空，当只有3个元素的时候，所以下面重置nextNode会判断
		nextNode = cur.next;
		prior.next = null;
		while(cur != null){
			cur.next = prior;
			prior = cur;
			cur = nextNode;
			//判空,因为cur为空，nextNode不存在
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
