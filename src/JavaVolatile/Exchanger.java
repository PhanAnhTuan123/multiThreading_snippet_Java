package JavaVolatile;

public class Exchanger {
	private Object object = null;
	private volatile boolean hasNewObject = false;
	public Exchanger(Object object) {
		this.object = object;
	}
	public void setObject(Object object) {
		this.object = object;
		this.hasNewObject = true;
	}
	public  Object getObject() {
		while(!this.hasNewObject) {
			//busy wait
		}
		Object returnValue = this.object;
		this.hasNewObject = false;
		return returnValue;
	}
	
	
}
