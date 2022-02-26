package account;

public class CreditAccount extends Account {
    private final Integer fee;
    private final Integer limit;

    public CreditAccount(String id, Integer balance, String ownerId, Integer limit, Integer fee, Boolean isVerified) {
        super(id, balance, ownerId, null, isVerified);
        this.fee = fee;
        this.limit = limit;
    }

    public Integer getFee() {
        return fee;
    }

    public Integer getLimit() {
        return limit;
    }

    @Override
    public void windrow(int sum) throws Exception {
        if (balance - sum >= limit)
            super.windrow(sum);
        throw new Exception("credit account: limit exceeded");
    }
}
