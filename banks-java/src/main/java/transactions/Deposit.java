package transactions;

import account.Account;

public class Deposit extends Transaction {
    protected Account source;
    protected Integer sum;

    public Deposit(Account source, Integer sum) {
        this.source = source;
        this.sum = sum;
    }

    @Override
    public void execute()  {
        source.backup();
        source.deposit(sum);
    }
}
