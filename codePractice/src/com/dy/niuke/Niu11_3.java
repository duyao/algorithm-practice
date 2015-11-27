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

	// !是分隔符，#是空节点

	// 先序序列化
	// 类似于树的先序遍历
	public static String serialByPre(Node head) {
		if (head == null) {
			return "#!";
		}
		// 处理根节点，然后左右节点
		String res = head.value + "!";
		res += serialByPre(head.left);
		res += serialByPre(head.right);
		return res;
	}

	// 先序反序列化
	public static Node reconByPreString(String preStr) {
		// 分割字符串
		String[] values = preStr.split("!");
		// 将字符添加到队列中
		Queue<String> preQueue = new LinkedList<String>();
		for (int i = 0; i < values.length; i++) {
			preQueue.add(values[i]);
		}
		// 还原树
		return reconPreOrder(preQueue);
	}

	// 还原树的过程类似于树的先序遍历
	public static Node reconPreOrder(Queue<String> queue) {
		// 得到队头结点
		String string = queue.poll();
		if (string.equals("#")) {
			return null;
		}
		// 处理根节点
		Node head = new Node(Integer.valueOf(string));
		// 处理左右节点
		head.left = reconPreOrder(queue);
		head.right = reconPreOrder(queue);
		return head;
	}

	// 层次序列化
	public static String serialByLevel(Node head) {
		if (head == null) {
			return "#!";
		}
		Queue<Node> queue = new LinkedList<Node>();

		// 头结点入队处理，而不出队时处理
		// 因为这里对于空节点也要处理
		String res = Integer.valueOf(head.value) + "!";
		queue.add(head);
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			// 入队时处理左右节点，因为有空节点
			if (node.left != null) {
				res += Integer.valueOf(node.left.value) + "!";
				queue.add(node.left);
			} else {
				// 空结点也要处理
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
		//与层次遍历相似
		Queue<Node> queue = new LinkedList<Node>();
		int index = 0;
		//处理头结点并入队
		Node head = generateNodeByString(strings[index++]);
		//不空才入队
		if(head != null){
			queue.add(head);
		}
		while(!queue.isEmpty()){
			//出队头
			Node node = queue.poll();
			//处理左结点，并入队
			node.left = generateNodeByString(strings[index++]);
			if(node.left != null){
				queue.add(node.left);
			}
			//处理右结点，并入队
			node.right = generateNodeByString(strings[index++]);
			if(node.right != null){
				queue.add(node.right);
			}
		}
		return head;

	}

	// 根据value返回node
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
