package es.rachelcarmena;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.junit.BeforeClass;
import org.junit.Test;

public class AccountTest {

	private static Deposit deposit1;
	private static Deposit deposit2;
	private static Withdrawal withdrawal;

	@BeforeClass
	public static void beforeClass() {
		deposit1 = createDeposit("1000.00", 2012, Month.JANUARY, 10);
		deposit2 = createDeposit("2000.00", 2012, Month.JANUARY, 13);
		withdrawal = createWithdrawal("500.00", 2012, Month.JANUARY, 14);
	}

	@Test
	public void create_statement_with_one_deposit() {
		Account account = new Account();
		account.makeOperation(deposit1);

		assertEquals("date || credit || debit || balance" + "%n10/01/2012 || 1000.00 || || 1000.00",
				account.createStatement());
	}

	@Test
	public void create_statement_with_two_deposits() {
		Account account = new Account();
		account.makeOperation(deposit1);
		account.makeOperation(deposit2);

		assertEquals("date || credit || debit || balance" + "%n13/01/2012 || 2000.00 || || 3000.00"
				+ "%n10/01/2012 || 1000.00 || || 1000.00", account.createStatement());
	}

	@Test
	public void create_statement_with_two_deposits_and_withdrawal() {
		Account account = new Account();
		account.makeOperation(deposit1);
		account.makeOperation(deposit2);
		account.makeOperation(withdrawal);

		assertEquals(
				"date || credit || debit || balance" + "%n14/01/2012 || || 500.00 || 2500.00"
						+ "%n13/01/2012 || 2000.00 || || 3000.00%n10/01/2012 || 1000.00 || || 1000.00",
				account.createStatement());
	}

	private static Deposit createDeposit(String value, int year, Month month, int day) {
		Quantity quantity = new Quantity(value);
		LocalDate date = LocalDate.of(year, month, day);
		return new Deposit(quantity, date);
	}

	private static Withdrawal createWithdrawal(String value, int year, Month month, int day) {
		Quantity quantity = new Quantity(value);
		LocalDate date = LocalDate.of(year, month, day);
		return new Withdrawal(quantity, date);
	}
}
