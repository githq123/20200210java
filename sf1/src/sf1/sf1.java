package sf1;

public class sf1 {
    public static void mergeSort(int[] a,int left,int right) {
		int[] b=new int[a.length];
		if(left<right) { 
			int mid=(left+right)/2;
			mergeSort(a, left, mid);//对左半部分排序
			mergeSort(a, mid+1, right);//对右半部排序
			merge(a,b,left,mid,right);//合并
			for(int i=left;i<=right;i++) {
				a[i]=b[i];//将排好序的数组元素重新赋值给a
			}
		}
		
	}
	/**
	 * 将数组a中的左右的元素按照大小存储到b中
	 * @param a左右都已排好序
	 * @param b空的数组
	 * @param left左端点
	 * @param mid中点
	 * @param right右端点
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
		//左半部还剩余元素
		if(i<=mid){
			for(int q=i;q<=mid;q++){
				b[k++]=a[q];
			}
		}else{
			//右半部还剩余元素
			for(int q=j;q<=right;q++){
				b[k++]=a[q];
			}
		}
		//System.out.println(Arrays.toString(b));测试
	}

}
