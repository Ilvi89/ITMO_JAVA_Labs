package memento;

public class BalanceSnapshot implements Snapshot<Integer> {
    private final int balance;

    public BalanceSnapshot(int balance) {
        this.balance = balance;
    }

    public Integer getState() {
        return balance;
    }
}
