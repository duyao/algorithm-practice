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
		//右边比第一个比当前结点大
		HashMap<Node, Node> rightMap = new HashMap<Node, Node>();
		//左边比第一个比当前结点大
		HashMap<Node, Node> leftMap = new HashMap<Node, Node>();
		
		//这里必须将所有结点存储到一个数组中，然后以后对node数组取出，修改，才能地址修改
		//如果不这样做，后续都对a建立新的结点，那么修改左右子树的每个结点都是新结点，不能保存原来的修改
		Node[] arr = new Node[a.length];
		for (int i = 0; i < a.length; i++) {
			arr[i] = new Node(a[i]);
		}

		// 初始化leftMap，记录左边第一个大
		for (int i = 0; i < arr.length; i++) {
			// Node cur = new Node(arr[i]);
			//对数组中的值取出，操作，这样才能保存修改
			Node cur = arr[i];
			//比当前结点小的pop
			while (!stack.empty() && stack.peek().value <= arr[i].value) {
				popSetMap(stack, leftMap);
			}
			// 比当前元素小的全部被pop后，放入
			stack.push(cur);
		}
		// 栈中可能会有剩余元素
		while (!stack.empty()) {
			popSetMap(stack, leftMap);
		}

		// 初始化rightMap
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
		// 建立树
		Node head = null;
		for (int i = 0; i < arr.length; i++) {
			// Node node = new Node(arr[i]);
			//仍然对node数组操作，左右子树的信息才能保存
			Node node = arr[i];
			Node lNode = rightMap.get(node);
			Node rNode = leftMap.get(node);
			//左右都没有比当前大的，说明最大，是根节点
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
				//左右都有比当前大的，选择较小的当做父亲节点
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
		// stack内是按照栈底到栈顶从大到小的顺序，因此栈底小，栈顶大
		// 相邻靠上的元素的第一个比他大的一定是他的下一个
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
