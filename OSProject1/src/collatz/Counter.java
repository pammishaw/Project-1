/**
 * This class provides synchronized access to a shared counter value used to distribute work across multiple threads. 
 * Each call to getNext() returns the next integer in the sequence starting from 1 up to a specified maximum.
 *
 * A ReentrantLock is used to ensure safe access to the counter, allowing multiple threads to retrieve values without overlap.
 * 
 * Returns -1 when all values in the range have been exhausted.
 * 
 * @author Pamela Mishaw
 * @date 06/12/2025
 * @info Course COP5518
 */

package collatz;

import java.util.concurrent.locks.ReentrantLock;

public class Counter{
	private int count = 1;
	private int max;
	private ReentrantLock lock = new ReentrantLock();
	
	/**
     * Constructs a Counter with a specified maximum value.
     *
     * @param max the maximum value the counter should return
     */
	public Counter (int max) {
		this.max = max;
	}

	/**
     * Safely returns the next integer.
     * If the maximum value is already reached, returns -1 to signal that no more work remains.
     *
     * @return the next integer to process or -1 if the range is exhausted
     */
	public int getNext(){
		lock.lock();
		try {
			if (count > max) {
				return -1;
			}
			return count++;
		}
		finally {
			lock.unlock();
		}

	}

}
