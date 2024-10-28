package banking;
import DataStructuresLib.MyTreeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.HashMap;

public class Bank {
    HashMap<String,Double> balances;

    public Bank(File startingBalancesFile) throws FileNotFoundException{
        Scanner sc = new Scanner(startingBalancesFile);
        sc.nextLine(); // pass the header
        String line;
        String[] data;
        balances = new HashMap<String,Double>();
        while(sc.hasNextLine()){
            line = sc.nextLine();
            // an array of length 2: name, balance
            data = line.split(",");
            String name = data[0];
            Double balance = Double.parseDouble(data[1]);
            balances.put(name, balance);
        }
    }

    public void processTransactions(File transactionsFile) throws FileNotFoundException{
        Scanner sc = new Scanner(transactionsFile);
        sc.nextLine(); // pass the header
        String line, payor, payee;
        Double amount, payorBalance, payeeBalance;
        String[] data;
        while(sc.hasNextLine()){
            try{
                line = sc.nextLine();
                // an array of length 4: id, payor, payee, amount
                data = line.split(",");
                payor = data[1];
                payee = data[2];
                amount = Double.parseDouble(data[3]);
            }catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
                continue;
            }
            // get the payor's balance
            payorBalance = balances.get(payor);
            // set the payor's balance to the previous balance - amount
            balances.put(payor, payorBalance - amount);
            // get the payee's balance
            payeeBalance = balances.get(payee);
            // set the payee's balance to the previous balance + amount
            balances.put(payee, payeeBalance + amount);
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
            Double balance;
            do{
                System.out.println("Whose balance would you like to check?");
                name = input.nextLine().strip();
                if(!name.equals("exit")){
                    if(myBank.balances.containsKey(name)){
                        balance = myBank.balances.get(name);
                        System.out.println("Your balance is: " + balance);
                    }
                    else System.out.println("Username not found");
                }
            } while(!name.equals("exit"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
