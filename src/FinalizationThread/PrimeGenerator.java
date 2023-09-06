package FinalizationThread;

public class PrimeGenerator extends Thread{
	@Override
	public void run() {
		long number = 1L;
		while(true) {
			if(isPrime(number)) {
				System.out.printf("Number %d is Prime\n",number);
				if(isInterrupted()) {
					System.out.println("The Prime Generator has been Interrupted");
					return;
				}
				number++;
			}
		}
	}
	private boolean isPrime(long number) {
		if(number<=2) {
			return true;
		}
		for(long i=2;i<number;i++) {
			if((number%i)==0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Thread task=new PrimeGenerator();
		 task.start();
		 try {
			 Thread.sleep(5000);
			 task.interrupt();
		 } catch (InterruptedException e) {
			 e.printStackTrace();
			 }
			 
			 System.out.printf("Main: Status of the Thread: %s\n",
					 task.getState());
					 System.out.printf("Main: isInterrupted: %s\n",
					 task.isInterrupted());
					 System.out.printf("Main: isAlive: %s\n", task.isAlive());
	}
}
