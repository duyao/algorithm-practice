package com.dy.others;

public class BinarySearch {
	// ��������x�Ƿ�������������,���ظ�
	public static int f1(int[] a, int x) {
		int left = 0;
		int right = a.length - 1;
		int mid = 0;
		// ������[0,n-1]
		while (left <= right) {
			mid = (left + right) / 2;
			if (a[mid] == x) {
				return mid;
			} else if (a[mid] > x) {
				// ������[left,mid-1]��
				right = mid - 1;
			} else {
				// ������[mid+1,right]��
				left = mid + 1;
			}
		}
		// �Ҳ���
		return -1;
	}

	//���еĵ�һ��x��λ��
	public static int f2(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		// ������[0,n)
		// ��֧�г�����l=mid ����r=mid��ѭ������Ҫд��left < right������д��left <= right
		//�������left=right-1������ѭ��
		while (left < right) {
			mid = (left + right) / 2;
			if (a[mid] >= x) {
				// ������[left,mid)��
				//��Ϊ������ҿ����䣬����right=mid
				right = mid;
			} else {
				// ������[mid+1,right)��
				left = mid + 1;
			}
		}
		//������
		return left;
	}

	//��һ������x��λ��R
	public static int f3(int[] a, int x) {
		int left = 0;
		int right = a.length;
		int mid = 0;
		// ������[0,n)
		// ��֧�г�����l=mid ����r=mid��ѭ������Ҫд��left < right������д��left <= right
		//�������left=right-1������ѭ��
		while (left < right) {
			mid = (left + right) / 2;
			//��һ������x��Ԫ�أ�û�еȺ�
			if (a[mid] > x) {
				// ������[left,mid)��
				right = mid;
			} else {
				// ������[mid+1,right)��
				left = mid + 1;
			}
		}
		// �Ҳ���
		return left;
	}

	public static void main(String[] args) {
		int[] a1 ={0,1,1,1,2,2,2,3,3,3};
		System.out.println(f1(a1,0));
		System.out.println(f2(a1,2));
		System.out.println(f3(a1,2));
		System.out.println(f2(a1,3));
		System.out.println(f3(a1,3));
	}
}
