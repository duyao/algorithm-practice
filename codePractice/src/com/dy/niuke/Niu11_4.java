package com.dy.niuke;

import java.util.HashMap;
import java.util.Stack;

public class Niu11_4 {

	public static class Node {
		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	public static Node getMaxTree(int[] a) {
		if (a == null || a.length == 0) {
			return null;
		}
		Stack<Node> stack = new Stack<Node>();
		//�ұ߱ȵ�һ���ȵ�ǰ����
		HashMap<Node, Node> rightMap = new HashMap<Node, Node>();
		//��߱ȵ�һ���ȵ�ǰ����
		HashMap<Node, Node> leftMap = new HashMap<Node, Node>();
		
		//������뽫���н��洢��һ�������У�Ȼ���Ժ��node����ȡ�����޸ģ����ܵ�ַ�޸�
		//���������������������a�����µĽ�㣬��ô�޸�����������ÿ����㶼���½�㣬���ܱ���ԭ�����޸�
		Node[] arr = new Node[a.length];
		for (int i = 0; i < a.length; i++) {
			arr[i] = new Node(a[i]);
		}

		// ��ʼ��leftMap����¼��ߵ�һ����
		for (int i = 0; i < arr.length; i++) {
			// Node cur = new Node(arr[i]);
			//�������е�ֵȡ�����������������ܱ����޸�
			Node cur = arr[i];
			//�ȵ�ǰ���С��pop
			while (!stack.empty() && stack.peek().value <= arr[i].value) {
				popSetMap(stack, leftMap);
			}
			// �ȵ�ǰԪ��С��ȫ����pop�󣬷���
			stack.push(cur);
		}
		// ջ�п��ܻ���ʣ��Ԫ��
		while (!stack.empty()) {
			popSetMap(stack, leftMap);
		}

		// ��ʼ��rightMap
		for (int i = arr.length - 1; i >= 0; i--) {
			// Node cur = new Node(arr[i]);
			Node cur = arr[i];
			while (!stack.empty() && stack.peek().value <= arr[i].value) {
				popSetMap(stack, rightMap);
			}
			stack.push(cur);
		}
		while (!stack.empty()) {
			popSetMap(stack, rightMap);
		}

		System.out.println("rightMap:");
		PrintHashMap(rightMap);
		System.out.println("leftMap:");
		PrintHashMap(leftMap);
		// ������
		Node head = null;
		for (int i = 0; i < arr.length; i++) {
			// Node node = new Node(arr[i]);
			//��Ȼ��node���������������������Ϣ���ܱ���
			Node node = arr[i];
			Node lNode = rightMap.get(node);
			Node rNode = leftMap.get(node);
			//���Ҷ�û�бȵ�ǰ��ģ�˵������Ǹ��ڵ�
			if (lNode == null && rNode == null) {
				head = node;
			} else if (lNode == null && rNode != null) {
				if (rNode.left == null) {
					rNode.left = node;
				} else {
					rNode.right = node;
				}
			} else if (lNode != null && rNode == null) {
				if (lNode.left == null) {
					lNode.left = node;
				} else {
					lNode.right = node;
				}
			} else {
				//���Ҷ��бȵ�ǰ��ģ�ѡ���С�ĵ������׽ڵ�
				Node parent = lNode.value < rNode.value ? lNode : rNode;
				if (parent.left == null) {
					parent.left = node;
				} else {
					parent.right = node;
				}
			}
		}
		return head;
	}

	
	public static void PrintHashMap(HashMap<Node, Node> hashMap) {
		for (Node key : hashMap.keySet()) {
			Node value = hashMap.get(key);
			if (key == null) {
				System.out.print("-1");
			} else {
				System.out.print(key.value);
			}
			System.out.print("->");
			if (value == null) {
				System.out.print("-1");
			} else {
				System.out.print(value.value);
			}
			System.out.println();

		}
		
	}

	public static void popSetMap(Stack<Node> stack, HashMap<Node, Node> hashMap) {
		Node node = stack.pop();
		// stack���ǰ���ջ�׵�ջ���Ӵ�С��˳�����ջ��С��ջ����
		// ���ڿ��ϵ�Ԫ�صĵ�һ���������һ����������һ��
		if (stack.empty()) {
			hashMap.put(node, null);
		} else {
			hashMap.put(node, stack.peek());
		}

	}

	public static void printPreOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.value + " ");
		printPreOrder(node.left);
		printPreOrder(node.right);
	}
	
	public static void printInOrder(Node node) {
		if (node == null) {
			return;
		}
		printInOrder(node.left);
		System.out.print(node.value + " ");
		printInOrder(node.right);
	}

	public static void main(String[] args) {
		int[] uniqueArr = { 3, 1, 5, 2, 4};
		Node head = getMaxTree(uniqueArr);
		printPreOrder(head);
		System.out.println();
		printInOrder(head);
		System.out.println();
	}

}
