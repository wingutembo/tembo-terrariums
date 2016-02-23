package tembo.terrariums.terrarium1;

import java.io.OutputStreamWriter;
import java.util.HashMap;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import tembo.simkern.Sim;
import tembo.simkern.SimSchedulingException;
import tembo.terrariums.components.Resource;
import tembo.terrariums.components.Universe;

/**
 * Hello world!
 *
 */
public class Terrarium1 
{
	
    private static Logger logger = Logger.getLogger("tembo");
    {
        ConsoleAppender ap = new ConsoleAppender();
        ap.setWriter(new OutputStreamWriter(System.err));
        // ap.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
        ap.setLayout(new PatternLayout("%m%n"));
        logger.removeAllAppenders();
        logger.addAppender(ap);
        
        logger.setLevel(Level.TRACE);

    }

    private HashMap<String,Resource> resources = new HashMap<String,Resource>();
	
	public void addResource(Resource resource)
	{
		this.resources.put(resource.getName(), resource);
	}

	public Resource getResource(String name) {
		return this.resources.get(name);
	}

	public static void main( String[] args ) throws SimSchedulingException
    {
    	// Create the simulation
    	Sim sim = new Sim();
    	
    	// Create the world!
    	Terrarium1 t1 = new Terrarium1();
        
        // Create the resource
        String name = "Nutrient";
        double available = 100000.0;
        double flow = 1.0;
        double volume = 100000.0;
        
        Resource resource = new Resource(name, available, flow, volume); 
        
        // Add the resource to the world
        t1.addResource(resource);
        
        // Create organisms
        for(int i = 0; i < 100; i++)
        {
        	SimOrganism simOrganism = new SimOrganism(t1,100.0,100.0,5.0);
        	SimOrganism.Eat eat = simOrganism.new Eat();
        	
        	// Start the cycle of rain - for now fixed at every 3 days 
        	sim.scheduleContinuousActivity("eat", 1.0/* *3 */, 4, eat);

        }
        
        sim.runSim(1000000); // A million days
        
    }

}
