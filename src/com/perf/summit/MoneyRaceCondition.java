package com.perf.summit;


public class MoneyRaceCondition {
	 
	public static void main(String[] args) {
		BankAccount account = new BankAccount("AccountNumber");
 
		// Total Expected Deposit: 10000 (100 x 100)
		for (int i = 0; i < 100; i++) {
			BankTransaction t = new BankTransaction(account, BankTransaction.TransactionType.DEPOSIT_MONEY, 100);
			t.start();
		}
 
		// Total Expected Withdrawal: 5000 (100 x 50)
		for (int i = 0; i < 100; i++) {
			BankTransaction t = new BankTransaction(account, BankTransaction.TransactionType.WITHDRAW_MONEY, 50);
			t.start();
 
		}
 
		// Let's just wait for a second to make sure all thread execution completes.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
 
		// Expected account balance is 5000
		System.out.println("Final Account Balance: " + account.getAccountBalance());
	}
}