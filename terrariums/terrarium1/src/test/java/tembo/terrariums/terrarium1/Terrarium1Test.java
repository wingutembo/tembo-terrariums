package tembo.terrariums.terrarium1;

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
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)
public class Terrarium1Test 
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public Terrarium1Test()
    {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Logger logger = Logger.getRootLogger();
        logger.setLevel(Level.ALL);

        ConsoleAppender ap = new ConsoleAppender();
        ap.setWriter(new OutputStreamWriter(System.err));
        ap.setLayout(new PatternLayout("%d [%p|%c|%C{1}] %m%n"));
        logger.removeAllAppenders();
        logger.addAppender(ap);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testApp()
    {
        assertTrue( true );
    }
}
