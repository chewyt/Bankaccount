package chewyt;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BankAccountTest {

    @Test
    public void gettingName() {
        BankAccount account = new BankAccount("ChewYT");
        
        String expected = "ChewYT"; 
        String actual = account.getName();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void gettingAccountNo() {
        BankAccount account = new BankAccount("ChewYT");
        
        //Checking if there is a auto generated number.

        String accNo = account.getAccNo();
        boolean isNumber  = true;
        try{
            int accNo_convert = Integer.parseInt(accNo);
            System.out.println(accNo_convert);  
        }
        catch(NumberFormatException e){
            isNumber  = false;
        }
        
        System.out.println(accNo.length());  
            
        assertTrue(accNo.length()==8 && isNumber); // No of digits in the account number
        
    }
    
    @Test
    public void gettingBalance() {
        BankAccount account = new BankAccount("ChewYT");
        
        //Checking if the balance is empty for 1st type of constructor.

        float expected = 0; 
        float actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
        
    }

    @Test
    public void gettingcreateDate() {
        BankAccount account = new BankAccount("ChewYT");
        
        //Checking if the create date is not empty

        LocalDate actual = account.getcreateDate();
        assertTrue(actual!=null); 
        
    }

    @Test
    public void gettingclosingDate() {
        BankAccount account = new BankAccount("ChewYT");
        //Bank account not closed.
        //Checking if the closing date is not empty

        LocalDate actual = account.getclosingDate();
        assertTrue(actual==null); 
        
    }
    @Test
    public void checkSecondConstructor() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds\

        float expected = 8888; 
        float actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
        
    }
    @Test
    public void transactions1_normal() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
  
        account.withdraw(1111);
        account.deposit(2222);
        account.withdraw(3333);
        float expected = 6666; 
        float actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
        
    }
    @Test
    public void transactions2_overwithdraw() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
    
        boolean status1 = account.withdraw(9000); // Transaction invalid
        boolean status2 = account.deposit(1112); //  Transaction Valid
        
        float expected = 10000; 
        float actual = account.getBalance();

        boolean status3 = expected == actual; // Checking expected balance
 
        assertTrue(status1==false&&status2==true&&status3==true); 
        
    }
    @Test
    public void transactions3_negatives() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
        //Checking transaction status for negative amount

        boolean status1 = account.withdraw(-9000); // Transaction invalid
        boolean status2 = account.deposit(-1112); //  Transaction invalid
        
        float expected = 8888; 
        float actual = account.getBalance();

        boolean status3 = expected == actual; // Checking expected balance
 
        assertTrue(status1==false&&status2==false&&status3==true); 
    }

    @Test
    public void transactions4_record() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
        //Check computed transaction records

        account.withdraw(900); 
        account.deposit(1112); 
        account.deposit(200);
        account.withdraw(50);
        ArrayList<String> Transacation = account.getTransactions();
        boolean numrecords = Transacation.size()==4;
        
        assertTrue(numrecords); 
    }

    @Test
    public void cancelAccount() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
        //Checking if closing account is successful for first and second round
        boolean firstcancel =  account.cancelAccount();
        boolean secondcancel =  account.cancelAccount();
        assertTrue(firstcancel==true&&secondcancel==false); 
    }
    
    @Test
    public void transactions5_afterCancelAcc() {
        BankAccount account = new BankAccount("ChewYT",8888);
        //Bank account with initial funds
        //Check computed transaction records

        account.withdraw(900); 
        account.deposit(1112); 
        account.deposit(200);
        account.withdraw(50);
        account.cancelAccount();
        
        assertFalse(account.withdraw(900)&&account.deposit(1112)); 
    }
}
