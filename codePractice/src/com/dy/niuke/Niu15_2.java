package com.dy.niuke;

public class Niu15_2 {

	public static int maxLength(int[] arr, int k) {
		//��¼��ǰλ�ó��ֵ����ͣ���һ����������
		int[] h = new int[arr.length + 1];
		int sum = 0;
		//�������0�ļ�¼
		h[0] = sum;
		for (int i = 0; i != arr.length; i++) {
			sum += arr[i];
			h[i + 1] = Math.max(sum, h[i]);
		}
		sum = 0;
		//���
		int res = 0;
		//�����������߽�
		int pre = 0;
		//���ȣ��ұ߽�-��߽�+1
		int len = 0;
		for (int i = 0; i != arr.length; i++) {
			//��ǰ���еĺ�
			sum += arr[i];
			//k+x=sum
			//��С�ڵ���k�����䣬��ô��Ҫ�ҵ����ڵ���x��(sum-k)��ֵ
			//�������һ������x��λ��
			pre = getIndex(h, sum - k);
			//�����Ҳ���
			len = pre == -1 ? 0 : i - pre + 1;
			res = Math.max(res, len);
		}
		return res;
	}

	// ��һ������x��λ��R
	public static int getIndex(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		//�п��ܳ����Ҳ��������
		int res = -1;
		// ������[0,n)
		// ��֧�г�����l=mid ����r=mid��ѭ������Ҫд��left < right������д��left <= right
		// �������left=right-1������ѭ��
		while (left < right) {
			mid = (left + right) / 2;
			// ��һ������x��Ԫ�أ�û�еȺ�
			if (a[mid] > x) {
				//�Ҳ����������x���κ����������������ڶ����������
				//�������Ҳ���������ǲ������
				//��res����¼������ҵ�������ˣ����ı�ֵ������-1
				res = mid;
				// ������[left,mid)��
				right = mid;
			} else {
				// ������[mid+1,right)��
				left = mid + 1;
			}
		}
		// �Ҳ���
		return res;
	}

	// for test
	public static int[] generateRandomArray(int len, int maxValue) {
		int[] res = new int[len];
		for (int i = 0; i != res.length; i++) {
			res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
		}
		return res;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		 int[] arr = { 3, -2, -4, 0, 6 };
		 int k = -2;
		 System.out.println(maxLength(arr, k));

//		int[] a1 = { 0, 1, 1, 1, 2, 2, 3, 3, 3, 4 };
//		System.out.println(getIndex(a1, 10));
//		System.out.println(getIndex1(a1, 4));
	}

}
