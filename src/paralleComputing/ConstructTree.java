package paralleComputing;

public class ConstructTree {

	public static void main(String[] args) {
		int[] array1;
		int[] array2;
		int[] array={3,1,7,0,4,1,6,5,7};
		int prefixSum[];
		int n = array.length;
		array1= new int[2*n-1];
		array2=new int[2*n-1];
		prefixSum=new int[n];
		
		int depth= (int) (Math.ceil(Math.log(n)/Math.log(2)));

		int botNum = (int) (2*n - Math.pow(2, depth));
		int a= (int) Math.pow(2, depth)-1;
		
		for(int i=0;i<botNum;i++){
			array1[a+i]=array[i];
		}
		
		for(int i=botNum;i<n;i++){
			array1[n-1+i-botNum]=array[i];
		}
		
		for(int d=depth-1;d>=0;d--){
			int index = (int)(Math.pow(2, d)-1);
			for(int i=0;i<=index;i++){
				if(i+index<n-1){
					array1[i+index] = array1[2*(i+index)+1]+array1[2*(i+index)+2];
				}
			}
		}
		
		for(int e:array1){
			System.out.println(e);
		}
		
		//-------------------------------------------------------
		System.out.println("-------------------------------------------------------");
		array1[0]=0;
		for(int d=0;d<depth;d++){
			int index = (int)(Math.pow(2, d)-1);
			for(int i=0;i<=index;i++){
				if(i+index<n-1){
					int t=array1[2*(i+index)+1];
					array2[2*(i+index)+1] = array2[i+index];
					array2[2*(i+index)+2]=t+array2[i+index];
				}
			}
		}
		

		for(int e:array2){
			System.out.println(e);
		}
		for(int i=botNum;i<n;i++){
			prefixSum[i]=array2[n-1+i-botNum];
		}
		for(int i=0;i<botNum;i++){
			prefixSum[i] = array2[a+i];
		}
		for(int i=0;i<n;i++){
			prefixSum[i]=prefixSum[i]+array[i];
		}
		System.out.println("--------------------------------------------");
		for(int e:prefixSum){
			System.out.println(e);
		}
	}
	
}
