/*
 * @author Sheng Zhang
 * @date  09/25/2015
 * @Course CS570
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class QuickSorter extends RecursiveAction {

	int[] number;
	int start;
	int end;
	int threshold;
	
	public QuickSorter(int[] number, int start, int end,int threshold) {
		this.start = start;
		this.end = end;
		this.number = number;
		this.threshold = threshold;
	}

	@Override
	protected void compute() {
		if(end<start){
			return;
		}
		if(end-start<threshold){
			Arrays.sort(number,start,end);
			return;
		}
		
		int pivotIndex = start;
		int pivotValue=number[pivotIndex];
		
		int i=start;
		int j= end;
		
		while(i<j){
			while(i<j&&number[j]>=pivotValue){
				j--;
			}
			number[i]=number[j];
			
			while(i<j&&number[i]<pivotValue){
				i++;
			}
			number[j]=number[i];
		}
		number[i]=pivotValue;
		
		QuickSorter left = new QuickSorter(number,start,i-1,threshold);
		QuickSorter right = new QuickSorter(number,i+1,end,threshold);
		left.fork();
		right.compute();
		left.join();
	}
	
	public static void main(String[] args) {
		
			ArrayList<Integer> list= new ArrayList<Integer>();
			try{
				BufferedReader bf= new BufferedReader(new FileReader(args[0]));
				String line=null;
				try {
					while((line=bf.readLine())!=null){
						list.add(Integer.parseInt(line));
					}
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}catch(FileNotFoundException e){
				e.printStackTrace();
			}
			int[] array = new int[list.size()];
			for(int i=0;i<array.length;i++){
				array[i]= list.get(i);
			}
			int threshold = Integer.parseInt(args[1]);
			QuickSorter quickSorter = new QuickSorter(array, 0, array.length-1,threshold);
			ForkJoinPool f = new ForkJoinPool();
			f.invoke(quickSorter);
			for(int n:quickSorter.number)
				System.out.println(n);
	}

}
