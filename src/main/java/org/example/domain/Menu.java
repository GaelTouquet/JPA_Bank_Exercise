package org.example.domain;

import org.example.model.Account;
import org.example.model.Agency;
import org.example.model.Client;
import org.example.services.BankService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Menu {

    static BankService bankService = new BankService();
    static Scanner scanner = new Scanner(System.in);

    static String askInput(String whatToAsk) {
        String outputString;
        while (true) {
            try {
                System.out.println(whatToAsk);
                outputString = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("should be a String!");
                continue;
            }
            return outputString;
        }
    }

    static long askLong(String whatToAsk) {
        long outputLong;
        while (true) {
            try {
                System.out.println(whatToAsk);
                outputLong = Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("should be a long!");
                continue;
            }
            return outputLong;
        }
    }

    static double askDouble(String whatToAsk) {
        double outputDouble;
        while (true) {
            try {
                System.out.println(whatToAsk);
                outputDouble = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("should be a double!");
                continue;
            }
            return outputDouble;
        }
    }

    static int askInt(String whatToAsk) {
        int outputInt;
        while (true) {
            try {
                System.out.println(whatToAsk);
                outputInt = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("should be a int!");
                continue;
            }
            return outputInt;
        }
    }

    static boolean askYesNo(String whatToAsk) {
        ArrayList<String> yesList = new ArrayList<>(Arrays.asList("Yes", "Y", "y", "yes"));
        ArrayList<String> noList = new ArrayList<>(Arrays.asList("No", "N", "n", "no"));
        String inputString;
        boolean isYes;
        do {
            System.out.println(whatToAsk);
            System.out.println("answer must be Yes/Y/yes/y or No/N/no/n ");
            inputString = scanner.nextLine();
            isYes = yesList.contains(inputString);
        } while ((!isYes) && (!noList.contains(inputString)));
        return isYes;
    }

    static public void mainMenu() {
        int choice;
        do {
            bankService.begin();
            String whatToAsk = "";
            whatToAsk += "***Main menu***\ntype the number of what you would like to do:\n";
            whatToAsk += "1) create client\n";
            whatToAsk += "2) create agency\n";
            whatToAsk += "3) create account\n";
            whatToAsk += "4) link account\n";
            whatToAsk += "5) see existing entities\n";
            whatToAsk += "0) take the french leave\n";
            choice = askInt(whatToAsk);
            switch (choice) {
                case 1 -> createClient();
                case 2 -> createAgency();
                case 3 -> createAccount();
                case 4 -> linkAccount();
                case 5 -> showEntities();
            }
            bankService.send();
        }while (choice!=0);
        bankService.close();
    }

    static public void createClient() {
        Client client = new Client();
        client.setNom(askInput("Client name"));
        client.setPrenom(askInput("Client first name"));
        Date bd;
        while (true) {
            try {
                bd = new SimpleDateFormat("dd/MM/yyyy").parse(askInput("Client date of birth"));
            } catch (ParseException e) {
                System.out.println(e.getMessage());
                continue;
            }
            break;
        }
        client.setBirthDate(bd);
        while (askYesNo("Would you like to add an existing account?")) {
            System.out.println("available Accounts");
            showAccountList();
            Account foundAccount = bankService.findAccountById(askLong("give account id"));
            client.getAccountList().add(foundAccount);
            foundAccount.getClientList().add(client);
        }
        bankService.createClient(client);
    }

    static public void createAccount() {
        Account account = new Account();
        account.setLabel(askInput("Account Label"));
        account.setIban(askInput("IBAN"));
        account.setCredit(askDouble("credit balance"));
        if (askYesNo("Would you like to set the agency?")) {
            System.out.println("available Agencies");
            showAgencyList();
            account.setAgency(bankService.findAgencyById(askLong("give agency id")));
        }
        while (askYesNo("Would you like to add an existing client?")) {
            System.out.println("available Clients");
            showClientList();
            Client foundCLient = bankService.findClientById(askLong("give client id"));
            foundCLient.getAccountList().add(account);
            account.getClientList().add(foundCLient);
        }
        bankService.createAccount(account);
    }

    static public void createAgency() {
        Agency agency = new Agency();
        agency.setAddress(askInput("agency address"));
        bankService.createAgency(agency);
    }

    static public void linkAccount() {
        System.out.println("available Accounts");
        showAccountList();
        linkAccount(askLong("account id"));
    }

    static public void linkAccount(Long id) {
        Account account = bankService.findAccountById(id);
        if (askYesNo("Would you like to set the agency?")) {
            System.out.println("available Agencies");
            showAgencyList();
            account.setAgency(bankService.findAgencyById(askLong("give agency id")));
        }
        while (askYesNo("Would you like to add an existing client?")) {
            System.out.println("available Clients");
            showClientList();
            Client foundCLient = bankService.findClientById(askLong("give client id"));
            foundCLient.getAccountList().add(account);
            account.getClientList().add(foundCLient);
            bankService.updateClient(foundCLient);
        }
        bankService.updateAccount(account);
    }

    static public void showEntities() {
        int choice;
        do {
            String whatToAsk = "";
            whatToAsk += "***Entity menu***\ntype the number of what you would like to do:\n";
            whatToAsk += "1) client\n";
            whatToAsk += "2) agency\n";
            whatToAsk += "3) account\n";
            whatToAsk += "0) take me back to the good old main menu!\n";
            choice = askInt(whatToAsk);
            switch (choice) {
                case 1 -> showClientList();
                case 2 -> showAgencyList();
                case 3 -> showAccountList();
            }
        }while (choice!=0);
    }

    public static void showClientList() {
        List<Client> clientList = bankService.findAllClient();
        for (Client client: clientList
             ) {
            System.out.println(client);
        }
    }

    public static void showAccountList() {
        List<Account> AccountList = bankService.findAllAccount();
        for (Account account: AccountList
        ) {
            System.out.println(account);
        }
    }

    public static void showAgencyList() {
        List<Agency> AgencyList = bankService.findAllAgency();
        for (Agency agency: AgencyList
        ) {
            System.out.println(agency);
        }
    }
}
