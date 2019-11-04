package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private Double balance;

	@Override
	public void add(Double addedAmount) {
		if (this.balance == null) {
			this.balance = addedAmount;
		} else {
			this.balance += addedAmount;
		}
	}

	@Override
	public Double getBalance() {
		return this.balance == null ? 0.0 : this.balance;
	}

	@Override
	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		double resultingAccountBalance = this.balance - withdrawnAmount;

		if (rule.withdrawPermitted(resultingAccountBalance)) {
			this.add(-withdrawnAmount);
		} else {
			throw (new IllegalBalanceException(resultingAccountBalance));
		}
		return this.balance;
	}

}
