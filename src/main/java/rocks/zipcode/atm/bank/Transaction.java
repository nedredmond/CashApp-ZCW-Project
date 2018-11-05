package rocks.zipcode.atm.bank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private String date;
    private String credit;
    private int amount;
    private int balance;

    public String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy HH:mm");
        Date now = new Date();
        String strDate = simpleDateFormat.format(now);
        return strDate;
    }

    public Transaction(String credit, int amount, int balance) {
        this.date = getCurrentTime();
        this.credit = credit;
        this.amount = amount;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
