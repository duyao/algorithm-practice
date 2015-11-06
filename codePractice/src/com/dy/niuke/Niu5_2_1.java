package com.dy.niuke;

public class Niu5_2_1 {
	  public int[] getMinKNumsByHeap(int[] arr, int k) { 
	        if (k < 1 || k > arr.length) { 
	            return arr; 
	        } 
	        
	        //建堆，长度为k，因为只需要找出前k大的数字
	        int[] kHeap = new int[k]; 
	        for (int i = 0; i != k; i++) {
	        	//向堆中插入数字
	            heapInsert(kHeap, arr[i], i); 
	        } 
	        
	        //找出前k大的数字
	        for (int i = k; i != arr.length; i++) { 
	        	//kHeap[0]是当前第k大的数字
	        	//因为只需要找前k大的数字，因此比堆顶大的数字不需要考虑，因为他至少是k+1大的数字了
	            if (arr[i] < kHeap[0]) { 
	            	//将当前堆顶换掉，换位目前第k大的数字
	                kHeap[0] = arr[i]; 
	                //调整堆，因为换了堆顶，要满足堆的定义
	                heapify(kHeap, 0, k); 
	            } 
	        } 
	        return kHeap; 
	    } 

	    public void heapInsert(int[] arr, int value, int index) { 
	        arr[index] = value; 
	        //保证是堆，即每个树父亲比孩子大
	        while (index != 0) { 
	            int parent = (index - 1) / 2;
	            //父亲小，作调整，在循环里，保证交换后都可以符合堆的定义
	            if (arr[parent] < arr[index]) { 
	                swap(arr, parent, index); 
	                index = parent; 
	            } else { 
	                break; 
	            } 
	        } 
	    } 

	    public void heapify(int[] arr, int index, int heapSize) { 
	    	//找到左右孩子
	        int left = index * 2 + 1; 
	        int right = index * 2 + 2; 
	        //假设堆顶最大
	        int largest = index; 
	        //发现比堆顶大的就交换，循环里保证堆的定义
	        while (left < heapSize) { 
	        	
	        	//2个if找到父亲，左右孩子中最大的值，然后交换
	            if (arr[left] > arr[index]) { 
	                largest = left; 
	            } 
	            if (right < heapSize && arr[right] > arr[largest]) { 
	                largest = right; 
	            }
	            
	            //交换
	            if (largest != index) { 
	                swap(arr, largest, index); 
	            } else { 
	                break; 
	            }
	            //使当前位置指向交换过的位置，继续检查
	            index = largest; 
	            left = index * 2 + 1; 
	            right = index * 2 + 2; 
	        } 
	    } 

	    public void swap(int[] arr, int index1, int index2) { 
	        int tmp = arr[index1]; 
	        arr[index1] = arr[index2]; 
	        arr[index2] = tmp; 
	    } 

}
