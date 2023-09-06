package com.anhTuan.ncurrencydTuMultithreading;

public class ThreadExample {
	public static void main(String[] args) {
//		example1();
//		example2();
//		example3();
//		example4();
		example5();
	}
	static void example5() {
		Runnable runnable = ()->{
			for (int i = 0; i < 5; i++) {
				sleep(1000);
				System.out.println("Running");
			}
		};
		Thread thread = new Thread(runnable);
		thread.setDaemon(true);
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void example4() {
		Runnable runnable = ()->{
			while(true) {
				sleep(1000);
				System.out.println("Running");
			}
		};
		Thread thread   = new Thread(runnable);
		thread.setDaemon(true); // Phụ thuộc vào luồng chính main()
		thread.start();
		sleep(3100);
	}
	static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static void example3() {
		StoppableRunnable stoppableRunnable = new StoppableRunnable();
		Thread thread = new Thread(stoppableRunnable,"The Thread");
		thread.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("requesting stop");
		stoppableRunnable.requestStop();
		System.out.println("Stop requested!!");
	}
	
	public static class StoppableRunnable implements Runnable{
		private boolean stopRequested = false;
		public synchronized void requestStop() {
			this.stopRequested  = true;
		}
		public synchronized boolean isStopRequested() {
			return this.stopRequested;
		}
		private void sleep(long millis) {
			try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Important Method
		@Override
		public void run() {
			System.out.println("StoppableRunnable Running");
			while(!isStopRequested()) {
				sleep(1000);
				System.out.println("...");
			}
			System.out.println("StoppableRunnable Stopped");
		}
	}
	static void example1() {
		Runnable runnable = ()->{
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " running");
		};
		Thread thread = new Thread(runnable, "The Thread 1");
//		thread.setName("Thread 1 moi fix");
		thread.start();
		Thread thread2 = new Thread(thread, "The Thread 2");
		thread2.start();
	}
	static void example2() {
		Runnable runnable = ()->{
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName+ " running");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(threadName+" is finish");
		};
		Thread thread = new Thread(runnable,"The Thread");
		thread.start();
	}
}
