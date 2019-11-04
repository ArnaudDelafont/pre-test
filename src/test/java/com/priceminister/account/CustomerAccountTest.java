package com.priceminister.account;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 *
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 *
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 *
 */
public class CustomerAccountTest {

	Account customerAccount;
	AccountRule rule;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		customerAccount = new CustomerAccount();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		Double balance = customerAccount.getBalance();
		assertTrue(balance != null && balance.doubleValue() == 0.0);
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 */
	@Test
	public void testAddPositiveAmount() {
		Double balance = customerAccount.getBalance();
		customerAccount.add(12.5);
		assertTrue(balance + 12.5 == customerAccount.getBalance());
	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 *
	 * @throws IllegalBalanceException
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws IllegalBalanceException {
		customerAccount.add(10.0);
		customerAccount.withdrawAndReportBalance(50.0, new CustomerAccountRule());
	}

	/**
	 * Tests that a legal withdrawal does not throw the expected exception and
	 * checks that the new balance is as expected.
	 *
	 */
	@Test
	public void testWithdrawAndReportBalanceLegalBalance() throws IllegalBalanceException {
		customerAccount.add(10.0);

		// test a legal withdrawal
		customerAccount.withdrawAndReportBalance(5.0, new CustomerAccountRule());
		assertTrue(customerAccount.getBalance().doubleValue() == 5.0);

		// test the limit of a legal withdrawal when it empties the account
		customerAccount.withdrawAndReportBalance(5.0, new CustomerAccountRule());
		assertTrue(customerAccount.getBalance().doubleValue() == 0.0);
	}

}
