package com.dy.niuke;

import java.util.HashMap;

public class Niu12_3 {

	public static class Pool<K>{
		//为了迅速找到intger，即size
		private HashMap<K, Integer> keyIndexMap;
		//为了迅速找到key
		private HashMap<Integer, K> indexKeyMap;
		int size;
		public Pool() {
			keyIndexMap = new HashMap<K, Integer>();
			indexKeyMap = new HashMap<Integer, K>();
			size = 0;
		}
		
		public void insert(K key){
			
			if(!this.keyIndexMap.containsKey(key)){
				//两个map都要维护
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size, key);
				//添加个数增加
				this.size++;
			}
		}
		
		public void delete(K key) {
			if(this.keyIndexMap.containsKey(key)){
				//得到删除的index
				int delIndex = keyIndexMap.get(key);
				//将个数减少
				int lastIndex = --size;
				//得到最后的index
				K lastK = indexKeyMap.get(lastIndex);
				//交换位置，并且删除最后一个 
				this.keyIndexMap.remove(key);
				this.keyIndexMap.put(lastK, delIndex);
				this.indexKeyMap.remove(lastIndex);
				this.indexKeyMap.put(lastIndex, lastK);
			}
		}
		
		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int rand = (int) (Math.random() * this.size);
			return this.indexKeyMap.get(rand);
		}
		
		public static void main(String[] args) {
			Pool<Integer> pool = new Pool<Integer>();
			pool.insert(1);
			pool.insert(2);
			pool.insert(3);
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			pool.delete(3);
			System.out.println("=========delete===========");
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());
			System.out.println(pool.getRandom());

		}

	}
	
	
	
}
