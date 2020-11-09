package safe;

import iot.safe.FireStore.FireBase;
import iot.safe.GUI.SignInWindow;

public class MainActivity {
    public static void main(String[] args) {
        FireBase fireBase = new FireBase();

        new SignInWindow().runSignIn(fireBase);
//        new SignUpWindow().runSignUp(fireBase);
//        SendEmail sendEmail = new SendEmail();
//        sendEmail.SendEmailFROMTO("m.adib.ghannam@gmail.com","Hello","Halooooo","","");

    }
}