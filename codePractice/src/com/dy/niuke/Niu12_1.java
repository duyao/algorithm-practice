package com.dy.niuke;

public class Niu12_1 {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// 判断链表是否有环
	public static Node getLoopNode(Node head) {

		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}

		Node nq = head;
		Node ns = head.next.next;
		// 循环使得快慢指针相遇
		while (ns != nq) {
			if (ns == null || nq == null) {
				break;
			}
			ns = ns.next;
			nq = nq.next.next;
		}

		Node node = head;
		// 新指针和慢指针遍历，直到相遇
		while (ns != node) {
			if (ns == null || node == null) {
				return null;
			}
			node = node.next;
			ns = ns.next;
		}
		return node;
	}

	// 无环的时候找公共的节点
	public static Node noLoop(Node head1, Node head2) {
		// n是记录长链表比短链表长多少，然后让长链表走过这一段，之后一起走，找到公共节点
		int n = 0;
		// head1走完全程
		Node n1 = head1;
		while (n1.next != null) {
			n++;
			n1 = n1.next;
		}
		// head2走完全程
		Node n2 = head2;
		while (n2.next != null) {
			n--;
			n2 = n2.next;
		}
		// 最后一个节点不相同，则一定没有公共节点
		if (n1 != n2) {
			return null;
		}

		// 把n1设置为长度更长的链表
		n1 = n > 0 ? head1 : head2;
		n2 = n1 == head1 ? head2 : head1;
		// n1把多出来的部分走过
		n = Math.abs(n);
		while (n != 0) {
			n--;
			n1 = n1.next;
		}
		// n1==n2时就是公共节点
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		if (loop1 == loop2) {
			// 入环点相同，相交点在环外
			// 方法类似无环链表求公共点
			// 先求长度差，然后长的走过长度差，之后一起走，直到相遇
			int n = 0;
			Node n1 = head1;
			//遍历到入环点停止，而不是为空
			while (n1.next != loop1) {
				n++;
				n1 = n1.next;
			}
			Node n2 = head2;
			//遍历到入环点停止，而不是为空
			while (n2.next != loop2) {
				n--;
				n2 = n2.next;
			}
			
			n1 = n > 0 ? head1 : head2;
			n2 = n1 == head1 ? head2 : head1;
			n = Math.abs(n);
			//长的走过多余的部分
			while (n != 0) {
				n--;
				n1 = n1.next;
			}
			//一起走，直到相遇
			while (n1 != n2) {
				n1 = n1.next;
				n2 = n2.next;
			}
			return n1;
		} else {
			// 入环点不同，只要遍历一个环，检查另一个入环点是否在其中即可
			Node node = loop1;
			do {
				// loop2是否在loop1中
				if (node == loop2) {
					return node;
				}
				node = node.next;
			} while (node != loop1);
			// 不在其中，则不相交
			return null;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		// 12都没有环
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		// 12都有环
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		// 一个有环一个无环，不可能相交！
		return null;
	}

	public static void main(String[] args) {
		// 1->2->3->4->5->6->7->null
		Node head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);

		// 0->9->8->6->7->null
		Node head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

		// 1->2->3->4->5->6->7->4...
		head1 = new Node(1);
		head1.next = new Node(2);
		head1.next.next = new Node(3);
		head1.next.next.next = new Node(4);
		head1.next.next.next.next = new Node(5);
		head1.next.next.next.next.next = new Node(6);
		head1.next.next.next.next.next.next = new Node(7);
		head1.next.next.next.next.next.next.next = head1.next.next.next; // 7->4

		// 0->9->8->2...
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next; // 8->2
		System.out.println(getIntersectNode(head1, head2).value);

		// 0->9->8->6->7->4->5->6..
		head2 = new Node(0);
		head2.next = new Node(9);
		head2.next.next = new Node(8);
		head2.next.next.next = head1.next.next.next.next.next; // 8->6
		System.out.println(getIntersectNode(head1, head2).value);

	}
}
