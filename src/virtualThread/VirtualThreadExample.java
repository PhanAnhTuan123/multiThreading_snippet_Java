package virtualThread;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadExample {
	public static void main(String[] args) {
		example2();
		
	}
	static void example2() {
		List<Thread>vThreads = new ArrayList<>();
		int vThreadCount = 100_000;
		for (int i = 0; i < vThreadCount; i++) {
			int vThreadIndex = i;
			
			@SuppressWarnings("preview")
			Thread vThread = Thread.ofVirtual().start(()->{
				int result = 1;
				for (int j = 0; j < 10; j++) {
					result *=(j+1);
				}
				System.out.println("Result["+vThreadIndex+"]:"+result);
			});
			vThreads.add(vThread);
		}
		for (int i = 0; i < vThreads.size(); i++) {
			try {
				vThreads.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static void example1() {
		Runnable runnable = ()->{
			for (int i = 0; i < 10; i++) {
				System.out.println("Index "+i);
			}
		};
		Thread vThread1 = Thread.ofVirtual().start(runnable);
	
		
		Thread vtThreadUnstart = Thread.ofVirtual().unstarted(runnable);
		
		vtThreadUnstart.start();
		
		try {
			vtThreadUnstart.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
