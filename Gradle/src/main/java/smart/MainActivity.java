package smart;

import smart.FireStore.FireBase;
import smart.GUI.SignInWindow;
import smart.GUI.SignUpWindow;

import javax.swing.*;

public class MainActivity {
    public static void main(String[] args) {
        FireBase fireBase = new FireBase();

//        new SignInWindow().runSignIn(fireBase);
        new SignUpWindow().runSignUp(fireBase);
    }
}