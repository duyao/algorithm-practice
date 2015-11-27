package com.dy.niuke;

public class Niu12_1 {

	public static class Node {
		public int value;
		public Node next;

		public Node(int data) {
			this.value = data;
		}
	}

	// �ж������Ƿ��л�
	public static Node getLoopNode(Node head) {

		if (head == null || head.next == null || head.next.next == null) {
			return null;
		}

		Node nq = head;
		Node ns = head.next.next;
		// ѭ��ʹ�ÿ���ָ������
		while (ns != nq) {
			if (ns == null || nq == null) {
				break;
			}
			ns = ns.next;
			nq = nq.next.next;
		}

		Node node = head;
		// ��ָ�����ָ�������ֱ������
		while (ns != node) {
			if (ns == null || node == null) {
				return null;
			}
			node = node.next;
			ns = ns.next;
		}
		return node;
	}

	// �޻���ʱ���ҹ����Ľڵ�
	public static Node noLoop(Node head1, Node head2) {
		// n�Ǽ�¼������ȶ��������٣�Ȼ���ó������߹���һ�Σ�֮��һ���ߣ��ҵ������ڵ�
		int n = 0;
		// head1����ȫ��
		Node n1 = head1;
		while (n1.next != null) {
			n++;
			n1 = n1.next;
		}
		// head2����ȫ��
		Node n2 = head2;
		while (n2.next != null) {
			n--;
			n2 = n2.next;
		}
		// ���һ���ڵ㲻��ͬ����һ��û�й����ڵ�
		if (n1 != n2) {
			return null;
		}

		// ��n1����Ϊ���ȸ���������
		n1 = n > 0 ? head1 : head2;
		n2 = n1 == head1 ? head2 : head1;
		// n1�Ѷ�����Ĳ����߹�
		n = Math.abs(n);
		while (n != 0) {
			n--;
			n1 = n1.next;
		}
		// n1==n2ʱ���ǹ����ڵ�
		while (n1 != n2) {
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}

	public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
		if (loop1 == loop2) {
			// �뻷����ͬ���ཻ���ڻ���
			// ���������޻������󹫹���
			// ���󳤶ȲȻ�󳤵��߹����Ȳ֮��һ���ߣ�ֱ������
			int n = 0;
			Node n1 = head1;
			//�������뻷��ֹͣ��������Ϊ��
			while (n1.next != loop1) {
				n++;
				n1 = n1.next;
			}
			Node n2 = head2;
			//�������뻷��ֹͣ��������Ϊ��
			while (n2.next != loop2) {
				n--;
				n2 = n2.next;
			}
			
			n1 = n > 0 ? head1 : head2;
			n2 = n1 == head1 ? head2 : head1;
			n = Math.abs(n);
			//�����߹�����Ĳ���
			while (n != 0) {
				n--;
				n1 = n1.next;
			}
			//һ���ߣ�ֱ������
			while (n1 != n2) {
				n1 = n1.next;
				n2 = n2.next;
			}
			return n1;
		} else {
			// �뻷�㲻ͬ��ֻҪ����һ�����������һ���뻷���Ƿ������м���
			Node node = loop1;
			do {
				// loop2�Ƿ���loop1��
				if (node == loop2) {
					return node;
				}
				node = node.next;
			} while (node != loop1);
			// �������У����ཻ
			return null;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2) {
		if (head1 == null || head2 == null) {
			return null;
		}
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		// 12��û�л�
		if (loop1 == null && loop2 == null) {
			return noLoop(head1, head2);
		}
		// 12���л�
		if (loop1 != null && loop2 != null) {
			return bothLoop(head1, loop1, head2, loop2);
		}
		// һ���л�һ���޻����������ཻ��
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
