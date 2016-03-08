package paralleComputing;

public class ComputePI extends Thread {

	int start;
	int stop;
	double width;
	double sum;
	double result;

	public ComputePI(int start, int stop, double width) {
		this.start = start;
		this.stop = stop;
		this.width = width;
	}

	public void run() {
		double x;
		sum = 0.0;
		for (int i = start; i < stop; i++) {
			x = (i + 0.5) * width;
			sum = sum + 4.0 / (double) (1 + x * x);
		}
		result = sum * width;
	}

	public double result() {
		return result;
	}
	
	public static void main(String[] args){
		int numberThreads = 10;
		int numberIntervals = 1000;
		int localIntervals = numberIntervals/ numberThreads;
		double width = 1.0/numberIntervals;
		double result = 0.0;
		
		ComputePI results[];
		results = new ComputePI[numberThreads];
		
		for(int i= 0; i<numberThreads; i++){
			int start = localIntervals*i;
			int stop = start+localIntervals;
			results[i]= new ComputePI(start, stop, width);
		}
		
		for(int i=0;i<numberThreads;i++){
			results[i].start();
		}
		
		for(int i=0;i<numberThreads;i++){
			try {
				results[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int i=0;i<numberThreads;i++){
			result= result + results[i].result;
		}
		
		System.out.println("Pi="+result);
	}
}
