package tembo.terrariums.components;

import org.apache.log4j.Logger;


public class Organism 
{
	
    private static Logger logger = Logger.getLogger(Organism.class.getPackage().getName());

    private double sizeMax;
	private double energyMax;
	
	private double currentSize;
	
	private double K;
	
	private static int idfactory = 1;
	private int id = idfactory++;
	
	/**
	 * Constructor
	 * @param sizeMax
	 * @param energyMax
	 */
	public Organism(double sizeMax, double energyMax, double initialSize)
	{
		this.sizeMax = sizeMax;
		this.energyMax = energyMax;
		computeK();
		this.currentSize = initialSize;
	}
	
	/**
	 * Create the organisms profile
	 * @param sizeMax
	 * @param energyMax
	 * @throws OrganismDeadException 
	 */
	private void createProfile(double sizeMax, double energyMax) throws OrganismDeadException
	{
		if(this.currentSize > sizeMax || energyMax < 0.0)
		{
			throw new OrganismDeadException("Organism no longer viable. "+(this.currentSize>sizeMax?"Current Size exceeds Max Size":"Surface Area is less than 0"));
		}
		this.sizeMax = sizeMax;
		this.energyMax = energyMax;
		computeK();
		
	}
	
	/**
	 * Compute the K
	 */
	private void computeK()
	{
		K = Math.log(1.0+energyMax) / sizeMax;
	}
	
	/**
	 * METABOLISM
	 * y is energy, t is size
	 * y=e^k(t)-1
	 * @return the energy required for metabolism at current size
	 */
	
	private double metabolismAtCurrentSize()
	{
		double y = Math.exp(K*currentSize);
		return y;
	}
	
	/**
	 * MAX ABSORBTION
	 * y is energy, t is size
	 * y=(y(max)-(e^(k*(tmax-t)-1)
     * @return the maximum energy that can be absorbed at current size
	 */
	protected double maxAbsorbtionAtCurrentSize()
	{
		double y = energyMax - (Math.exp(K*(sizeMax-currentSize))-1.0);
		return y;
	}
	
	/**
	 * Grow the organism
	 * @param energyAbsorbed
	 * @throws OrganismDeadException 
	 */
	protected void grow(final double energyAbsorbed) throws OrganismDeadException
	{
		/* compute where in the energy to size profile it falls */
		double minEnergy = metabolismAtCurrentSize();
		if(energyAbsorbed<minEnergy)
		{
			// Re-evaluate the organisms profile
			double energyDeficit = minEnergy - energyAbsorbed;
			double sizeDeficit = energyDeficit * Universe.sizeToEnergyConstant;
			// Reduce the max energy available to this organism by the energy deficit
			this.createProfile(this.sizeMax - sizeDeficit, this.energyMax - energyDeficit);
			
			
		}
		else
		{
			double maxEnergy = maxAbsorbtionAtCurrentSize();
			double actualEnergyAbsorbed = energyAbsorbed;
			if(energyAbsorbed>maxEnergy)
			{
				actualEnergyAbsorbed = maxEnergy;
			}
			
			// Grow the organism with excess energy
			double excessEnergy = actualEnergyAbsorbed - minEnergy;
			this.currentSize += excessEnergy * Universe.sizeToEnergyConstant;
		}
		
		if(logger.isTraceEnabled())
		{
			logger.trace(this);
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Organism other = (Organism) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organism [sizeMax=" + sizeMax + ", energyMax=" + energyMax
				+ ", currentSize=" + currentSize + ", id=" + id + "]";
	}
	
}
