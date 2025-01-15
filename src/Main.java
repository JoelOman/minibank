import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>(); // ArrayList för att spara alla users
        boolean run = true;

        while (run) {
            System.out.println("""
                    ------MENY------
                    Tryck 1 för att logga in
                    Tryck 2 för att skapa konto
                    Tryck 3 för att avsluta""");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    login(scan, users);
                    break;
                case 2:
                    createUser(scan, users); // Kallar på createUser-metoden
                    break;
                case 3:
                    System.out.println("Avslutar... ");
                    run = false;
                    break;
                default:
                    System.out.println("Felaktig inmatning. Försök igen");
            }
        }

    }

    static void login(Scanner scan, ArrayList<User> users){
        int loginAttempts = 0; // Räknare för inloggningsförsök

        while (loginAttempts < 3){ // Så länge färre försök än 3
            System.out.println("Ange personnummer: ");
            int persNummer = scan.nextInt();
            System.out.println("Ange pinkod: ");
            int pin = scan.nextInt();

            // Kolla om persnummer & pinkod matchar en användare
            User loggedIn = null; // Uservariabel loggedIn, sätts till null då ingen matchande användare hittats

            for(User user : users){ // Loopar igenom alla sparade användare
                if(user.getPersNumber() == persNummer && user.getPin() == pin){
                    loggedIn = user; // Om en matchande användare hittas, sätts värdet av loggedIn till den matchande användaren
                    break;
                }
            }

                if(loggedIn != null){ // Om loggedIn ej längre är null, dvs en matchande användare hittats
                    System.out.println("Välkommen! ");
                    menu(scan, loggedIn); // Användaren skickas vidare till användarmenyn
                    break;
                }
                else {
                    loginAttempts++; // Värdet på räknaren ökar med 1 vid fel uppgifter
                    System.out.println("Felaktiga uppgifter. Försök igen. ");
                    if(loginAttempts == 3){ // Vid 3 felaktiga försök avslutas programmet
                        System.out.println("För många felaktiga försök. ");
                        System.exit(0);
                    }
                }
            }

        }


    static void createUser(Scanner scan, ArrayList<User> users){
        System.out.println("Ange personnummer (sex siffror): ");
        int persNummer = scan.nextInt();
        System.out.println("Ange pinkod: ");
        int pin = scan.nextInt();
        User user = new User(persNummer, pin);
        users.add(user);
        System.out.println("Användare skapad. Kontonummer: "+user.getAccountNumber() + " Saldo: "+user.getSavingsAccount().getBalance());
    }

    static void menu(Scanner scan, User user){
        boolean loggedIn = true;
        while (loggedIn){
            System.out.println("""
                    ----ANVÄNDARMENY---
                    Tryck 1 för att visa saldo
                    Tryck 2 för att sätta in pengar
                    Tryck 3 för att överföra
                    Tryck 4 för att logga ut
                    """);
            int choice = scan.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Ditt saldo är: "+user.getSavingsAccount().getBalance()+ " kr");
                    break;
                case 2:
                    System.out.println("Ange belopp att sätta in: ");
                    int amount = scan.nextInt();
                    user.getSavingsAccount().deposit(amount);
                case 3:
                    break;
                case 4:
                    System.out.println("Loggar ut...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Felaktig inmatning. Försök igen");
            }
        }
    }
}