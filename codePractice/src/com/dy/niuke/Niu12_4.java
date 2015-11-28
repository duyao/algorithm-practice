package com.dy.niuke;

import java.util.HashMap;

public class Niu12_4 {
	//节点信息
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
		//即将打印的数字
		int printNum;
		public MessageBox(){
			//表示未打印集合中的头结点
			headMap = new HashMap<Integer, Node>();
			//表示未打印集合中的尾结点
			tailMap = new HashMap<Integer, Node>();
			printNum = 1;
		}
		
		public void receive(int value) {
			if(value < 1){
				return;
			}
			//把node看做单独的节点
			Node node = new Node(value);
			headMap.put(value, node);
			tailMap.put(value, node);
			//由于value-1是头，因此hand中应该包含，而tail中不应该包含
			//因此tail去掉value-1,head去掉value
			if(tailMap.containsKey(value-1)){
				tailMap.get(value-1).next = node;
				headMap.remove(value);
				tailMap.remove(value-1);
			}
			//value+1是尾，tail中应该包含，而head中不应该有
			//因此head去掉value+1，tail去掉value
			if(headMap.containsKey(value+1)){
				node.next = headMap.get(value+1);
				tailMap.remove(value);
				headMap.remove(value+1);
			}
			//打印，看头结点是否有即将打印的数字
			if(headMap.containsKey(printNum)){
				printNode();
			}
		}
		public void printNode(){
			Node node = headMap.get(printNum);
			//因为已经打印，所以head中要去掉
			headMap.remove(printNum--);
			while (node != null) {
				System.out.print(node.value + " ");
				node = node.next;
				printNum++;
			}
			System.out.println();
			//打印完成后去掉尾结点
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
