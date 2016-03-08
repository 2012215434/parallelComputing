
package paralleComputing;

/**
 * @author Sheng Zhang
 * @subject CS470/570 A1
 */
import java.util.concurrent.ThreadLocalRandom;

public class P_Pi extends Thread {

	// the number of times the thread generates a random number
	int iterations;
	// the number of times the random number is in the circle each threads
	int count;

	public P_Pi(int iter) {
		this.iterations = iter;
	}

	public void run() {
		for (int i = 0; i < iterations; i++) {
			double x = ThreadLocalRandom.current().nextDouble(1.0);
			double y = ThreadLocalRandom.current().nextDouble(1.0);
			if (x * x + y * y <= 1)
				count++;
		}

	}

	public int getCounts() {
		return count;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numIterations = Integer.parseInt(args[0]);
		int numThread = Integer.parseInt(args[1]);
		int totalCounts = 0;
		double result = 0.0;

		P_Pi[] counts = new P_Pi[numThread];

		for (int i = 0; i < numThread; i++) {
			counts[i] = new P_Pi(numIterations);
		}

		for (int i = 0; i < numThread; i++) {
			counts[i].start();
		}

		for (int i = 0; i < numThread; i++) {
			try {
				counts[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < numThread; i++) {
			totalCounts = totalCounts + counts[i].getCounts();
		}
		result = (4.0 * totalCounts) / (numIterations * numThread);
		System.out.println("Pi is approximately: " + result);
	}

}
