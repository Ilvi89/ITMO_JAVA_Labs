package transactions;

import account.Account;

public class Withdraw extends Transaction {
    protected Account from;
    protected Integer sum;

    public Withdraw(Account from, Integer sum) {
        this.from = from;
        this.sum = sum;
    }

    @Override
    public void execute() throws Exception {
        next = new WithdrawCredit(from, sum);
        next.setNext(new WithdrawDebit(from, sum));
        next.execute();
    }
}
