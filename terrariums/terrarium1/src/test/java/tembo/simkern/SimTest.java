/**
 * 
 */
package tembo.simkern;

import static org.junit.Assert.*;

import java.io.OutputStreamWriter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author angelmi
 *
 */
public class SimTest {

    static Logger logger = Logger.getLogger("tembo");
    {
        ConsoleAppender ap = new ConsoleAppender();
        ap.setWriter(new OutputStreamWriter(System.err));
        ap.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
        logger.removeAllAppenders();
        logger.addAppender(ap);
        
        logger.setLevel(Level.ALL);

    }
    
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

    /**
     * @throws SimSchedulingException 
     * 
     */
	@Test
    public void testSim() throws SimSchedulingException
    {
    	Sim sim = new Sim();
    	sim.scheduleDiscreteEvent("Event 1", 0.0, 1, new MyEvent());
    	sim.scheduleDiscreteEvent("Event 2", 0.0, 2, new MyEvent());
    	sim.scheduleContinuousActivity("Activity 1", 1.0, 3, new MyActivity());
    	sim.scheduleContinuousActivity("Activity 2", 1.0, 4, new MyActivity());
    	
    	sim.runSim(100.0);
    }

    class MyEvent extends SimObj
    {
    	int i = 1;
    	
    	@Override
    	public void execute(String occurrenceName, double currentTime, int priority, Sim sim) throws SimSchedulingException 
    	{
    		SimTest.logger.debug("Executing "+occurrenceName+" event at "+currentTime+" with priority="+priority);
    		
    		sim.scheduleDiscreteEvent(occurrenceName, i*1.0, priority, this);
    		i++;
    	}
    	
    }

    class MyActivity extends SimObj
    {
    	@Override
    	public void execute(String occurrenceName, double currentTime, int priority, Sim sim) throws SimSchedulingException 
    	{
    		SimTest.logger.debug("Executing "+occurrenceName+" event at "+currentTime+" with priority="+priority);
    		
    		// Cancel Self
    		if(currentTime>=50.0 && priority==3)
    		{
    			sim.cancelSchedulable(this.getActivityId());
    		}
    	}
    	
    }

}
