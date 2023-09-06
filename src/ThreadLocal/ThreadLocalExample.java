package ThreadLocal;

public class ThreadLocalExample {
	public static void main(String[] args) {
		example2();
	}
	static void example2() {
		ThreadLocal<String>threadlocal = new ThreadLocal<>();
		InheritableThreadLocal<String>inheritableThreadlocal = new InheritableThreadLocal<>();
		Thread thread1 = new Thread(()->{
			System.out.println("==== Thread 1 ====");
			threadlocal.set("Thread 1 - ThreadLocal");
			inheritableThreadlocal.set("Thread 1 - InheritableThreadLocal");
			System.out.println(threadlocal.get());
			System.out.println(inheritableThreadlocal.get());
			
			Thread childThread  = new Thread(()->{
				System.out.println("==== ChildThread ====");
				System.out.println(threadlocal.get());
				System.out.println(inheritableThreadlocal.get());
			});
			childThread.start();
			
		});
		
		Thread thread2 = new Thread(()->{
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("==== Thread 2 ====");
			System.out.println(threadlocal.get());
			System.out.println(inheritableThreadlocal.get());
			
		});
		thread1.start();
		thread2.start();
		
	}
	static void example1() {
		ThreadLocal<MyObject>threadLocal1 = new ThreadLocal<>() {
			@Override
			protected MyObject initialValue() {
				return new MyObject();
			}
		};
		ThreadLocal<MyObject>threadLocal2 = ThreadLocal.withInitial(()->{
			return new MyObject();
		});
		Thread thread1 = new Thread(()->{
			System.out.println("ThreadLocal 1: " + threadLocal1.get());
			System.out.println("ThreadLocal 2: " + threadLocal2.get());
			
		});
		Thread thread2 = new Thread(()->{
			System.out.println("ThreadLocal1: "	+threadLocal1.get());
			System.out.println("ThreadLocal2: "+threadLocal2.get());
		});
		
		
		
		
//		Thread thread1 = new Thread(()->{
//			threadLocal.set("Thread 1");
////			try {
////				Thread.sleep(2000);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//			String value = threadLocal.get();
//			System.out.println(value);
//			threadLocal.remove();
//			value = threadLocal.get();
//			System.out.println(value);
//		});
//		Thread thread2 = new Thread(()->{
//			threadLocal.set("Thread 2");
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String value = threadLocal.get();
//			System.out.println(value);
//			threadLocal.remove();
//			value = threadLocal.get();
//			System.out.println(value);
//		});
		thread1.start();
		thread2.start();
	
	}
}
