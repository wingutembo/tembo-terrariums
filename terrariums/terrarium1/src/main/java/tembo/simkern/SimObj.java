package tembo.simkern;

public abstract class SimObj 
{
	/**
	 * Implement the behavior for this simulation object
	 * 
	 * @param eventName
	 * @param currentTime
	 * @param priority
	 * @param sim
	 * @throws SimSchedulingException
	 */
	public abstract void execute(String eventName, double currentTime, int priority, Sim sim)  throws SimSchedulingException;

	/**
	 * Unschedules this activity
	 * @param sim
	 */
	public void unschedule(Sim sim)
	{
		sim.cancelSchedulable(this.activityId);
	}
	
	private long activityId;
	
	public void setActivityId(long activityId)
	{
		this.activityId = activityId;
	}
	
	public long getActivityId() {
		return this.activityId;
	}
	
	


}
