package collatz;

import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable{
	private Counter counter;
	private int[] histogram;
	private ReentrantLock lock;
	
	public Worker(Counter counter, int[] histogram, ReentrantLock lock) {
		this.counter = counter;
		this.histogram = histogram;
		this.lock = lock;
	}
	

	@Override
	public void run() {
		int value = counter.getNext();
		while(true) {
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
