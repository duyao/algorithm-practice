package com.dy.niuke;

import java.util.HashMap;

public class Niu13_5 {
	
	// �����еĽ��
	public static class Node<V> {
		//˫������еĽڵ�Ҫ��ǰ��ָ��
		private Node<V> prior;
		private Node<V> next;
		private V value;

		public Node(V value) {
			this.value = value;
			this.next = null;
			this.prior = null;
		}
	}

	// ˫�˶���
	public static class NodeDoubleLinkedList<V> {
		//ͷ�������ʹ�õ�
		private Node<V> head;
		//β�����ʹ�õ�
		private Node<V> tail;

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		//�½ڵ���ӵ�β��
		public void addToTail(Node<V> newNode) {
			if (newNode == null) {
				return;
			}
			//����û�нڵ�
			if (head == null) {
				head = newNode;
				tail = newNode;
				head.next = null;
				head.prior = null;
				tail.prior = null;
				tail.next = null;
			} else {
				//�нڵ�
				newNode.prior = tail;
				tail.next = newNode;
				tail = newNode;
				tail.next = null;
			}
		}

		//�����ڲ���������Ҫ��ͷ�ڵ�ȥ��
		public Node<V> removeHead() {
			//û��ͷ���
			if(head == null){
				return null;
			}
			//��ȡ��ͷ���
			Node<V> res = head;
			//ֻ��һ���ڵ�
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head.next.prior = null;
				head = head.next;
			}
			//���صĽڵ�Ҫ����ɾ���ֻ��ֵ��ָ��ȫ�����
			res.next = null;
			res.prior = null;
			return res;
		}

		//���ڸոս��в����Ľڵ�Ҫ�ƶ�����β
		public void moveToTail(Node<V> curNode) {
			//�ƶ�ͷ���
			if (head == curNode) {
				curNode = this.removeHead();
				addToTail(curNode);
			}else if(curNode != tail){
				//��������ڶ�β���ò���
				if (head != tail) {
					// ɾ���ڵ�
					curNode.prior.next = curNode.next;
					curNode.next.prior = curNode.prior;
					// �Ƶ�β��
					addToTail(curNode);
				}
			}
		}

	}

	public static class MyCache<K, V> {
		//2��hashMap��Ӧkey��value�����ٲ���
		private HashMap<K, Node<V>> keyNodeMap;
		private HashMap<Node<V>, K> nodeKeyMap;
		//˫�����
		private NodeDoubleLinkedList<V> nDLList;
		//���ڴ�С
		private int windowSize;
		//��ʼ�����ڴ�С
		public MyCache(int capacity) {
			this.windowSize = capacity;
			this.keyNodeMap = new HashMap<K, Node<V>>();
			this.nodeKeyMap = new HashMap<Node<V>, K>();
			this.nDLList = new NodeDoubleLinkedList<V>();
		}
		//����key�õ�value
		public V get(K k) {
			if (keyNodeMap.containsKey(k)) {
				Node<V> cur = keyNodeMap.get(k);
				//���²����Ľڵ��Ƶ���ͷ
				nDLList.moveToTail(cur);
				return cur.value;
			} else {
				return null;
			}
		}

		public void set(K k,V value){
			if(keyNodeMap.containsKey(k)){
				//���½ڵ�
				Node<V> cur = keyNodeMap.get(k);
				cur.value = value;
				//�������Ľڵ��Ƶ���ͷ
				nDLList.moveToTail(cur);
			}else{
				//�����ʱ���ڴﵽ���ֵ��������û���õļ�ͷ���ȥ��
				if(keyNodeMap.size() == windowSize){
					removeMostUnusedCache();
				}
				//�����ڵ�
				Node<V> newNode= new Node<V>(value);
				keyNodeMap.put(k, newNode);
				nodeKeyMap.put(newNode, k);
				//�Ƶ���β
				nDLList.addToTail(newNode);
				
			}
		}

		public void removeMostUnusedCache() {
			//ɾ��ͷ���
			Node<V> rNode = nDLList.removeHead();
			K rKey = nodeKeyMap.get(rNode);
			//hashMap��ɾ���������
			nodeKeyMap.remove(rNode);
			keyNodeMap.remove(rKey);
		}

	}
	
	
	public static void main(String[] args) {
		MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
		
		testCache.set("A", 1);
		testCache.set("B", 2);
		testCache.set("C", 3);
		System.out.println(testCache.get("B"));
		System.out.println(testCache.get("A"));
		testCache.set("D", 4);
		System.out.println(testCache.get("D"));
		System.out.println(testCache.get("C"));
		
		//2 1 2 1 2 3 4
		//4����ȱҳ��1���û�
		MyCache<Integer, Integer> cache = new MyCache<Integer, Integer>(3);
		cache.set(2, 2);
		cache.set(1, 1);
		cache.set(2, 2);
		cache.set(1, 1);
		cache.set(2, 2);
		cache.set(3, 3);
		cache.set(2, 2);
		cache.set(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));




	}

}
