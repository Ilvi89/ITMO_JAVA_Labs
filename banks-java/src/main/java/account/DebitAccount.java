package account;

public class DebitAccount extends Account {
    public DebitAccount(String id, Integer balance, String ownerId, Float interestOnBalance, Boolean isVerified) {
        super(id, balance, ownerId, interestOnBalance, isVerified);
    }
}
