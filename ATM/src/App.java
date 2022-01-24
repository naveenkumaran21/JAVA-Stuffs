public class App extends AuthWel{
    public static void main(String[] args) {

        AuthWel objAuthWel = new AuthWel();

        while(true){
            int opt = objAuthWel.inpCheck();

            if(opt == 1)
                objAuthWel.borrwerFunc();
            else if(opt == 2)
                objAuthWel.adminFunc();
            else if(opt == 3)
                break;
        }
    }
}
