public class Savings extends Account{

    public Savings(int accountNumber){
        super(accountNumber);
    }

    void deposit(int amount){ // Metod för att lägga in pengar, tar amount som parameter
        addBalance(amount); // Kallar på addBalance-metoden i superklassen, skickar amount som argument
        System.out.println("Du la till "+amount+" kronor på kontot. Nuvarande saldo: "+getBalance());
    }

}
