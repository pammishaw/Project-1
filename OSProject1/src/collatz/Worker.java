/**
 * This class implements Runnable to create a thread. Each thread uses a shared Counter object to retrieve the next value, 
 * computes the Collatz stopping time for the number, and updates a shared histogram array. The shared array is synchronized
 * using a ReentrantLock to ensure safe updates. Computed stopping times that are greater than the histogram length are ignored.
 *
 * @author Pamela Mishaw
 * @date 06/12/2025
 * @info Course COP5518
 */

package collatz;

import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable{
	private Counter counter;
	private int[] histogram;
	private ReentrantLock lock;
	
	/**
     * Constructs a Worker instance.
     *
     * @param counter   the shared Counter used to fetch the next number in the range
     * @param histogram the shared histogram array to store stopping time frequencies
     * @param lock      the ReentrantLock used to synchronize access to the histogram
     */
	public Worker(Counter counter, int[] histogram, ReentrantLock lock) {
		this.counter = counter;
		this.histogram = histogram;
		this.lock = lock;
	}
	
	/**
     * Initiates the thread to repeatedly retrieve a number from the counter,
     * compute its Collatz stopping time, and update the histogram.
     * Terminates when there are no more numbers to process.
     */
	@Override
	public void run() {
		
		while(true) {
			int value = counter.getNext();
			if(value == -1) {
				break;
			}
			int stopTime = Collatz.computeCollatzStoppingTime(value);
			
			if (stopTime < histogram.length) {
				lock.lock();
				try {
				histogram[stopTime]++;
				}
				finally {
					lock.unlock();
				}
			}
		}	
	}
}
