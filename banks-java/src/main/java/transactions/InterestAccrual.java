package transactions;

import account.Account;

import java.util.Date;

public class InterestAccrual extends Transaction {
    private final Account account;

    public InterestAccrual(Account account) {
        this.account = account;
    }

    @Override
    public void execute() {
        account.deposit(account.getCurrentInterestOnBalance());
        account.backup();
    }
}
