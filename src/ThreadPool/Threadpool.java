package ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Threadpool {
	private BlockingQueue<Runnable> taskQueue = null;
	private List<PoolThreadRunnable>runnables = new ArrayList<>();
	private boolean isStopeed = false;
	
	public Threadpool(int noOfThreads,int maxNoOfTasks) {
		taskQueue = new ArrayBlockingQueue<Runnable>(maxNoOfTasks);
		for (int i = 0; i < noOfThreads; i++) {
			PoolThreadRunnable poolThreadRunnable = new PoolThreadRunnable(taskQueue);
			runnables.add(poolThreadRunnable);
		}
		for (PoolThreadRunnable poolThreadRunnable : runnables) {
			new Thread(poolThreadRunnable).start();
		}
	}
	public synchronized void execute(Runnable task) throws Exception{
		if(this.isStopeed) throw new IllegalStateException("Thread is Stopped");
		this.taskQueue.offer(task);
	}
	public synchronized void stop() {
		this.isStopeed = true;
		for (PoolThreadRunnable runnable : runnables) {
			runnable.doStop();
		}
	}
	public synchronized void waitUntilAllTasksFinished() {
		while(this.taskQueue.size() > 0) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
