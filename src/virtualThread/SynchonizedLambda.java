package virtualThread;

import java.util.function.Consumer;



public class SynchonizedLambda {
	public class Reentrance{
		private int count = 0;
		public synchronized void inc() {
			this.count++;
		}
		public synchronized int incAndGet() {
			
		}
	}
	private static Object object = null;
	public static synchronized void setObject(Object o) {
		object = o;
	}
	public static void consumeObject(Consumer consumer) {
		consumer.accept(object);
	}
	public static void main(String[] args) {
		consumeObject((obj)->{
			synchronized (SynchonizedLambda.class) {
				System.out.println(obj);
			}
		});
		consumeObject((obj)->{
			synchronized (String.class) {
				System.out.println(obj);
			}
		});
	}
}
