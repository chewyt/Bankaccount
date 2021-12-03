package chewyt;

import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;  
import java.util.ArrayList;
import java.util.Random;

public class FixedDepositAccount {

    private final float interest;
    private final int duration;
    private final String name;                //final
    private final String accountNumber;       //FINAL
    private float accountBalance;
    private ArrayList<String> transactions;
    private LocalDate createDate;
    private LocalDate closingDate;
    
    Random random = new Random();

    FixedDepositAccount(String name, float initialBalance){
        //overloaded constructor
        this.name = name;
        int tempNumber = random.nextInt(99999998)+1;
        this.accountNumber = String.format("%08d",tempNumber);

        this.accountBalance = initialBalance;
        this.createDate = LocalDate.now();
        this.closingDate = null;
        this.transactions = new ArrayList<String>();
        this.interest = 0.03f;
        this.duration = 6;
    }

    FixedDepositAccount(String name, float initialBalance, float interest){
        //overloaded constructor
        this.name = name;
        int tempNumber = random.nextInt(99999998)+1;
        this.accountNumber = String.format("%08d",tempNumber);

        this.accountBalance = initialBalance;
        this.createDate = LocalDate.now();
        this.closingDate = null;
        this.transactions = new ArrayList<String>();
        this.interest = interest;
        this.duration = 6;
    }

    FixedDepositAccount(String name, float initialBalance, float interest, int duration){
        //overloaded constructor
        this.name = name;
        int tempNumber = random.nextInt(99999998)+1;
        this.accountNumber = String.format("%08d",tempNumber);

        this.accountBalance = initialBalance;
        this.createDate = LocalDate.now();
        this.closingDate = null;
        this.transactions = new ArrayList<String>();
        this.interest = interest;
        this.duration = duration;
    }
    //Getters

    public String getName(){
        return this.name;
    }
    public String getAccNo(){
        return this.accountNumber;
    }
    public float getBalance(){
        return this.accountBalance*(1+interest);
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
    public float getInterest(){
        return this.interest;
    }
    public int getDuration(){
        return this.duration;
    }
    
    //Setters

    public boolean deposit(double cash){
        if(cash > 0 && this.closingDate==null){
            System.out.println("Perform NOP operation");
            return true;            
        }else if (this.closingDate!=null){
            System.out.println("Account already closed.");
            return false;
        }
        else{
            System.out.println("Transaction invalid. Please enter positive amount.");
            return false;
        }
    }
    public boolean withdraw(double cash){
        if(cash > 0 && this.closingDate==null){
            System.out.println("Perform NOP operation");
            return true;            
        }else if (this.closingDate!=null){
            System.out.println("Account already closed.");
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
