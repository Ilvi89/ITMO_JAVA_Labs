package transactions;

import account.Account;

public class Undo extends Transaction {
    protected Account account;

    public Undo(Account account) {
        this.account = account;
    }

    @Override
    public void execute() {
        account.undo();
    }
}
