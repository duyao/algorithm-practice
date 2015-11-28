package com.dy.niuke;

import java.util.HashMap;

public class Niu12_4 {
	//�ڵ���Ϣ
	public static class Node{
		int value;
		Node next;
		public Node(int value){
			this.value = value;
		}
	}
	public static class MessageBox{
		HashMap<Integer, Node> headMap;
		HashMap<Integer, Node> tailMap;
		//������ӡ������
		int printNum;
		public MessageBox(){
			//��ʾδ��ӡ�����е�ͷ���
			headMap = new HashMap<Integer, Node>();
			//��ʾδ��ӡ�����е�β���
			tailMap = new HashMap<Integer, Node>();
			printNum = 1;
		}
		
		public void receive(int value) {
			if(value < 1){
				return;
			}
			//��node���������Ľڵ�
			Node node = new Node(value);
			headMap.put(value, node);
			tailMap.put(value, node);
			//����value-1��ͷ�����hand��Ӧ�ð�������tail�в�Ӧ�ð���
			//���tailȥ��value-1,headȥ��value
			if(tailMap.containsKey(value-1)){
				tailMap.get(value-1).next = node;
				headMap.remove(value);
				tailMap.remove(value-1);
			}
			//value+1��β��tail��Ӧ�ð�������head�в�Ӧ����
			//���headȥ��value+1��tailȥ��value
			if(headMap.containsKey(value+1)){
				node.next = headMap.get(value+1);
				tailMap.remove(value);
				headMap.remove(value+1);
			}
			//��ӡ����ͷ����Ƿ��м�����ӡ������
			if(headMap.containsKey(printNum)){
				printNode();
			}
		}
		public void printNode(){
			Node node = headMap.get(printNum);
			//��Ϊ�Ѿ���ӡ������head��Ҫȥ��
			headMap.remove(printNum--);
			while (node != null) {
				System.out.print(node.value + " ");
				node = node.next;
				printNum++;
			}
			System.out.println();
			//��ӡ��ɺ�ȥ��β���
			tailMap.remove(printNum);
			printNum++;
		}
		
		public static void main(String[] args) {
			// MessageBox only receive 1~N
			MessageBox box = new MessageBox();

			box.receive(2); // - 2
			box.receive(1); // 1 2 -> print, trigger is 1

			box.receive(4); // - 4
			box.receive(5); // - 4 5
			box.receive(7); // - 4 5 - 7
			box.receive(8); // - 4 5 - 7 8
			box.receive(6); // - 4 5 6 7 8
			box.receive(3); // 3 4 5 6 7 8 -> print, trigger is 3

			box.receive(9); // 9 -> print, trigger is 9

			box.receive(10); // 10 -> print, trigger is 10

			box.receive(12); // - 12
			box.receive(13); // - 12 13
			box.receive(11); // 11 12 13 -> print, trigger is 11

		}
	}

}
