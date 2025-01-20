public abstract class Account {
    protected int accountNumber;
    protected int balance;

    public Account(int accountNumber){
        this.accountNumber = accountNumber;
        this.balance = 0; // Kontot skapas med 0 i saldo
    }

    public void addBalance(int deposit){
        this.balance += deposit;
    }
    public int getBalance() {
        return balance;
    }

    // Metod för att överföra. Tar typ av konto & summa som parametrar
    public void transfer(Account targetAccount, int amount){
        if(this.balance >= amount){ // Om saldot är minst lika stort som summan
            this.balance -= amount; // minskar saldot med den överförda summan.
            targetAccount.addBalance(amount); // Kontot som ska överföras till får den valda summan.
            System.out.println("Du överförde "+amount+" kr"); // (Kolla överföringsdelen i användarmenyn för mer info, rad 142 i main)
        } else {
            System.out.println("Du har ej nog med pengar på detta konto. ");
        }
    }
}
