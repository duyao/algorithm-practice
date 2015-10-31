package com.dy.niuke;



public class Niu2_5 {
	
	public class Node {
		public int value;
		public Node left;
		public Node right;
		public Node parent;
		public Node(int data) {
			this.value = data;
		}
	}
	
	
	public int find(Node node){
		//叶子节点
		if(node.left == null && node.right == null ){
			//找parent到parent的右孩子不是自己或者不存在
			return parent(node);
		}else{
			//非叶子结点
			if(node.left !=null){
				//右孩子存在，返回右孩子的最左
				return left(node.right);
			}else{
				//右孩子 不存在，返回parent
				return node.parent.value;
			}
			
		}
	}
	
	public int left(Node node){
		//找左孩子
		while(node.left != null){
			left(node.left);
		}
		return node.value;
	}
	
	public int parent(Node node){
		if(node.parent != null){
			if(node.parent.right == null){
				return node.parent.value;
			}else{
				//应该是哪一个啊？
				//while(node.parent.right == node){
				if(node.parent.right == node){
					parent(node.parent);
				}
				return node.parent.value;
			}
			
		}else{
			return -1;
		}
	}
	

}
