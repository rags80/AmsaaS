/**
 * 
 */
package com.ams.sharedkernel.domain.model.measuresandunits;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Raghavendra Badiger
 * 
 */
public class MoneyTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#copy()}.
	 */
	@Test
	public final void testCopy()
	{
		Money m = new Money(null, null);
		System.out.println(m.getAmount());
	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#add(java.math.BigDecimal)}
	 * .
	 */
	@Test
	public final void testAddBigDecimal()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before addition operation:" + m1.getAmount());
		Money m2 = m1.add(BigDecimal.TEN);
		System.out.println("M1 value after addition operation:" + m1.getAmount());
		System.out.println("M2 value after addition operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#add(com.ams.sharedkernel.domain.model.measuresandunits.Money)}
	 * .
	 */
	@Test
	public final void testAddMoney()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before addition operation:" + m1.getAmount());
		Money m2 = m1.add(new Money(BigDecimal.TEN, Currency.getInstance("INR")));
		System.out.println("M1 value after addition operation:" + m1.getAmount());
		System.out.println("M2 value after addition operation:" + m2.getAmount());
		m2 = m1.add(new Money(BigDecimal.TEN, Currency.getInstance("GBP")));
		System.out.println("M2 value after addition operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#divideBy(java.math.BigDecimal)}
	 * .
	 */
	@Test
	public final void testDivideByBigDecimal()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before division operation:" + m1.getAmount());
		Money m2 = m1.divideBy(BigDecimal.TEN);
		System.out.println("M1 value after division operation:" + m1.getAmount());
		System.out.println("M2 value after division operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#divideBy(com.ams.sharedkernel.domain.model.measuresandunits.Money)}
	 * .
	 */
	@Test
	public final void testDivideByMoney()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before division operation:" + m1.getAmount());
		Money m2 = m1.divideBy(new Money(BigDecimal.TEN, Currency.getInstance("INR")));
		System.out.println("M1 value after division operation:" + m1.getAmount());
		System.out.println("M2 value after division operation:" + m2.getAmount());
		m2 = m1.divideBy(new Money(BigDecimal.TEN, Currency.getInstance("GBP")));
		System.out.println("M1 value after division operation:" + m1.getAmount());
		System.out.println("M2 value after division operation:" + m2.getAmount());
	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#multiplyBy(java.math.BigDecimal)}
	 * .
	 */
	@Test
	public final void testMultiplyByBigDecimal()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before multiplication operation:" + m1.getAmount());
		Money m2 = m1.multiplyBy(BigDecimal.TEN);
		System.out.println("M1 value after multiplication operation:" + m1.getAmount());
		System.out.println("M2 value after multiplication operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#multiplyBy(com.ams.sharedkernel.domain.model.measuresandunits.Money)}
	 * .
	 */
	@Test
	public final void testMultiplyByMoney()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before multiplication operation:" + m1.getAmount());
		Money m2 = m1.multiplyBy(new Money(BigDecimal.TEN, Currency.getInstance("INR")));
		System.out.println("M1 value after multiplication operation:" + m1.getAmount());
		System.out.println("M2 value after multiplication operation:" + m2.getAmount());
		m2 = m1.multiplyBy(new Money(BigDecimal.TEN, Currency.getInstance("USD")));
		System.out.println("M1 value after multiplication operation:" + m1.getAmount());
		System.out.println("M2 value after multiplication operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#subtract(java.math.BigDecimal)}
	 * .
	 */
	@Test
	public final void testSubtractBigDecimal()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before subtraction operation:" + m1.getAmount());
		Money m2 = m1.subtract(BigDecimal.TEN);
		System.out.println("M1 value after subtraction operation:" + m1.getAmount());
		System.out.println("M2 value after subtraction operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#subtract(com.ams.sharedkernel.domain.model.measuresandunits.Money)}
	 * .
	 */
	@Test
	public final void testSubtractMoney()
	{
		Money m1 = new Money(BigDecimal.TEN, Currency.getInstance("INR"));
		System.out.println("M1 value before subtraction operation:" + m1.getAmount());
		Money m2 = m1.subtract(new Money(BigDecimal.ONE, Currency.getInstance("INR")));
		System.out.println("M1 value after subtraction operation:" + m1.getAmount());
		System.out.println("M2 value after subtraction operation:" + m2.getAmount());
		m2 = m1.subtract(new Money(BigDecimal.ONE, Currency.getInstance("GBP")));
		System.out.println("M1 value after subtraction operation:" + m1.getAmount());
		System.out.println("M2 value after subtraction operation:" + m2.getAmount());

	}

	/**
	 * Test method for
	 * {@link com.ams.sharedkernel.domain.model.measuresandunits.Money#compareTo(com.ams.sharedkernel.domain.model.measuresandunits.Money)}
	 * .
	 */
	@Test
	public final void testCompareTo()
	{
		fail("Not yet implemented");
	}

}
