package bank;

import account.Account;
import account.CreditAccount;
import account.DebitAccount;
import client.Client;
import transactions.InterestAccrual;

import java.util.*;

public class Bank {
    private final List<Client> clients = new ArrayList<>();
    private final List<Account> accounts = new ArrayList<>();
    private final String id;
    private final TimeProvider timeProvider;

    protected Bank(String id, TimeProvider timeProvider) {
        this.id = id;
        this.timeProvider = timeProvider;
    }

    public void setCurrentDate(Date date) {
        timeProvider.setCurrentDate(date);
    }

    public String getId() {
        return id;
    }

    //    Todo: default param - password
    public Client createClient(String name, String surname, String passport) {
        Client client = new Client(UUID.randomUUID().toString(), name, surname, passport);
        addClient(client);
        return client;
    }

    public Client createClient(String name, String surname) {
        Client client = new Client(UUID.randomUUID().toString(), name, surname);
        addClient(client);
        return client;
    }

    private void addClient(Client client) {
        if (clients.contains(client)) throw new RuntimeException("bank: client exist");
        clients.add(client);
    }

    private void addAccount(Account account) throws Exception {
        if (accounts.stream().anyMatch(client -> account.getOwnerId().equals(client.getId())))
            throw new Exception("bank: client not exist");
        accounts.add(account);
    }


    public DebitAccount createDebitAccount(Client client, int balance, float interestOnBalance) throws Exception {
        DebitAccount account = new DebitAccount(
                UUID.randomUUID().toString(), balance, client.getId(), interestOnBalance, client.getPassport() != null);
        addAccount(account);
        return account;
    }

    public CreditAccount createCreditAccount(Client client, int balance, int limit, int fee) throws Exception {
        CreditAccount account = new CreditAccount(
                UUID.randomUUID().toString(), balance, client.getId(), limit, fee, client.getPassport() != null);
        addAccount(account);
        return account;
    }

    public void calculateInterest() {
        for (Account a : accounts) {
            a.calculateInterest(timeProvider.getCurrentDate());
        }
    }

    protected void accrueInterest(Account account) {
        new InterestAccrual(account);
    }

    protected void accrueInterest() {
        for (Account a : accounts) {
            accrueInterest(a);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id.equals(bank.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
