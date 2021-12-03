package chewyt;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
//import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FixedDepositAccountTest {

    @Test
    public void gettingName() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        
        String expected = "ChewYT"; 
        String actual = account.getName();
        
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void gettingAccountNo() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",6666);
        
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
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",6666);
        
        //Checking if the balance is empty for 1st type of constructor.

        float expected = 6666 * (1+account.getInterest()); 
        float actual = account.getBalance();
        Assertions.assertEquals(expected, actual);
        
    }

    @Test
    public void gettingcreateDate() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",6666);
        
        //Checking if the create date is not empty

        LocalDate actual = account.getcreateDate();
        assertTrue(actual!=null); 
        
    }

    @Test
    public void gettingclosingDate() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        //Bank account not closed.
        //Checking if the closing date is not empty

        LocalDate actual = account.getclosingDate();
        assertTrue(actual==null); 
        
    }
    @Test
    public void checkSecondConstructor() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888,0.04f);
        //Bank account with initial funds\

        float expected = 0.04f; 
        float actual = account.getInterest();
        Assertions.assertEquals(expected, actual);
        
    }
    @Test
    public void checkThirdConstructor() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888,0.04f,12);
        //Bank account with initial funds\

        float expected = 12; 
        float actual = account.getDuration();
        Assertions.assertEquals(expected, actual);
        
    }
    @Test
    public void transactions1_checkBalancewithInterest() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",10000);
        //Bank account with initial funds
            
        float expected = 10000 * (1+account.getInterest()); 
        float actual = account.getBalance();

        Assertions.assertEquals(expected, actual);
        
    }
    @Test
    public void transactions2_CheckBalanceafterTransaction() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        //Bank account with initial funds
        //Check balance based on FD interest and not transactions
  
        boolean status1 = account.withdraw(1111); //NOP operation
        boolean status2 =account.deposit(2222);  //NOP operation
        boolean status3 =account.withdraw(3333); //NOP operation

        float expected = 8888*(1+account.getInterest()); 
        float actual = account.getBalance();
        boolean status4 = expected==actual;

        assertTrue(status1&&status2&&status3&&status4);
    }
    
    @Test
    public void transactions3_negatives() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        //Bank account with initial funds
        //Checking transaction status for negative amount

        boolean status1 = account.withdraw(-9000); // Transaction invalid
        boolean status2 = account.deposit(-1112); //  Transaction invalid
        
        System.out.println(status1);
        System.out.println(status2);
        assertFalse(status1 && status2); 
    }


    @Test
    public void cancelAccount() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        //Bank account with initial funds
        //Checking if closing account is successful for first and second round
        boolean firstcancel =  account.cancelAccount();
        boolean secondcancel =  account.cancelAccount();
        assertTrue(firstcancel&&!secondcancel); 
    }
    
    @Test
    public void transactions5_afterCancelAcc() {
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",8888);
        //Bank account with initial funds
        //Check computed transaction records
        
        account.cancelAccount();        
        
        assertFalse(account.withdraw(900)&&account.deposit(1112)); 
    }
}
