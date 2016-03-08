package paralleComputing;

public class PI {
	public static void main(String[] args) {
		long steps=100000;
		double pi,sum=0.0,width,x;
		width=1.0/(double)steps;
		for(int i=0;i<steps;i++){
			x = i*width;
			sum = sum+ 4.0/(1+x*x)*width;
		}
		pi= sum;
		System.out.println(pi);
	}
}
