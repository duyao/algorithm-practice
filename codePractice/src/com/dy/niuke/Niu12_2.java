package com.dy.niuke;

import java.util.HashMap;

public class Niu12_2 {

	//valueֵ�Ǵ���ʱ�����
	public static class MyValue<V>{
		private V value;
		private long time;
		public MyValue(V value, long time){
			this.time = time;
			this.value = value;
		}
		public V getValue(){
			return this.value;
		}
		public long getTime(){
			return this.time;
		}
	}
	
	
	public static class MyHashMap<K, V>{
		//hashMap��value�Ǵ���ʱ�����value
		private HashMap<K, MyValue<V>> baseMap;
		private long time;
		//setAll��Ϊ����������
		private MyValue<V> setAll;
		//��ʼ��
		public MyHashMap(){
			this.baseMap = new HashMap<K, MyValue<V>>();
			this.time = 0;
			this.setAll = new MyValue<V>(null, -1);
		}
		
		//��ͨ��containsKey
		public boolean containsKey(K key){
			return this.baseMap.containsKey(key);
		}
		
		//���ʱҪ���ʱ���
		public void put(K key, V value) {
			this.baseMap.put(key, new MyValue<V>(value, this.time++));
		}
		
		//��setAll�������ó�Ϊһ�����򣬶����Ǹı����е�ֵ
		public void setAll(V value){
			this.setAll = new MyValue<V>(value, time++);
		}
		
		//ȡֵʱ��Ҫ����ʱ������ж�ȡ��setAll����myValue
		public V get(K key){
			if(this.containsKey(key)){
				if(this.baseMap.get(key).time > this.setAll.time){
					return this.baseMap.get(key).getValue();
				}else{
					return this.setAll.getValue();
				}
			}else{
				return null;
			}
			
		}
		
		public static void main(String[] args) {
			MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
			test.put("Tom", 1);
			test.put("James", 2);
			System.out.println(test.containsKey("Tom"));
			System.out.println(test.get("Tom"));
			System.out.println(test.containsKey("James"));
			System.out.println(test.get("James"));
			test.setAll(3);
			test.put("Rose", 4);
			System.out.println(test.get("Tom"));
			System.out.println(test.get("James"));
			System.out.println(test.get("Rose"));
			test.setAll(5);
			System.out.println(test.get("Tom"));
			System.out.println(test.get("James"));
			System.out.println(test.get("Rose"));

		}
		
	}
	
	
	
	
}
