/**
 * 
 */
package tembo.terrariums.components;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
public class ComponentsTest {

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

	@Test
	public void testOrganism() 
	{
		double sizeMax = 100;
		double energyMax = 100; 
		
		Organism[] organisms = new Organism[20];
		
		organisms[0] = new Organism(sizeMax, energyMax, 5);
		organisms[1] = new Organism(sizeMax, energyMax, 10);
		organisms[2] = new Organism(sizeMax, energyMax, 15);
		organisms[3] = new Organism(sizeMax, energyMax, 20);
		organisms[4] = new Organism(sizeMax, energyMax, 25);
		organisms[5] = new Organism(sizeMax, energyMax, 30);
		organisms[6] = new Organism(sizeMax, energyMax, 35);
		organisms[7] = new Organism(sizeMax, energyMax, 40);
		organisms[8] = new Organism(sizeMax, energyMax, 45);
		organisms[9] = new Organism(sizeMax, energyMax, 50);
		organisms[10] = new Organism(sizeMax, energyMax, 55);
		organisms[11] = new Organism(sizeMax, energyMax, 60);
		organisms[12] = new Organism(sizeMax, energyMax, 65);
		organisms[13] = new Organism(sizeMax, energyMax, 70);
		organisms[14] = new Organism(sizeMax, energyMax, 75);
		organisms[15] = new Organism(sizeMax, energyMax, 80);
		organisms[16] = new Organism(sizeMax, energyMax, 85);
		organisms[17] = new Organism(sizeMax, energyMax, 90);
		organisms[18] = new Organism(sizeMax, energyMax, 95);
		organisms[19] = new Organism(sizeMax, energyMax, 100);

		double maxExcess = 0.0;
		int sizeAtMaxExcess = -1;
		
		for(int i = 0; i < 20; i++)
		{
			double m = organisms[i].metabolismAtCurrentSize();
			double sa = organisms[i].maxAbsorbtionAtCurrentSize();
			
			double excess = sa - m;
			if(excess > maxExcess)
			{
				maxExcess = excess;
				sizeAtMaxExcess = i;
			}
		}
		
		assertTrue(sizeAtMaxExcess == 9);
		
		
	}

	@Test
	public void testOrganismGrowth() 
	{
		double sizeMax = 100;
		double energyMax = 100; 
		
		Organism o = new Organism(sizeMax,energyMax,5);

		// for energy at SA
 		int age = o.getAge();
		assertEquals(0,age); // Birth age should be 0
		double lastCurrentSize = o.getCurrentSize();
		assertEquals(5.0,lastCurrentSize,0.00001);
		
		try {
			while(age<1000)
			{
				double sa = o.maxAbsorbtionAtCurrentSize();
					o.grow(sa);
				
				double currentSize = o.getCurrentSize();
				assertTrue(currentSize >= lastCurrentSize);
				assertTrue(currentSize <= sizeMax);
				
				lastCurrentSize = currentSize;
				age = o.getAge();
				
				logger.trace(o);
			}		
			fail("Too old");
		} catch (OrganismDeadException e) {
			logger.error(e);
		}
		
		
		
	}
}
