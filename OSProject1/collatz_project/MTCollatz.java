/**
 * This program computes the Collatz stopping times for numbers in the range 1 to N where N is provided by the first command line argument. 
 * The second command line argument is the desired number of threads to compute, in parallel, the stopping times. 
 * 
 * The program accepts the following command-line arguments:
 *   1. numRange (int): the maximum number in the range [1, numRange] to compute stopping times for.
 *   2. numWorkers (int): the number of worker threads to use for the computation.
 *   3. Optional: "-nolock" flag to disable locking.
 * 
 * Each thread uses a shared Counter (that is initialized with N) to access the next number to compute a Collatz stopping time.
 * Each thread increments the corresponding entry for each stopping time count in an array to construct an array of the frequency of each time. 
 * Access to the array is synchronized using a Reentrant Lock. 
 * 
 * Once the threads are complete, the histogram is printed to standard output.
 * The parameters and execution time in milliseconds are printed to standard error.
 * 
 * @author Pamela Mishaw, Zafeerah Hamid
 * @date 06/12/2025
 * @info Course COP5518
 */

//package collatz;
import java.lang.Thread;
import java.util.concurrent.locks.ReentrantLock;
import java.time.Instant;
import java.time.Duration;

public class MTCollatz {

	public static void main(String[] args) {
		if (args.length < 2){ 
         	   System.err.println("Usage: java main <numRange> <numWorkers> [-nolock]");
          	   return;
        }

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
		
		for(int i = 1; i < histogram.length; i++) {
			if(histogram[i] > 0) {
			System.out.println(i + " " + histogram[i]);
			}
		}
		
		System.err.printf("%d,%d,%.9f%n", numRange, numWorkers, (double)timeLapse.toMillis());

	}

}
