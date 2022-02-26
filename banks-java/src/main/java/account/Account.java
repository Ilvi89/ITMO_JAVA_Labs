package account;

import memento.BalanceSnapshot;
import memento.Caretaker;

import java.util.Date;

public abstract class Account {

    private final Caretaker caretaker = new Caretaker(this);
    protected String id;
    protected Integer balance;
    protected String ownerId;
    protected Float interestOnBalance;
    protected Boolean isVerified;
    protected Date lastInterestCharge;
    protected Integer currentInterestOnBalance;

    protected Account(String id, Integer balance, String ownerId, Float interestOnBalance, Boolean isVerified) {
        this.id = id;
        this.balance = balance;
        this.ownerId = ownerId;
        this.interestOnBalance = interestOnBalance;
        this.isVerified = isVerified;

        this.currentInterestOnBalance = 0;
    }

    public Integer getBalance() {
        return balance;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public String getId() {
        return id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public Integer getCurrentInterestOnBalance() {
        return currentInterestOnBalance;
    }

    public void windrow(int sum) throws Exception {
        balance -= sum;
    }


    public void deposit(int sum) {
        balance += sum;
    }

    // https://www.raiffeisen.ru/wiki/kak-rasschitat-procenty-po-vkladu/
    public void calculateInterest(Date updateDate) {
        int count = lastInterestCharge != null ? lastInterestCharge.compareTo(updateDate) : 0;
        currentInterestOnBalance += Math.round(interestOnBalance / 365 * balance * (count) / 100);
        lastInterestCharge = updateDate;
    }

    public BalanceSnapshot createBalanceSnapshot() {
        return new BalanceSnapshot(balance);
    }

    public void restore(BalanceSnapshot memento) {
        balance = memento.getState();
    }


    public void undo() {
        caretaker.undo();
    }

    public void backup() {
        caretaker.backup();
    }
}
