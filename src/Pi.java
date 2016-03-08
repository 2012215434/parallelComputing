public class Pi {
	int iterations;

	public Pi(int iter) {
		iterations = iter;
	}

	public double calculate() {
		int count = 0;
		for (int i = 0; i < iterations; i++) {
			double x = Math.random();
			double y = Math.random();
			if (x * x + y * y <= 1)
				count++;
		}
		return (4.0 * count) / iterations;
	}

	public static void main(String args[]) {
		int iter = new Integer(args[0]);
		Pi p = new Pi(iter);
		System.out.println("Pi is approximately " + p.calculate());
	}
}