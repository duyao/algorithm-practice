package com.dy.others;

/**
 * @author dy
 * ��С������
 * ������Dij
 * ����ʱ�Ǹ��µ�����ϵľ��룬������ĳ����ľ���
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
		//ͼ�еĵ���
		int v;
		//ͼ�еı���
		int e;
		//�߼�
		Edge edge[];
		//���췽��
		public Graph(int v, int e){
			this.v = v;
			this.e = e;
			for(int i = 0; i < e; i++){
				this.edge[i] = new Edge();
			}
		}
		
	}
	

}
