package com.perf.summit;

import com.perf.summit.BankTransaction.TransactionType;

public class SynchronizedBankTransaction extends Thread {

	private TransactionType transactionType;
	private BankAccount account;
	private double amount;

	/*
	 * If transactionType == 1, depositAmount() else if transactionType == 2 withdrawAmount()
	 */
	public SynchronizedBankTransaction(BankAccount account, TransactionType transactionType, double amount) {
		this.transactionType = transactionType;
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		switch (this.transactionType) {
		case DEPOSIT_MONEY:
			synchronizedDeposit();
			printBalance();
			break;
		case WITHDRAW_MONEY:
			synchronizedWithdraw();
			printBalance();
			break;
		default:
			System.out.println("NOT A VALID TRANSACTION");
		}
	}

	public synchronized void synchronizedDeposit() {
		this.account.depositAmount(this.amount);
	}

	public synchronized void synchronizedWithdraw() {
		this.account.withdrawAmount(this.amount);
	}

	public void printBalance() {
		System.out.println(Thread.currentThread().getName() + " : TransactionType: " + this.transactionType + ", Amount: " + this.amount);
		System.out.println("New Account Balance: " + this.account.getAccountBalance());
	}
}
