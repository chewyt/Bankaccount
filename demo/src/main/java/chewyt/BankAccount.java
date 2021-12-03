package chewyt;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
import java.util.Random;

public class BankAccount {
    private final String name;                //final
    private final String accountNumber;       //FINAL
    private float accountBalance;
    private ArrayList<String> transactions;
    private LocalDate createDate;
    private LocalDate closingDate;
    
    Random random = new Random();

    BankAccount(String name){
        //constructor
        this.name = name;
        int tempNumber = random.nextInt(100000000)+1;
        this.accountNumber = String.format("%08d",tempNumber);
        this.accountBalance = 0;
        this.createDate = LocalDate.now();
        this.closingDate = null;
        this.transactions = new ArrayList<String>();
    }
    BankAccount(String name, float initialBalance){
        //overloaded constructor
        this.name = name;
        int tempNumber = random.nextInt(99999998)+1;
        this.accountNumber = String.format("%08d",tempNumber);

        this.accountBalance = initialBalance;
        this.createDate = LocalDate.now();
        this.closingDate = null;
        this.transactions = new ArrayList<String>();
    }
    //Getters

    public String getName(){
        return this.name;
    }
    public String getAccNo(){
        return this.accountNumber;
    }
    public float getBalance(){
        return this.accountBalance;
    }
    public LocalDate getcreateDate(){
        return this.createDate;
    }
    public LocalDate getclosingDate(){
        if (this.closingDate == null) {
            System.out.println("The account is active");
        }
        return this.closingDate;
    }
    
    //Setters

    public boolean deposit(double cash){
        if (cash >  0 && this.closingDate==null){
            this.accountBalance += cash;
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
            String formatDateTime = now.format(format);  
            String transactionLine  =String.format("deposit $%.2f at %s", cash,formatDateTime);
            transactions.add(transactionLine);
            return true;
        } 
        else if (this.closingDate!=null){
            System.out.println("Account already closed.");
            return false;
        }
        else{
            System.out.println("Transaction invalid. Please enter positive amount.");
            return false;
        }    
    }
    public boolean withdraw(double cash){
        if (cash >  0 && cash < this.accountBalance && this.closingDate==null) {
            this.accountBalance -= cash;
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
            String formatDateTime = now.format(format);
            String transactionLine  =String.format("withdraw $%.2f at %s", cash,formatDateTime);
            transactions.add(transactionLine);
            return true;
        }
        else if (this.closingDate!=null){
            System.out.println("Account already closed.");
            return false;
        }
        
        else if (cash > this.accountBalance){
            System.out.println("Transaction invalid. Balance lower than amount to be withdrawn.");
            return false;
        }

        else{
            System.out.println("Transaction invalid. Please enter positive amount.");
            return false;
        }
    }
    public ArrayList<String> getTransactions(){
        System.out.println("Transactions");
        System.out.println("-------------------------------------");
        for(String i : this.transactions){
            System.out.println(i);
        }
        return this.transactions;
    }    

    public boolean cancelAccount(){

        if(this.closingDate ==null){
        this.closingDate = LocalDate.now();
        return true;
        }
        else{
            System.out.println("Account already closed");
            return false;
        }

    }
}
