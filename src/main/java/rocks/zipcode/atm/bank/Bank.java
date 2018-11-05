package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Gulliver Grimwald", "ggrim@gmail.com", 500
        )));

        accounts.put(6666, new PremiumAccount(new AccountData(
                6666, "B. L. Zebub", "mephis@hotmail.com", 666
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Richard Ricardo", "rricardo@business.com", 20000
        )));

        accounts.put(0420, new BasicAccount(new AccountData(
                0420, "Guy Dudero", "lmfao4eva@ymail.com", 69
        )));

        accounts.put(9999, new PremiumAccount(new AccountData(
                9999, "Final Boss", "bosskey@gmail.com", 9999
        )));

        accounts.put(0001, new BasicAccount(new AccountData(
                0001, "Bill Busker", "bboy@yandex.com", 0
        )));
    }

    public ActionResult<AccountData> getAccountByPIN(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

//    public ActionResult<AccountData> getAccountByEmail(String email) {
//        Account account = accounts.get(email);
//
//        if (account != null) {
//            return ActionResult.success(account.getAccountData());
//        } else {
//            return ActionResult.fail("No account with email: " + email);
//        }
//    }

    public ActionResult<AccountData> getAccountByEmailAndPIN(String email, int pin) {
        Account account = accounts.get(pin);
        String accountEmail = account.getAccountData().getEmail();

        if (account != null && email.equals(accountEmail)) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Login failed. Please confirm your credentials and try again.");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean canWithdraw = account.withdraw(amount);

        if (canWithdraw) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
