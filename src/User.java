public class User {
    final private int persNumber;
    private int pin;
    final private int accountNumber;
    private Savings savingsAccount;
    private Salary salaryAccount;

    public User(int persNumber, int pin){
        this.persNumber = persNumber;
        this.pin = pin;
        this.accountNumber = accNumber();
        this.savingsAccount = new Savings(accountNumber);
        this.salaryAccount = new Salary(accountNumber);

    }
    private int accNumber(){
        return (int) (Math.random() * 10000);
    }

    public int getPersNumber() {
        return persNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public Savings getSavingsAccount() {
        return savingsAccount;
    }

    public Salary getSalaryAccount() {
        return salaryAccount;
    }

}
