package executorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServicesExample {
	public static void main(String[] args) {
//		example1();
//		submitExample();
//		submitCallable();
//		invokeAnyExample();
		invokeAlExample();
	}
	
	
	private static void invokeAlExample() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Callable<String>>callables = new ArrayList<Callable<String>>();
		callables.add(newCallable("Task 1.1"));
		callables.add(newCallable("Task 1.2"));
		callables.add(newCallable("Task 1.3"));
		
		try {
			List<Future<String>>results = executorService.invokeAll((Collection<Callable<String>>) callables);
		for (Future<String> future : results) {
			System.out.println(future.get());
		}
		} catch (Exception e) {
		}
		executorService.shutdown();
		
	}
	
	private static void invokeAnyExample() {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Callable<String>>callables = new ArrayList<Callable<String>>();
		callables.add(newCallable("Task 1.1"));
		callables.add(newCallable("Task 1.2"));
		callables.add(newCallable("Task 1.3"));
		
		try {
			String result = (String) executorService.invokeAny((Collection)callables);
			System.out.println(result);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executorService.shutdown();
	}
	
	private static void submitCallable() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		Future future = executorService.submit(newCallable("Task 1.1"));
		System.out.println(future.isDone());
		
		String result;
		try {
			result = (String) future.get();
			System.out.println(result);
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
		System.out.println(future.isDone());
		executorService.shutdown();
	}
	
	
	
	private static Callable newCallable(String string) {
		// TODO Auto-generated method stub
//		return new Callable() {
//			@Override
//			public Object call() throws Exception {
//				String compelteMsg = Thread.currentThread().getName() + " : "+string;
//				return compelteMsg;
//			}
//		};
		return ()->{
			String compelteMsg = Thread.currentThread().getName() + " : "+string;
			return compelteMsg;
		};
	}

	private static void submitExample() {
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		Future future = executorService.submit(newRunnable("Task 1.1."));
		System.out.println(future.isDone());
		
		try {
			future.get();
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		}
		System.out.println(future.isDone());
		
		executorService.shutdown();
	}
	
	private static void example1() {
		
//		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		
		executorService.execute(newRunnable("Task 1"));
		executorService.execute(newRunnable("Task 2"));
		executorService.execute(newRunnable("Task 3"));
		
		executorService.shutdown();
	}
	
	private static Runnable newRunnable(String msg) {
		return  ()->{
			String completeMsg = Thread.currentThread().getName() + " : " + msg;
			System.out.println(completeMsg);
		};
	}
}
