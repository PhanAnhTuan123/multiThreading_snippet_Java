package JavaLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class Calculation {
	public static final int UNSPECIFIED = -1;
	public static final int ADDTION = 0;
	public static final int SUBTRACTION = 1;
	int type = UNSPECIFIED;
	public double value;
	private double result = 0.0D;
	public Calculation(int type, double value) {
		this.type = type;
		this.value = value;
	}
	
	Lock lock = new ReentrantLock();
	
	public void add(double value) {
		try {

			lock.lock();
			this.result +=value;
		} finally {
			lock.unlock();
		}
	}
	public void subtract(double value) {
		try {

			lock.lock();
			this.result -=value;
		} finally {
			lock.unlock();
		}
	}
	public void calculate(Calculation ...calculations ) {
		
		try {	
			lock.lock();
			for (Calculation calculation : calculations) {
				switch(calculation.type) {
				case Calculation.ADDTION : add(calculation.value); break;
				case Calculation.SUBTRACTION : subtract(calculation.value); break;	
				}
			}
		} finally {
			lock.unlock();
			
		}
		
	}
}
