package ThreadPool;

public class ThreadPoolMain {
	public static void main(String[] args) {
		Threadpool threadPool = new Threadpool(3,10);
		for (int i = 0; i < 10; i++) {
			int taskNo = i;
			try {
				threadPool.execute(()->{
					String 	message = Thread.currentThread().getName() + ": Task" + taskNo;
					System.out.println(message);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		threadPool.waitUntilAllTasksFinished();
		threadPool.stop();
	}
}
