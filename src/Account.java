public abstract class Account {
    protected int accountNumber;
    protected int balance;

    public Account(int accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    public void addBalance(int deposit){
        this.balance += deposit;
    }
    public int getBalance() {
        return balance;
    }
}
