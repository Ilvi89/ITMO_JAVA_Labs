package transactions;

import account.Account;
import account.DebitAccount;

public class WithdrawDebit extends Withdraw {
    public WithdrawDebit(Account from, Integer sum) {
        super(from, sum);
    }

    @Override
    public void execute() throws Exception {
        if (from instanceof DebitAccount debitAccount) {
            if (sum > debitAccount.getBalance())
                throw new Exception("debit withdraw: balance in negative");
            if (!debitAccount.isVerified() && sum > 1000)
                throw new Exception("debit windrow: withdraw from unverified account must be less 1000");

            from.backup();
            from.windrow(sum);
        } else next.execute();
    }
}
