package collatz;

import java.util.concurrent.locks.ReentrantLock;

public class Counter{
	private int count = 1;
	private int max;
	private ReentrantLock lock = new ReentrantLock();
	
	public Counter (int max) {
		this.max = max;
	}

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
