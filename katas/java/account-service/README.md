# Bank kata

Create a simple bank application with the following features:

- Deposit into Account
- Withdraw from an Account
- Print a bank statement to the console

## Acceptance criteria

Statement should have the following the format:

```
  DATE       | AMOUNT  | BALANCE
  10/04/2014 | 500.00  | 1400.00
  02/04/2014 | -100.00 | 900.00
  01/04/2014 | 1000.00 | 1000.00
```

## Requirement

This class must be used:

```
public class AccountService {

  public void deposit(int amount);

  public void withdraw(int amount);

  public void printStatement();

}
```

## CRC Cards

[CRC cards](CRC-cards.pdf) from https://echeung.me/crcmaker/
