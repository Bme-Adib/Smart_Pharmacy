package smart;

import smart.FireStore.FireBase;
import smart.GUI.SignInWindow;

import javax.swing.*;

public class MainActivity {
    public static void main(String[] args) {
//        SignInWindow signInWindow = new SignInWindow();
//        signInWindow.runSignIn();

        FireBase fireBase = new FireBase();

        new SignInWindow().runSignIn(fireBase);
    }
}