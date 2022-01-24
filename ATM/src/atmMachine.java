import java.util.Scanner;

public class atmMachine {

    Scanner sc = new Scanner(System.in);
    
    protected int twoThouCnt = 100;
    protected int fiveHundCnt = 100;
    protected int twoHundCnt = 100;
    protected int hundCnt = 100;

    protected int displayBal() {
        return (twoHundCnt*200) + (twoThouCnt*2000) + (fiveHundCnt*500) + (hundCnt*100);
    }

    protected void loadMoney(){
        
        System.out.println();
        System.out.print("Please Enter the number of Two Thousand Rupees Notes: ");
        twoThouCnt += sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Five Hundred Rupees Notes: ");
        fiveHundCnt += sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Two Hundred Rupees Notes: ");
        twoHundCnt += sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Hundred Rupees Notes: ");
        hundCnt += sc.nextInt();

        System.out.println();
        System.out.println("Money Added Successfully");
        System.out.println();
        System.out.println("Balace in ATM Machine: " + displayBal());

        
    }

    protected boolean amtAvail(Integer reqAmount) {
        return (displayBal() > reqAmount);
    }

    protected boolean dedMoney(int amt){
        if(amt > 2000){
            int n = amt/2000;
            twoThouCnt -= n;
            amt -= (2000*n); 
        }
        if(amt > 500){
            int n = amt/500;
            twoThouCnt -= n;
            amt -= (500*n); 
        }
        if(amt > 200){
            int n = amt/200;
            twoThouCnt -= n;
            amt -= (200*n); 
        }
        if(amt > 100){
            int n = amt/100;
            twoThouCnt -= n;
            amt -= (100*n); 
        }
        return true;
    }

}
