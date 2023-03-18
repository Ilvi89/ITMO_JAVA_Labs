package bank;

import account.DebitAccount;
import bank.Bank;
import bank.CentralBank;
import client.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class BankTest {
    private static Bank bank;

    @BeforeAll
    public static void setUp(){
        CentralBank centralBank = new CentralBank();
        bank = centralBank.createBank();
    }


    @Test
    public void testAccountVerification() throws Exception {
        Client client1 = bank.createClient("Max", "V", "123431");
        Client client2 = bank.createClient("Tom", "U");
        DebitAccount verifiedDebitAccount = bank.createDebitAccount(client1, 1000, 10);
        DebitAccount unverifiedDebitAccount = bank.createDebitAccount(client2, 1000, 10);

        Assertions.assertEquals(true, verifiedDebitAccount.isVerified());
        Assertions.assertEquals(false, unverifiedDebitAccount.isVerified());
    }


}
