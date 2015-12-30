package com.dy.others;

/**
 * @author dy
 * 最小生成树
 * 类似于Dij
 * 更新时是更新到该组合的距离，而不是某个点的距离
 */
public class Prim {
	class Edge implements Comparable<Edge>{
		int src, dest, weight;
		@Override
		public int compareTo(Edge o) {
			//asc
			return this.weight - o.weight;
		}
		
	}
	class Graph{
		//图中的点数
		int v;
		//图中的边数
		int e;
		//边集
		Edge edge[];
		//构造方法
		public Graph(int v, int e){
			this.v = v;
			this.e = e;
			for(int i = 0; i < e; i++){
				this.edge[i] = new Edge();
			}
		}
		
	}
	

}
