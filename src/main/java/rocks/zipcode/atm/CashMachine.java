package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;

    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    public final static CashMachine INSTANCE = new CashMachine(new Bank());

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    private boolean errorExists = false;
    private String errorMessage = null;

//    public void login(String email) {
//        tryCall(
//                () -> bank.getAccountByEmail(email),
//                update
//        );
//    }

    public void login(int pin) {
        tryCall(
                () -> bank.getAccountByPIN(pin),
                update
        );
    }

    public void login(String email, int pin) {
        tryCall(
                () -> bank.getAccountByEmailAndPIN(email,pin),
                update
        );
    }

    public void deposit(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(int amount) {
        if (accountData != null) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void logOut() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
        String displayString;
        if (errorExists) {
            displayString = errorMessage;
        } else {
            displayString = accountData != null ? accountData.toString() : "Login failed. Please confirm your credentials and try again.";
        }
        return displayString;
    }

    public <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                errorExists = false;
                T data = result.getData();
                postAction.accept(data);
            } else {
                errorExists = true;
                errorMessage = result.getErrorMessage();
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public int getBalance() {
        return accountData.getBalance();
    }
}