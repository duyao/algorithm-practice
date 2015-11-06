package com.dy.niuke;

public class Niu5_2_1 {
	  public int[] getMinKNumsByHeap(int[] arr, int k) { 
	        if (k < 1 || k > arr.length) { 
	            return arr; 
	        } 
	        
	        //���ѣ�����Ϊk����Ϊֻ��Ҫ�ҳ�ǰk�������
	        int[] kHeap = new int[k]; 
	        for (int i = 0; i != k; i++) {
	        	//����в�������
	            heapInsert(kHeap, arr[i], i); 
	        } 
	        
	        //�ҳ�ǰk�������
	        for (int i = k; i != arr.length; i++) { 
	        	//kHeap[0]�ǵ�ǰ��k�������
	        	//��Ϊֻ��Ҫ��ǰk������֣���˱ȶѶ�������ֲ���Ҫ���ǣ���Ϊ��������k+1���������
	            if (arr[i] < kHeap[0]) { 
	            	//����ǰ�Ѷ���������λĿǰ��k�������
	                kHeap[0] = arr[i]; 
	                //�����ѣ���Ϊ���˶Ѷ���Ҫ����ѵĶ���
	                heapify(kHeap, 0, k); 
	            } 
	        } 
	        return kHeap; 
	    } 

	    public void heapInsert(int[] arr, int value, int index) { 
	        arr[index] = value; 
	        //��֤�Ƕѣ���ÿ�������ױȺ��Ӵ�
	        while (index != 0) { 
	            int parent = (index - 1) / 2;
	            //����С������������ѭ�����֤�����󶼿��Է��϶ѵĶ���
	            if (arr[parent] < arr[index]) { 
	                swap(arr, parent, index); 
	                index = parent; 
	            } else { 
	                break; 
	            } 
	        } 
	    } 

	    public void heapify(int[] arr, int index, int heapSize) { 
	    	//�ҵ����Һ���
	        int left = index * 2 + 1; 
	        int right = index * 2 + 2; 
	        //����Ѷ����
	        int largest = index; 
	        //���ֱȶѶ���ľͽ�����ѭ���ﱣ֤�ѵĶ���
	        while (left < heapSize) { 
	        	
	        	//2��if�ҵ����ף����Һ���������ֵ��Ȼ�󽻻�
	            if (arr[left] > arr[index]) { 
	                largest = left; 
	            } 
	            if (right < heapSize && arr[right] > arr[largest]) { 
	                largest = right; 
	            }
	            
	            //����
	            if (largest != index) { 
	                swap(arr, largest, index); 
	            } else { 
	                break; 
	            }
	            //ʹ��ǰλ��ָ�򽻻�����λ�ã��������
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
