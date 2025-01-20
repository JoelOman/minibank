public class User {
    final private String persNumber;
    private String pin;
    final private int accountNumber;
    private Savings savingsAccount;
    private Salary salaryAccount;

    public User(String persNumber, String pin){
        this.persNumber = persNumber;
        this.pin = pin;
        this.accountNumber = accNumber(); // Kallar på slumpmetoden och sätter värdet av den till kontonummer
        this.savingsAccount = new Savings(accountNumber); // Skapar ett spar- och lönekonto när en användare skapas
        this.salaryAccount = new Salary(accountNumber, 50000); // Användarens kontonummer används som argument
    }
    private int accNumber(){ // Metod som skapar ett slumpat kontonummer med 4 siffror
        return (int) (Math.random() * 10000);
    }

    public String getPersNumber() {
        return persNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
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
