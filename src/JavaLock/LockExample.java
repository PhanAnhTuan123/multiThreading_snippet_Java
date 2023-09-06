package JavaLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
	
	private static ReentrantLock lock = new ReentrantLock();
	
	public static void main(String[] args) {
//		Lock lock = new ReentrantLock(true);
//		lock.lock();
//		
//		//do something
//		
//		lock.unlock();
//		lockBasics();
//		lockInterruptibly();
		tryLock();
	}
	
	private static void tryLock() {
		Lock lock = new ReentrantLock();
		try {
			boolean lockSuccessful;
			try {
				lockSuccessful = lock.tryLock(1000,TimeUnit.MILLISECONDS);
				System.out.println("Lock successful: "+lockSuccessful);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} finally {
			lock.unlock();
		}
	}
	
	
	//
	private static void lockInterruptibly() {
		Lock lock = new ReentrantLock();
		Thread.currentThread().interrupt();
		try {
			lock.lockInterruptibly();
			lock.unlock();
		} catch (InterruptedException e) {
			System.out.println("Thread interrupted!!!");
		}
		
	}
	
	
	
	//Lock Basic
	private static void lockBasics() {
		Lock lock = new ReentrantLock(false);
		Runnable runnable = ()->{
			lockSleepUnlock(lock, 1000);
		};
		Thread thread1 = new Thread(runnable, "Thread 1");
		Thread thread2 = new Thread(runnable, "Thread 2");
		Thread thread3 = new Thread(runnable, "Thread 3");
		thread1.start();
		thread2.start();
		thread3.start();
	}
	private static void lockSleepUnlock(Lock lock,long timeMillis) {
		try {
			lock.lock();
			printThread("holds the lock");
			sleep(1000);
		} finally {
			lock.unlock();
		}
	}
	private static void printThread(String text) {
		System.out.println(Thread.currentThread().getName() + text);
	}
	private static void sleep(long timeMillis) {
		try {
			Thread.sleep(timeMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static void accessResource() throws InterruptedException {
		boolean lockAccquired = lock.tryLock(5, TimeUnit.SECONDS);
		if(lockAccquired) {
			try {
				//access resource
			} finally {
				lock.unlock();
			}
		}else {
			//do alternate thing
		}
	}
}
