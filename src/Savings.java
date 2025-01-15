public class Savings extends Account{

    public Savings(int accountNumber){
        super(accountNumber);
    }

    void deposit(int amount){
        addBalance(amount);
        System.out.println("Du la till "+amount+" kronor på kontot. Nuvarande saldo: "+getBalance());
    }
}
