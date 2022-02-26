package memento;

import account.Account;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private final Account account;
    private final List<BalanceSnapshot> mementos;

    public Caretaker(Account account) {
        this.account = account;
        this.mementos = new ArrayList<>();
    }

    public void backup() {
        mementos.add(account.createBalanceSnapshot());
    }

    public void undo() {
        if (mementos.size() == 0) return;

        BalanceSnapshot memento = mementos.get(mementos.size() - 1);
        mementos.remove(memento);
        account.restore(memento);
    }
}
