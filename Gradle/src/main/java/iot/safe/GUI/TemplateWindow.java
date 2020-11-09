package iot.safe.GUI;

import iot.safe.BackEnd.Doctor;
import iot.safe.BackEnd.Pharmacy;
import iot.safe.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;

public class TemplateWindow {

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Doctor doctor;

    //
    private JFrame windowSignIn;
    private Container container;



    public void runSignIn(FireBase fireBase) {
        this.fireBase = fireBase;
        setUpWindow();
        setUpAssignment();
        setPlacement();
        setClickListeners();


        windowSignIn.setVisible(true);
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
        windowSignIn = new JFrame(PV.APP_TITLE);
        windowWidth = PV.WINDOW_WIDTH;
        windowHeight = PV.WINDOW_HEIGHT;
        windowSignIn.setSize(windowWidth, windowHeight);
        windowSignIn.setResizable(false);
//        windowSignIn.setUndecorated(true);
        windowSignIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowSignIn.setLocation((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2);
        container = windowSignIn.getContentPane();
        container.setBackground(PV.APPLICATION_BACKGROUND);
        container.setLayout(null);
    }

}
