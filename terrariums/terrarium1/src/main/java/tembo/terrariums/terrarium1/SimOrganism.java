package tembo.terrariums.terrarium1;

import org.apache.log4j.Logger;

import tembo.simkern.Sim;
import tembo.simkern.SimObj;
import tembo.simkern.SimSchedulingException;
import tembo.terrariums.components.Organism;
import tembo.terrariums.components.OrganismDeadException;
import tembo.terrariums.components.Resource;

public class SimOrganism extends Organism {

	private Terrarium1 t1;
	
	/**
	 * Constructor
	 * 
	 * @param t1
	 * @param sizeMax
	 * @param energyMax
	 * @param initialSize
	 */
    public SimOrganism(Terrarium1 t1, double sizeMax, double energyMax, double initialSize) {
		super(sizeMax, energyMax, initialSize);
		this.t1 = t1;
	}

	static Logger logger = Logger.getLogger(SimOrganism.class.getPackage().getName());

	/**
	 * Eat activity
	 * @author angelmi
	 *
	 */
	class Eat extends SimObj
	{
		private double lastTime = 0.0;
		
		@Override
		public void execute(String eventName, double currentTime, int priority,
				Sim sim) throws SimSchedulingException {

			SimOrganism.logger.trace("Energy Refresh@"+currentTime);

			Resource r = t1.getResource("Nutrient");
			if(r==null)
			{
				throw new SimSchedulingException("No such resource as Nutrient");
			}
			
			double maxAbsorbtionAtCurrentSize = SimOrganism.this.maxAbsorbtionAtCurrentSize();
			double energyConsumed;

			energyConsumed = r.consume(maxAbsorbtionAtCurrentSize,currentTime-lastTime);
			try {
				SimOrganism.this.grow(energyConsumed);
			} catch (OrganismDeadException e) {
				// Unschedule this activity
				this.unschedule(sim);
			}
			
			lastTime = currentTime;
			
		}

	}

}
