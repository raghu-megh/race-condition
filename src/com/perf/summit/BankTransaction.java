package com.perf.summit;


public class BankTransaction extends Thread {
	 
	public static enum TransactionType {
		DEPOSIT_MONEY(1), WITHDRAW_MONEY(2);
 
		private TransactionType(int value) {
		}
	};
 
	private TransactionType transactionType;
	private BankAccount account;
	private double amount;
 
	/*
	 * If transactionType == 1, depositAmount() else if transactionType == 2 withdrawAmount()
	 */
	public BankTransaction(BankAccount account, TransactionType transactionType, double amount) {
		this.transactionType = transactionType;
		this.account = account;
		this.amount = amount;
	}
 
	public void run() {
		switch (this.transactionType) {
		case DEPOSIT_MONEY:
			depositAmount();
			printBalance();
			break;
		case WITHDRAW_MONEY:
			withdrawAmount();
			printBalance();
			break;
		default:
			System.out.println("NOT A VALID TRANSACTION");
		}
	}
 
	public void depositAmount() {
		this.account.depositAmount(this.amount);
	}
 
	public void withdrawAmount() {
		this.account.withdrawAmount(amount);
	}
	
	public synchronized void synchronizedDeposit() {
		this.account.depositAmount(this.amount);
	}
	
	public synchronized void synchronizedWithdraw() {
		this.account.depositAmount(this.amount);
	}
 
	public void printBalance() {
		System.out.println(Thread.currentThread().getName() + " : TransactionType: " + this.transactionType + ", Amount: " + this.amount);
		System.out.println("New Account Balance: " + this.account.getAccountBalance());
	}
}

