package com.perf.summit.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.perf.summit.BankAccount;
import com.perf.summit.BankTransaction;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4938056002655855602L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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

		PrintWriter writer = resp.getWriter();
		writer.append("Final Account Balance: " + account.getAccountBalance());
	}
}
