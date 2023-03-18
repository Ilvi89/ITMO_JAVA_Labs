package transactions;

import account.DebitAccount;
import bank.Bank;
import bank.CentralBank;
import client.Client;
import org.junit.jupiter.api.*;
import java.util.Calendar;
import java.util.Date;

public class TransactionTest {

    private  Bank bank;
    private  DebitAccount account1;
    private  DebitAccount account2;



    @BeforeEach
    public void setUp() throws Exception {
        CentralBank centralBank = new CentralBank();
        bank = centralBank.createBank();

        Client client1 = bank.createClient("Max", "V", "123431");
        Client client2 = bank.createClient("Tom", "U", "123111");

        account1 = bank.createDebitAccount(client1, 1000, 10);
        account2 = bank.createDebitAccount(client2, 1000, 10);
    }


    @Test
    public void testTransfer() throws Exception {

        new Transfer(account1, account2, 1000).execute();
        Assertions.assertEquals(0, account1.getBalance());
        Assertions.assertEquals(2000, account2.getBalance());
    }

    @Test
    public void testWithdraw() throws Exception {

        new Withdraw(account1, 500).execute();
        Assertions.assertEquals(500, account1.getBalance());

    }

    @Test
    public void testDeposit() {
        new Deposit(account1, 500).execute();
        Assertions.assertEquals(1500, account1.getBalance());
    }

    @Test
    public void testUndo() {
        new Deposit(account1, 500).execute();
        Assertions.assertEquals(1500, account1.getBalance());

        new Undo(account1).execute();
        Assertions.assertEquals(1000, account1.getBalance());
    }

    @Test
    public void testInterestAccrual() {
        int oldBalance = account1.getBalance();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Date datePlusDay = c.getTime();

        bank.setCurrentDate(datePlusDay);
        bank.calculateInterest();

        new InterestAccrual(account1).execute();
        new Deposit(account1, 500).execute();

        Assertions.assertEquals(1500, account1.getBalance());
    }



}
