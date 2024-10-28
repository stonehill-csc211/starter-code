package banking;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import DataStructuresLib.MyTreeMap;

public class Bank {
    MyTreeMap<String,Double> balances;

    public Bank(File startingBalancesFile) throws FileNotFoundException{
        Scanner sc = new Scanner(startingBalancesFile);
        sc.nextLine(); // pass the header
        String line;
        String[] data;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            // an array of length 2: name, balance
            data = line.split(",");
            // TODO figure out how to deal with this data and add it to balances
        }
    }

    public void processTransactions(File transactionsFile) throws FileNotFoundException{
        Scanner sc = new Scanner(transactionsFile);
        sc.nextLine(); // pass the header
        String line;
        String[] data;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            // an array of length 4: id, payor, payee, amount
            data = line.split(",");
            // TODO figure out how to deal with this data and change balances
        }
    }

    public static void main(String[] args){
        try {
            Bank myBank = new Bank(new File("banking/starting_balances.csv"));
            System.out.println("Processing transactions...");
            long startTime = System.nanoTime();
            myBank.processTransactions(new File("banking/transactions.csv"));
            long endTime = System.nanoTime();
            System.out.println("Finished. Processing transactions took " 
                                + (endTime - startTime) * 1e-9 + " seconds");
            Scanner input = new Scanner(System.in);
            String name;
            do{
                System.out.println("Whose balance would you like to check?");
                name = input.nextLine();
                if(name != "exit"){
                    // TODO: print the balance
                }
            } while(name != "exit");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
