package usingInterruptException;

import java.util.concurrent.Callable;

public class InterruptExample {
	public static void main(String[] args) {
		Thread taskThread = new Thread(new Task());
		taskThread.start();
		System.out.println("Long running task started");
	
		//Hủy 1 thread đang chạy -> không nên sử dụng cancel()
		
		//some time later 
		
		//oops, changed my mind
		taskThread.interrupt();
		System.out.println("Asking politely to stop");
		
		
	}
	static class Task implements Runnable{
		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				
				//perform few (subnet of) steps	
//				processThisNumber(i);

				//poll for interrupts
				if(Thread.currentThread().isInterrupted()) {
					//If thread stops abruptly it may leave things in inconsistent state
					// Data integrity
					// Open connections
					// Half operations
					
					//-> Thread has a chance for clean up
					
					
					//don't care.. ignoring
					System.out.println("Stopping the task!");
					return;
				}
				
			}
		}
}
	static class Integer  implements Callable<Integer>{
		@Override
		public Integer call() throws Exception {
			for (int i = 0; i < 10000; i++) {
				//perform few (subnet of) steps
//				processThisNumber(i);
				
				//poll for interrupts
				if(Thread.currentThread().isInterrupted()) {
					throw new InterruptedException();
				}
			}
			return null;
		}
	}
}
