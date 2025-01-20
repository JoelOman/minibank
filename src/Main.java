import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>(); // ArrayList för att spara alla användare
        boolean run = true;

        while (run) { // Startmeny & huvudloop
            System.out.println(""" 
                    ------MENY------
                    Tryck 1 för att logga in
                    Tryck 2 för att skapa konto
                    Tryck 3 för att avsluta""");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    login(scan, users); // Kallar på login-metoden. Skickar users-listan som argument
                    break;
                case 2:
                    createUser(scan, users); // Kallar på createUser-metoden --!!--
                    break;
                case 3:
                    System.out.println("Avslutar... ");
                    run = false; // Avbryter loopen och avslutar programmet
                    break;
                default:
                    System.out.println("Felaktig inmatning. Försök igen");
            }
        }
    }

    static void login(Scanner scan, ArrayList<User> users){ // Metod för att logga in. Tar listan med användare som parameter
        int loginAttempts = 0; // Räknare för inloggningsförsök
        while (loginAttempts < 3){ // Så länge färre försök än 3
            System.out.println("Ange personnummer: ");
            String persNumber = scan.next();
            System.out.println("Ange pinkod: ");
            String pin = scan.next();

            User loggedIn = null; // Deklarerar en Uservariabel, sätts till null då ingen matchande användare hittats än

            for(User user : users){ // Loopar igenom alla sparade användare & kollar om pers & pin matchar
                if(Objects.equals(user.getPersNumber(), persNumber) && Objects.equals(user.getPin(), pin)){ // Jämför om det inmatade pin- och personnumret matchar userobjektets
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
        String persNummer;
        System.out.println("Ange personnummer (sex siffror): ");
        while (true){ // Loop för att säkerställa att användaren matar in korrekt personnummer(sex siffror)
            persNummer = scan.next();
            if(persNummer.matches("\\d{6}")){ // Om personnumret är korrekt angett, dvs 6 siffror avbryts loopen
                break;
            }else {
                System.out.println("Felaktigt personnummer. Ange sex siffror(ååmmdd) ");
            }
        }
        String pin;
        System.out.println("Ange pinkod (fyra siffor): ");
        while (true){ // Loop för att säkerställa att användaren matar in korrekt pinkod
            pin = scan.next();
            if (pin.matches("\\d{4}")){ // Om pinkoden är korrekt angett. avbryts loopen
                break;
            }else {
                System.out.println("Felaktig pinkod. Ange fyra siffor. ");
            }
        }

        User user = new User(persNummer, pin); // Skapar en användare med pers- & pinnummer
        users.add(user); // Användaren sparas i users ArrayListen
        // Skriver ut att användaren skapas och med vilket kontonummer den fått.
        System.out.println("Användare skapad. Kontonummer: "+user.getAccountNumber() + " Saldo: "+user.getSavingsAccount().getBalance());
    }


    static void menu(Scanner scan, User user){  //Meny-metod som endast går att nås om användaren är inloggad
        boolean loggedIn = true;
        while (loggedIn){ // Loop som körs så länge loggedIn är true
            System.out.println("""
                    ----ANVÄNDARMENY---
                    Tryck 1 för att visa saldo
                    Tryck 2 för att sätta in pengar
                    Tryck 3 för att göra en överföring
                    Tryck 4 för att visa kontonummer
                    Tryck 5 för att logga ut
                    """);
            int choice = scan.nextInt();
            switch (choice){
                case 1: // Skriver ut användarens saldo
                    System.out.println("""
                            Välj konto:
                            1. Lönekonto
                            2. Sparkonto""");
                    int whichView = scan.nextInt();
                    if(whichView == 1){
                        System.out.println("Ditt saldo är: "+user.getSalaryAccount().getBalance()+ " kr ");
                    } else if(whichView == 2){
                        System.out.println("Ditt saldo är: "+user.getSavingsAccount().getBalance()+ " kr ");
                    }else {
                        System.out.println("Felaktigt val");
                    }
                    break;
                case 2: // Sätter in pengar genom att kalla på deposit-metoden med amount som argument
                    System.out.println("""
                            Välj konto:
                            1. Lönekonto
                            2. Sparkonto""");
                    int whichDepo = scan.nextInt();
                    System.out.println("Ange belopp att sätta in: ");
                    int amount = scan.nextInt();
                    if (whichDepo == 1){
                        user.getSalaryAccount().deposit(amount);
                    } else if (whichDepo == 2){
                        user.getSavingsAccount().deposit(amount);
                    }else {
                        System.out.println("Felaktig inmatning. ");
                    }
                    break;
                case 3: // För över från ett konto till ett annat
                    System.out.println("""
                            Välj konto att överföra från:
                            1. Lönekonto
                            2. Sparkonto""");
                    int whichTransfer = scan.nextInt();
                    System.out.println("Hur mycket vill du överföra? ");
                    int transferAmount = scan.nextInt();
                    if (whichTransfer == 1){ // Om lönekonto väljs används sparkontot & summan som argument när överföringsmetoden kallas på
                        user.getSalaryAccount().transfer(user.getSavingsAccount(), transferAmount);
                    }else if (whichTransfer == 2){ // Om sparkonto väljs används lönekonto & summan som argument när överföringsmetoden kallas på
                        user.getSavingsAccount().transfer(user.getSalaryAccount(), transferAmount);
                    }else {
                        System.out.println("Felaktig inmatning. ");
                    }
                    break;
                case 4:
                    int accNumber = user.getAccountNumber();
                    System.out.println("Ditt kontonummer är: "+accNumber);
                    break;
                case 5: // Loggar ut genom att sätta loggedIn till false
                    System.out.println("Loggar ut... ");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Felaktig inmatning. Försök igen ");
            }
        }
    }
}