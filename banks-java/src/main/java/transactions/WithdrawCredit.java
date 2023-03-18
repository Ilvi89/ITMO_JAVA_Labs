package transactions;

import account.Account;
import account.CreditAccount;

public class WithdrawCredit extends Withdraw {
    protected WithdrawCredit(Account from, Integer sum) {
        super(from, sum);
    }

    @Override
    public void execute() throws Exception {
        if (from instanceof CreditAccount creditAccount) {
            if (creditAccount.getBalance() - sum < -creditAccount.getLimit())
                throw new Exception("credit withdraw: credit limit exceeded");
            if (!creditAccount.isVerified() && sum > 1000)
                throw new Exception("credit withdraw: withdraw from unverified account must be less 1000");
            from.backup();
            from.windrow(sum);
        } else next.execute();

    }
}
