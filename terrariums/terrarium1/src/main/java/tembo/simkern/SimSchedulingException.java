package tembo.simkern;

public class SimSchedulingException extends Exception 
{
	
	public SimSchedulingException(String eventName) 
	{
		super(eventName);
	}

	public SimSchedulingException(Exception e) {
		super(e);
	}

}
