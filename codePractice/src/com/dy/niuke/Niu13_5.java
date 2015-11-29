package com.dy.niuke;

import java.util.HashMap;

public class Niu13_5 {
	
	// 队列中的结点
	public static class Node<V> {
		//双向队列中的节点要有前后指针
		private Node<V> prior;
		private Node<V> next;
		private V value;

		public Node(V value) {
			this.value = value;
			this.next = null;
			this.prior = null;
		}
	}

	// 双端队列
	public static class NodeDoubleLinkedList<V> {
		//头是最不经常使用的
		private Node<V> head;
		//尾是最经常使用的
		private Node<V> tail;

		public NodeDoubleLinkedList() {
			head = null;
			tail = null;
		}

		//新节点添加到尾部
		public void addToTail(Node<V> newNode) {
			if (newNode == null) {
				return;
			}
			//队列没有节点
			if (head == null) {
				head = newNode;
				tail = newNode;
				head.next = null;
				head.prior = null;
				tail.prior = null;
				tail.next = null;
			} else {
				//有节点
				newNode.prior = tail;
				tail.next = newNode;
				tail = newNode;
				tail.next = null;
			}
		}

		//当窗口不够，就需要将头节点去掉
		public Node<V> removeHead() {
			//没有头结点
			if(head == null){
				return null;
			}
			//获取到头结点
			Node<V> res = head;
			//只有一个节点
			if (head == tail) {
				head = null;
				tail = null;
			} else {
				head.next.prior = null;
				head = head.next;
			}
			//返回的节点要清理干净，只有值，指针全部清空
			res.next = null;
			res.prior = null;
			return res;
		}

		//对于刚刚进行操作的节点要移动到队尾
		public void moveToTail(Node<V> curNode) {
			//移动头结点
			if (head == curNode) {
				curNode = this.removeHead();
				addToTail(curNode);
			}else if(curNode != tail){
				//如果本身在队尾不用操作
				if (head != tail) {
					// 删除节点
					curNode.prior.next = curNode.next;
					curNode.next.prior = curNode.prior;
					// 移到尾部
					addToTail(curNode);
				}
			}
		}

	}

	public static class MyCache<K, V> {
		//2个hashMap对应key和value，加速查找
		private HashMap<K, Node<V>> keyNodeMap;
		private HashMap<Node<V>, K> nodeKeyMap;
		//双向队列
		private NodeDoubleLinkedList<V> nDLList;
		//窗口大小
		private int windowSize;
		//初始化窗口大小
		public MyCache(int capacity) {
			this.windowSize = capacity;
			this.keyNodeMap = new HashMap<K, Node<V>>();
			this.nodeKeyMap = new HashMap<Node<V>, K>();
			this.nDLList = new NodeDoubleLinkedList<V>();
		}
		//根据key得到value
		public V get(K k) {
			if (keyNodeMap.containsKey(k)) {
				Node<V> cur = keyNodeMap.get(k);
				//最新操作的节点移到队头
				nDLList.moveToTail(cur);
				return cur.value;
			} else {
				return null;
			}
		}

		public void set(K k,V value){
			if(keyNodeMap.containsKey(k)){
				//更新节点
				Node<V> cur = keyNodeMap.get(k);
				cur.value = value;
				//操作过的节点移到队头
				nDLList.moveToTail(cur);
			}else{
				//如果此时窗口达到最大值，则把最久没有用的即头结点去掉
				if(keyNodeMap.size() == windowSize){
					removeMostUnusedCache();
				}
				//新增节点
				Node<V> newNode= new Node<V>(value);
				keyNodeMap.put(k, newNode);
				nodeKeyMap.put(newNode, k);
				//移到队尾
				nDLList.addToTail(newNode);
				
			}
		}

		public void removeMostUnusedCache() {
			//删除头结点
			Node<V> rNode = nDLList.removeHead();
			K rKey = nodeKeyMap.get(rNode);
			//hashMap中删除相关内容
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
		//4出现缺页，1被置换
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
