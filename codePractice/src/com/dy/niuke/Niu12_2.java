package com.dy.niuke;

import java.util.HashMap;

public class Niu12_2 {

	//value值是带有时间戳的
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
		//hashMap的value是带有时间戳的value
		private HashMap<K, MyValue<V>> baseMap;
		private long time;
		//setAll变为单独的区域
		private MyValue<V> setAll;
		//初始化
		public MyHashMap(){
			this.baseMap = new HashMap<K, MyValue<V>>();
			this.time = 0;
			this.setAll = new MyValue<V>(null, -1);
		}
		
		//普通的containsKey
		public boolean containsKey(K key){
			return this.baseMap.containsKey(key);
		}
		
		//添加时要添加时间戳
		public void put(K key, V value) {
			this.baseMap.put(key, new MyValue<V>(value, this.time++));
		}
		
		//将setAll单独设置成为一个区域，而不是改变所有的值
		public void setAll(V value){
			this.setAll = new MyValue<V>(value, time++);
		}
		
		//取值时，要根据时间戳来判断取出setAll还是myValue
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
