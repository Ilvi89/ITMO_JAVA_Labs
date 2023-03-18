package transactions;

import account.Account;

public class Transfer extends Transaction {
    protected Account sender;
    protected Account recipient;
    protected Integer sum;

    public Transfer(Account sender, Account recipient, Integer sum) {
        this.sender = sender;
        this.recipient = recipient;
        this.sum = sum;
    }

    @Override
    public void execute() throws Exception {
        next = new Withdraw(sender, sum);
        next.execute();
        next = new Deposit(recipient, sum);
        next.execute();
    }
}
