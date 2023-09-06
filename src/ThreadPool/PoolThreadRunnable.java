package ThreadPool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable{
	
	
	private Thread thread  = null;
	private BlockingQueue taskQueue = null;
	private boolean isStopped = false;
	
	
	
	
	public PoolThreadRunnable(BlockingQueue taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	@Override
	public void run() {
		this.thread = Thread.currentThread();
		while (!isStopped()) {	
			Runnable runnable;
			try {
				runnable = (Runnable)taskQueue.take();
				runnable.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public synchronized void doStop() {
		isStopped = true;
		thread.interrupted();
	}
	
	private boolean isStopped() {
		return isStopped;
	}
}
