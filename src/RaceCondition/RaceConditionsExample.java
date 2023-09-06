package RaceCondition;

class RaceConditionsExample {
	public static void main(String[] args) {
		Counter counter = new Counter();
		
		
		
//		Thread thread1 = new Thread(getRunnable(counter, "Thread1 final count: "));
//		Thread thread2 = new Thread(getRunnable(counter, "Thread2 final count: "));
		Thread thread1 = new Thread(getIncrementingRunnable(counter));
		Thread thread2 = new Thread(getReadingRunnable(counter));
		thread1.start();
		thread2.start();
	}
	private static Runnable getReadingRunnable(Counter counter) {
		return()->{
			for (int i = 0; i < 5; i++) {
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread2 count: "+counter.get());
			}
		};
	}
	private static Runnable getRunnable(Counter counter,String message) {
		return ()->{
			for (int i = 0; i < 1_000_000; i++) {
				counter.incAndGet();
			}
			System.out.println(message + counter.get());
		};
	}
	private static Runnable getIncrementingRunnable(Counter counter) {
		return ()->{
			for (int i = 0; i < 1_000_000; i++) {
				counter.incAndGet();
			}
			System.out.println("Thread1 final count: "+counter.get());
			
		};
	}
}
