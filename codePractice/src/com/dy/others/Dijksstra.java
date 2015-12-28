package com.dy.others;

import java.util.ArrayList;

public class Dijksstra {
	//�ڽӾ���
	public static int[] matrix(int[][] g, int s){
		int[] d = new int[g.length];
		//d��¼����㵽����������·��
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		//vis��¼�Ƿ���ʹ�
		boolean[] vis = new boolean[d.length];
		//��㵽�Լ��ľ�����0
		d[s] = 0;
		for(int i = 0; i < g.length; i++){
			//��¼d�о�����С�ĵ�
			int u = -1;
			//��С�ľ���ֵ
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < g.length; j++){
				if(!vis[j] && d[j] < min){
					u = j;
					min = d[j];
				}
			}
			if(u == -1) return d;
			//ѡ������С����ı��
			vis[u] = true;
			//���¾���
			for(int v = 0; v < g.length; v++){
				if(!vis[v] && g[u][v]!= -1 && d[v] > d[u] + g[u][v]){
					d[v] = d[u] + g[u][v];
				}
			}
			
			
		}
		return d;
	}
	//�ڽӱ�Ľṹ
	public class edge{
		int v;
		int dis;
	}
	//�ڽӱ�
	public static int[] vertor(ArrayList<edge>[] adj, int s){
		int[] d = new int[adj.length];
		//d��¼����㵽����������·��
		for (int i = 0; i < d.length; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		//vis��¼�Ƿ���ʹ�
		boolean[] vis = new boolean[d.length];
		//��㵽�Լ��ľ�����0
		d[s] = 0;
		for(int i = 0; i < adj.length; i++){
			//��¼d�о�����С�ĵ�
			int u = -1;
			//��С�ľ���ֵ
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < adj.length; j++){
				if(!vis[j] && d[j] < min){
					u = j;
					min = d[j];
				}
			}
			if(u == -1) return d;
			//ѡ������С����ı��
			vis[u] = true;
			//���¾���
			for(int v= 0; v < adj[u].size(); v++){
				int j = adj[u].get(v).v;
				if(!vis[j] && adj[u].get(v).dis != -1 && d[j] > d[u] + adj[u].get(v).dis){
					d[j] = d[u] + adj[u].get(v).dis;
				}
			}
			
			
		}
		return d;
		
	}
	public static void main(String[] args) {
		int[][] map1 = { 
                { 0,  7,  9,  -1,  -1, 14 }, 
                { 7,  0,  10, 15, -1, -1 },
                { 9,  10, 0,  11, -1, 2 }, 
                { -1,  15, 11, 0,  6, -1 },
                { -1,  -1,  -1,  6,  0, 9 }, 
                { 14, -1,  2,  -1,  9, 0 } };
		int[] d1 = Dijksstra.matrix(map1, 0);
		for (int i = 0; i < d1.length; i++) {
			System.out.print(d1[i]+" ");
		}
		System.out.println();
		
	}

}
