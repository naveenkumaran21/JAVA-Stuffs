import java.util.*;

public class Functions extends atmMachine{

    atmMachine obj = new atmMachine();

    Scanner sc = new Scanner(System.in);

    //User Functions

    //Stores User Data
    protected static List<HashMap<String, String>> usrDet = new ArrayList<HashMap<String, String>>();

    //Stores User Data if found
    HashMap<String, String> fndData = new HashMap<>();

    public void cls() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    //update user data
    final void updateUsrDet(){
        HashMap<String, String> usr1 = new HashMap<>();
        HashMap<String, String> usr2 = new HashMap<>();
        
        usr1.put("UserName", "Ram");
        usr1.put("Pin", "1234");
        usr1.put("BankName", "NK");
        usr1.put("Balance", "100000");
        usr1.put("transCnt", "0");
        usr1.put("Statement", "");

        usrDet.add(usr1);

        usr2.put("UserName", "Naveen");
        usr2.put("Pin", "1234");
        usr2.put("BankName", "NK");
        usr2.put("Balance", "100000");
        usr2.put("transCnt", "0");
        usr2.put("Statement", "");

        usrDet.add(usr2);
    }

    //To know it's position in list
    int pos;

    //check User Name Exists or Not
    protected HashMap<String, String> usrCheck(String usrName) {
        
        //Stores the status
        fndData.put("Status", "false"); 
        pos = -1;

        for (HashMap<String, String> hashMap : usrDet) {
            pos++;
            if(hashMap.get("UserName").equals(usrName)){
                for(String str: hashMap.keySet()){
                    fndData.put(str, hashMap.get(str));
                } //Storing found user data 
                fndData.replace("Status", "true");
            }
        }
        
        return fndData;
    }

    //check pin number
    protected boolean authentication(HashMap<String, String> usrDet, String pin) {  

        if(usrDet.get("Pin").equals(pin)){
            return true;
        }else{
            return false;
        }
    }

    protected void withdrawAmount(){
        
        System.out.println();
        System.out.print("Enter the Amount to be withdrawn: ");
        int amt = sc.nextInt();
        int usrBal = Integer.parseInt( usrDet.get(pos).get("Balance") );
        System.out.println();

        if((usrBal > amt) && obj.amtAvail(amt) && obj.dedMoney(amt) && (amt%100 == 0)){
            System.out.println("Withdraw Successful");
            usrDet.get(pos).replace("Balance", String.valueOf(usrBal-amt));
            addStatement("Debit " + amt + "  " + "Balance " + (usrBal-amt) );
        }else{
            System.out.println("Entered Amount not available");
        }
    }

    protected String checkBalance() {
        return usrDet.get(pos).get("Balance");
    }

    protected boolean pinChange(String newPin, String usrName) {

        usrDet.get(pos).replace("Pin", newPin);
        return true;
    }

    protected void miniStatement() {
        System.out.println(usrDet.get(pos).get("Statement"));
        System.out.println();
    }

    protected void addStatement(String trans){
        usrDet.get(pos).replace( "Statement", (usrDet.get(pos).get("Statement") + "\n" + trans));
    }

    protected void directDeposit() {

        int twoThouCnt;
        int fiveHundCnt;
        int twoHundCnt;
        int hundCnt;

        System.out.println();
        System.out.print("Please Enter the number of Two Thousand Rupees Notes: ");
        twoThouCnt = sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Five Hundred Rupees Notes: ");
        fiveHundCnt = sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Two Hundred Rupees Notes: ");
        twoHundCnt = sc.nextInt();

        System.out.println();
        System.out.print("Please Enter the number of Hundred Rupees Notes: ");
        hundCnt = sc.nextInt();

        int Money = (twoHundCnt*200) + (twoThouCnt*2000) + (fiveHundCnt*500) + (hundCnt*100);
        int addMoney = Integer.parseInt( usrDet.get(pos).get("Balance")) + Money;

        usrDet.get(pos).replace("Balance", String.valueOf( addMoney ) );

        addStatement("Credit " + Money + "  " + "Balance " + addMoney);

        System.out.println();
        System.out.println("Money Added Successfully !!!");
        System.out.println();
        System.out.println("Balance : Rs." + addMoney);
        
    }
    
    protected void amountTransfer() {

        System.out.print("Enter 6 Digit Account Number: 0000");
        String accNo = sc.next();
        System.out.println();

        if(accNo.length() >= 10){

            System.out.print("Enter 4 Digit IFSC Code: NB00");
            String ifsc = sc.next();
            System.out.println();

            if(ifsc.length() == 4) {

                System.out.print("Enter the Amount need to be transfered: ");
                int mon = sc.nextInt();
                int Balance = Integer.parseInt(usrDet.get(pos).get("Balance"));

                if(Balance > mon){
                    usrDet.get(pos).replace("Balance", String.valueOf(Balance-mon));
                    System.out.println("Amount Transfered Successfully !!!");
                }else{
                    System.out.println("The Money Entered Exceeds Your Account Balance");
                }
            }
            System.out.println();
        }
    }

    //Admin Functions


    //Stores Admin Details
    static List<HashMap<String, String>> adminDet = new ArrayList<HashMap<String, String>>();

    //Declaring Admins
    final void addAdmin(){
        HashMap<String, String> tempData = new HashMap<>();
        tempData.put("UserName", "Admin1");
        tempData.put("Pin", "0000");
        adminDet.add(tempData);
        HashMap<String, String> tempData1 = new HashMap<>();
        tempData1.put("UserName", "Admin2");
        tempData1.put("Pin", "1111");
        adminDet.add(tempData1);
    }

    // check admin details
    protected boolean adminAuth(String adminName){
        
        boolean flag = true;

        for (HashMap<String, String> hashMap : adminDet) { 

            if(hashMap.get("UserName").equals(adminName)){
                
                flag = false;
               
                System.out.print("Please Enter Your Pin to Continue:  ");
                
                int pin = sc.nextInt();

                if(Integer.parseInt(hashMap.get("Pin")) == pin){
                    System.out.println();
                    System.out.println("Login Sucessfull");
                    return true;
                }else{
                    System.out.println();
                    System.out.println("Incorrect Pin, Try Again Later");
                    return false;
                }
            }
        }
        if(flag){
            System.out.println();
            System.out.println("Admin Not Found, Please Try Again");
            return false;
        }
        return false;
    }

    protected void loadMoney() {
        obj.loadMoney();
    }

    protected void displayBalane() {
        System.out.println();
        System.out.println(obj.displayBal());
    }
}
