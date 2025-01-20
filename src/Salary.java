public class Salary extends Account{
    public Salary(int accountNumber, int wage){
        super(accountNumber);
        this.balance = wage;
    }
    void deposit(int amount){ // Metod för att lägga in pengar, tar amount som parameter
        addBalance(amount); // Kallar på addBalance-metoden i superklassen, skickar amount som argument
        System.out.println("Du la till "+amount+" kronor på kontot. Nuvarande saldo: "+getBalance());
    }
}
