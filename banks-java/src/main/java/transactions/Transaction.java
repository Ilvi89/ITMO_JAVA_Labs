package transactions;

public abstract class Transaction {
    protected Transaction next;

    public void setNext(Transaction next) {
        this.next = next;
    }

    public abstract void execute() throws Exception;
}