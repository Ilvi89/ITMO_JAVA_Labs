package bank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class CentralBank {
    private final List<Bank> banks = new ArrayList<>();

    public CentralBank() {
    }

    public Bank createBank() {
        Bank newBank = new Bank(UUID.randomUUID().toString(), new TimeProvider(Calendar.getInstance().getTime()));
        banks.add(newBank);
        return newBank;
    }

    public Bank getBank(String id) throws Exception {
        return banks.stream().filter(bank -> id.equals(bank.getId())).findFirst().orElseThrow(()
                -> new Exception("central bank: bank not found"));
    }
}
