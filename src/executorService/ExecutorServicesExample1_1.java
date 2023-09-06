package executorService;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServicesExample1_1 {
	public static void main(String[] args) {
		int corePoolSize = 10;
		int maxPoolSize = 20;
		long keepAliveTime =3000;
		
		ExecutorService threadPoolExecutor = 
				new ThreadPoolExecutor(corePoolSize,
				maxPoolSize,
				keepAliveTime, 
				TimeUnit.MILLISECONDS, 
				new ArrayBlockingQueue<Runnable>(128));
		
		
		threadPoolExecutor = Executors.newFixedThreadPool(3);
		
		ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(corePoolSize);
		
	}
}
