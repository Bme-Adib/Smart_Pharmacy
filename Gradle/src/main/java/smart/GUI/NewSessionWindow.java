package smart.GUI;

import smart.BackEnd.Doctor;
import smart.BackEnd.Patient;
import smart.BackEnd.Pharmacy;
import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;

public class NewSessionWindow {

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Doctor doctor;

    //
    private JFrame windowNewSession;
    private Container container;



    public void runNewSession(FireBase fireBase, Doctor doctor, Patient patient) {
        this.fireBase = fireBase;
        setUpWindow();
        setUpAssignment();
        setPlacement();
        setClickListeners();


        windowNewSession.setVisible(true);
    }

    private void setClickListeners() {

    }

    private void setUpAssignment() {

    }

    private void setPlacement() {
        int xMargin = 10;
        int yMargin = 20;
        int lineSpacer = 5;
        int logo_dim = 200;
        int yStep = yMargin;


    }

    private void setUpWindow() {
        windowNewSession = new JFrame(PV.APP_TITLE + " - New Session");
        windowWidth = PV.WINDOW_WIDTH;
        windowHeight = PV.WINDOW_HEIGHT;
        windowNewSession.setSize(windowWidth, windowHeight);
        windowNewSession.setResizable(false);
//        windowSignIn.setUndecorated(true);
        windowNewSession.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowNewSession.setLocation((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2);
        container = windowNewSession.getContentPane();
        container.setBackground(PV.APPLICATION_BACKGROUND);
        container.setLayout(null);
    }

}
