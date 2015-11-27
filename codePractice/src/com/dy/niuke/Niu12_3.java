package com.dy.niuke;

import java.util.HashMap;

public class Niu12_3 {

	public static class Pool<K>{
		//Ϊ��Ѹ���ҵ�intger����size
		private HashMap<K, Integer> keyIndexMap;
		//Ϊ��Ѹ���ҵ�key
		private HashMap<Integer, K> indexKeyMap;
		int size;
		public Pool() {
			keyIndexMap = new HashMap<K, Integer>();
			indexKeyMap = new HashMap<Integer, K>();
			size = 0;
		}
		
		public void insert(K key){
			
			if(!this.keyIndexMap.containsKey(key)){
				//����map��Ҫά��
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size, key);
				//��Ӹ�������
				this.size++;
			}
		}
		
		public void delete(K key) {
			if(this.keyIndexMap.containsKey(key)){
				//�õ�ɾ����index
				int delIndex = keyIndexMap.get(key);
				//����������
				int lastIndex = --size;
				//�õ�����index
				K lastK = indexKeyMap.get(lastIndex);
				//����λ�ã�����ɾ�����һ�� 
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
