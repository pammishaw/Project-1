package collatz;
import java.lang.Thread;
import java.util.concurrent.locks.ReentrantLock;
import java.time.Instant;
import java.time.Duration;

public class Main {

	public static void main(String[] args) {
		int numRange = Integer.parseInt(args[0]);
		int numWorkers = Integer.parseInt(args[1]);
		
		Counter counter = new Counter(numRange);
		int[] histogram = new int[2000];
		ReentrantLock lock = new ReentrantLock();
		
		Worker[] workers = new Worker[numWorkers];
		Thread[] threads = new Thread[numWorkers];
		
		Instant beginning = Instant.now();
		
		for(int i = 0; i < numWorkers; i++) {
			workers[i] = new Worker(counter, histogram, lock);
			threads[i] = new Thread(workers[i]);
			threads[i].start();
		}
		
		for(int i = 0; i < numWorkers; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Instant end = Instant.now();
		
		Duration timeLapse = Duration.between(beginning, end);
		
		// Display the histogram
		
		for(int i = 1; i < histogram.length; i++) {
			if(histogram[i] > 0) {
			System.out.println(i + " " + histogram[i]);
			}
		}
		
		System.err.printf("%d,%d,%.9f%n", numRange, numWorkers, (double)timeLapse.toMillis());

	}

}
