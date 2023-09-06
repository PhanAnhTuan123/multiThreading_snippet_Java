package virtualThread;

public class SeparateObjects {
	public static void main(String[] args) {
		int myLocalVar = 0;
		String myLocalString = "Test";
		
		Runnable runnable1 = new MyRunnable();
		Runnable runnable2 = new MyRunnable();
		
				
		Thread thread1 = new Thread(runnable1, "Thread 1");
		Thread thread2 = new Thread(runnable2, "Thread 1");
		
		thread1.start();
		thread2.start();
	}
}
