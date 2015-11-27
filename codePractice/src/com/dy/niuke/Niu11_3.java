package com.dy.niuke;

import java.util.LinkedList;
import java.util.Queue;

public class Niu11_3 {

	public static class Node {
		Node left;
		Node right;
		int value;

		public Node(int value) {
			this.value = value;
		}
	}

	// !�Ƿָ�����#�ǿսڵ�

	// �������л�
	// �����������������
	public static String serialByPre(Node head) {
		if (head == null) {
			return "#!";
		}
		// ������ڵ㣬Ȼ�����ҽڵ�
		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}

	// �������л�
	public static Node reconByPreString(String preStr) {
		// �ָ��ַ���
		String[] values = preStr.split("!");
		// ���ַ���ӵ�������
		Queue<String> preQueue = new LinkedList<String>();
		for (int i = 0; i < values.length; i++) {
			preQueue.add(values[i]);
		}
		// ��ԭ��
		return reconPreOrder(preQueue);
	}

	// ��ԭ���Ĺ��������������������
	public static Node reconPreOrder(Queue<String> queue) {
		// �õ���ͷ���
		String string = queue.poll();
		if (string.equals("#")) {
			return null;
		}
		// ������ڵ�
		Node head = new Node(Integer.valueOf(string));
		// �������ҽڵ�
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		return head;
	}

	// ������л�
	public static String serialByLevel(Node head) {
		if (head == null) {
			return "#!";
		}
		Queue<Node> queue = new LinkedList<Node>();

		// ͷ�����Ӵ�����������ʱ����
		// ��Ϊ������ڿսڵ�ҲҪ����
		String res = Integer.valueOf(head.value) + "!";
		queue.add(head);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			// ���ʱ�������ҽڵ㣬��Ϊ�пսڵ�
			if (node.left != null) {
				res += Integer.valueOf(node.left.value) + "!";
				queue.add(node.left);
			} else {
				// �ս��ҲҪ����
				res += "#!";
			}
			if (node.right != null) {
				res += Integer.valueOf(node.right.value) + "!";
				queue.add(node.right);
			} else {
				res += "#!";
			}
		}
		return res;
	}

	public static Node reconByLevelString(String levelStr) {
		
		String[] strings = levelStr.split("!");
		//���α�������
		Queue<Node> queue = new LinkedList<Node>();
		int index = 0;
		//����ͷ��㲢���
		Node head = generateNodeByString(strings[index++]);
		//���ղ����
		if(head != null){
			queue.add(head);
		}
		while(!queue.isEmpty()){
			//����ͷ
			Node node = queue.poll();
			//�������㣬�����
			node.left = generateNodeByString(strings[index++]);
			if(node.left != null){
				queue.add(node.left);
			}
			//�����ҽ�㣬�����
			node.right = generateNodeByString(strings[index++]);
			if(node.right != null){
				queue.add(node.right);
			}
		}
		return head;

	}

	// ����value����node
	public static Node generateNodeByString(String val) {
		if (val.equals("#")) {
			return null;
		}
		return new Node(Integer.valueOf(val));
	}

	// for test -- print tree
	public static void printTree(Node head) {
		System.out.println("Binary Tree:");
		printInOrder(head, 0, "H", 17);
		System.out.println();
	}

	public static void printInOrder(Node head, int height, String to, int len) {
		if (head == null) {
			return;
		}
		printInOrder(head.right, height + 1, "v", len);
		String val = to + head.value + to;
		int lenM = val.length();
		int lenL = (len - lenM) / 2;
		int lenR = len - lenM - lenL;
		val = getSpace(lenL) + val + getSpace(lenR);
		System.out.println(getSpace(height * len) + val);
		printInOrder(head.left, height + 1, "^", len);
	}

	public static String getSpace(int num) {
		String space = " ";
		StringBuffer buf = new StringBuffer("");
		for (int i = 0; i < num; i++) {
			buf.append(space);
		}
		return buf.toString();
	}

	public static void main(String[] args) {
		Node head = null;
		printTree(head);

		String pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		String level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.right.right = new Node(5);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

		head = new Node(100);
		head.left = new Node(21);
		head.left.left = new Node(37);
		head.right = new Node(-42);
		head.right.left = new Node(0);
		head.right.right = new Node(666);
		printTree(head);

		pre = serialByPre(head);
		System.out.println("serialize tree by pre-order: " + pre);
		head = reconByPreString(pre);
		System.out.print("reconstruct tree by pre-order, ");
		printTree(head);

		level = serialByLevel(head);
		System.out.println("serialize tree by level: " + level);
		head = reconByLevelString(level);
		System.out.print("reconstruct tree by level, ");
		printTree(head);

		System.out.println("====================================");

	}

}
