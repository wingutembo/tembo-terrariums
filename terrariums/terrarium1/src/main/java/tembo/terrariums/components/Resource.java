package tembo.terrariums.components;


public class Resource 
{
	/**
	 * Field constructor
	 * @param name of the resource
	 * @param available initial quantity available
	 * @param flow velocity of the resource
	 * @param volume the volume the resource occupies
	 */
	public Resource(String name, double available, double flow, double volume) {
		this.name = name;
		this.available = available;
		this.flow = flow;
		this.volume = volume;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Resource [name=" + name + ", available=" + available
				+ ", flow=" + flow + ", volume=" + volume + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Resource other = (Resource) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private String name;
	private double available;
	private double flow;
	private double volume;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the available
	 */
	public double getAvailable() {
		return available;
	}

	/**
	 * @return the flow
	 */
	public double getFlow() {
		return flow;
	}

	/**
	 * @return the volume
	 */
	public double getVolume() {
		return volume;
	}

	/**
	 * Consume the resource
	 * @param maxAbsorbtionAtCurrentSize - the most the organism can eat, i.e. surface area
	 * @param deltaTime - the time of eating
	 * @return the energy actually acquired
	 */
	public double consume(double maxAbsorbtionAtCurrentSize, double deltaTime) 
	{
		// density = N/V
		double density = this.available / this.volume;
		// distance = flow * deltaTime
		double distance = this.flow * deltaTime;
		// n = most the organism can eat * the amount of food that flows by it
		double n = maxAbsorbtionAtCurrentSize * distance * density;
		
		// Remove the n from the available
		this.available -= n;
		if(this.available < 0)
		{
			n = this.available;
			this.available = 0;
		}
		
		// Convert n into energy
		double e = n * Universe.sizeToEnergyConstant;
		
		return e;
	}


	
}
