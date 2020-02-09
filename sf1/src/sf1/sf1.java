package sf1;

public class sf1 {
    public static void mergeSort(int[] a,int left,int right) {
		int[] b=new int[a.length];
		if(left<right) { 
			int mid=(left+right)/2;
			mergeSort(a, left, mid);//����벿������
			mergeSort(a, mid+1, right);//���Ұ벿����
			merge(a,b,left,mid,right);//�ϲ�
			for(int i=left;i<=right;i++) {
				a[i]=b[i];//���ź��������Ԫ�����¸�ֵ��a
			}
		}
		
	}
	/**
	 * ������a�е����ҵ�Ԫ�ذ��մ�С�洢��b��
	 * @param a���Ҷ����ź���
	 * @param b�յ�����
	 * @param left��˵�
	 * @param mid�е�
	 * @param right�Ҷ˵�
	 */
	public static void merge(int[] a,int[] b,int left,int mid,int right) {
		
		int i=left,j=mid+1,k=left;
		while(i<=mid&&j<=right) {
			if(a[i]<a[j]) {
				b[k++]=a[i++];
			}else {
				b[k++]=a[j++];
			}
		}
		//��벿��ʣ��Ԫ��
		if(i<=mid){
			for(int q=i;q<=mid;q++){
				b[k++]=a[q];
			}
		}else{
			//�Ұ벿��ʣ��Ԫ��
			for(int q=j;q<=right;q++){
				b[k++]=a[q];
			}
		}
		//System.out.println(Arrays.toString(b));����
	}

}
