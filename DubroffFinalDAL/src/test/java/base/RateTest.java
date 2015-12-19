package base;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import domain.RateDomainModel;

public class RateTest {

	ArrayList <RateDomainModel> rates = RateDAL.getRates();
	double TestRate1 = RateDAL.getRate(725);
	double TestRate2 = RateDAL.getRate(604);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testGetRates(){
		assertTrue(rates.isEmpty() == false);
		assertTrue(rates.size()==5);
	}

	@Test
	public void testGetRate() {

		assertTrue(TestRate1 == 4);
		
		assertTrue(TestRate2 == 5);

	}


}
