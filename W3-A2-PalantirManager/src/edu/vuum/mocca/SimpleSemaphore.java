package edu.vuum.mocca;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;

/**
 * @class SimpleSemaphore
 * 
 * @brief This class provides a simple counting semaphore implementation using
 *        Java a ReentrantLock and a ConditionObject. It must implement both
 *        "Fair" and "NonFair" semaphore semantics, just liked Java Semaphores.
 */
public class SimpleSemaphore {
	private int permits;
	private ReentrantLock lock;
	private Condition condition;

	/**
	 * Constructor initialize the data members.
	 */
	public SimpleSemaphore(int permits, boolean fair) {
		// TODO - you fill in here
		this.permits = permits;
		this.lock = new ReentrantLock(fair);
		condition = lock.newCondition();
	}

	/**
	 * Acquire one permit from the semaphore in a manner that can be
	 * interrupted.
	 */
	public void acquire() throws InterruptedException {
		// TODO - you fill in here
		lock.lockInterruptibly();
		try {
			while (permits == 0) {
				condition.await();
			}
			permits--;

		} finally {
			lock.unlock();
		}

	}

	/**
	 * Acquire one permit from the semaphore in a manner that cannot be
	 * interrupted.
	 */
	public void acquireUninterruptibly() {
		// TODO - you fill in here
		lock.lock();
		try {
			while (permits == 0) {
				condition.awaitUninterruptibly();
			}
			permits--;
		} finally {
			lock.unlock();
		}

	}

	/**
	 * Return one permit to the semaphore.
	 */
	void release() {
		// TODO - you fill in here
		lock.lock();
        try {
            // increment permit count
            permits++;
            // letting know all interested in that threads that Condition has been met
            condition.signal();
        } finally {
            lock.unlock();
        }
	}

	/**
	 * Return the number of permits available.
	 */
	public int availablePermits() {
		// TODO - you fill in here
		return permits; // You will change this value.
	}

	/**
	 * Define a ReentrantLock to protect the critical section.
	 */
	// TODO - you fill in here

	/**
	 * Define a ConditionObject to wait while the number of permits is 0.
	 */
	// TODO - you fill in here

	/**
	 * Define a count of the number of available permits.
	 */
	// TODO - you fill in here
}
