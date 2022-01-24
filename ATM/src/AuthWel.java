import java.util.*;

class AuthWel extends Functions{

    Scanner sc = new Scanner(System.in);
    Functions obj = new Functions();
    
    AuthWel(){
        updateUsrDet();
    }

    public int inpCheck(){
        System.out.println();
        System.out.println("1. Borrower \n2. Admin \n3. Close");
        System.out.println();
        
        int usrOpt = sc.nextInt();
        sc.nextLine();
        cls();

        return usrOpt;
    }

    public void borrwerFunc() {

        System.out.println();
        System.out.print("Please Enter the User Name: ");
        String usrName = sc.nextLine();
        HashMap<String, String> usrDet = obj.usrCheck(usrName);
        if(usrDet.get("Status") == "true"){

            System.out.print("Please Enter Your Pin: ");
            String pin = sc.next();
            cls();

            if(obj.authentication(usrDet, pin)){

                System.out.println();
                System.out.println("Welcome " + usrName);
                System.out.println();
                cls();
               
                System.out.println("1. Withdraw Amount \n2. Check Balance \n3. Pin Change \n4. Download Mini Statement \n5. Direct Deposit \n6. Amount Transfer \n");
                int opt = sc.nextInt();
                cls();

                switch(opt){

                    case 1:
                        obj.withdrawAmount();
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Rs: " + obj.checkBalance());
                        break;

                    case 3:
                        System.out.println();
                        System.out.print("Please Enter New Pin: ");
                        String newPin = sc.next();
                        System.out.println();
                        if(obj.pinChange(newPin, usrName))  System.out.println("Pin Changed Successfully");
                        else    System.out.println("Pin Change Failed");
                        break;

                    case 4:
                        obj.miniStatement();
                        break;

                    case 5:
                        obj.directDeposit();
                        break;

                    case 6:
                        obj.amountTransfer();
                        break;
                }

            }else{
                System.out.println();
                System.out.println("Invalid Pin, Try Again");
            }

        }else{
            System.out.println();
            System.out.println("User Name Not Found, Try Again");
        }
    }

    public void adminFunc() {

        System.out.println();
        System.out.print("Please Enter Your Admin Name:  ");
        String adminName = sc.next();

        obj.addAdmin();

        if(obj.adminAuth(adminName)){
            System.out.println();
            System.out.println("Welcome " + adminName);
            System.out.println();
            System.out.println("1. Load Money \n2. Display Money");
            System.out.println();

            int adminChoice = sc.nextInt();

            switch(adminChoice){
                case 1:
                    obj.loadMoney();
                    break;
                case 2:
                    obj.displayBalane();
                    break;
            }
        }

    }
}
