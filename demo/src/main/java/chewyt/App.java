package chewyt;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! Welcome to Chew Bank" );
        FixedDepositAccount account = new FixedDepositAccount("ChewYT",800);
 
        System.out.println("Account: "+account.getAccNo());
        // account.withdraw(-10);
        //account.deposit(100);
        //account.withdraw(50);
        account.deposit(800);
        System.out.println(account.deposit(-800));
        System.out.println();

        account.getTransactions(); 
        System.out.println();
        account.cancelAccount();
        account.withdraw(900); 
        account.deposit(1112); 
        account.deposit(200);
        account.withdraw(50);
    }
}
