package smart.GUI;

import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;

public class SignInWindow {

    private int windowWidth;
    private int windowHeight;

    private JFrame windowSignIn;
    Container container;

    JLabel jTile;


    public void runSignIn(FireBase fireBase) {
        setUpWindow();
        setUpAssignment();
        setPlacement();


        windowSignIn.setVisible(true);
    }




    private void setUpAssignment() {
        jTile = new JLabel("Smart Pharmacy");
        jTile.setFont(PV.HEADING);
        jTile.setForeground(PV.BLACK);
        container.add(jTile);

    }

    private void setPlacement() {
        int xMargin = 10;
        int yMargin = 20;
        int lineSpacer = 5;
        int yStep = yMargin;

        // Line 1
        Dimension ps = jTile.getPreferredSize();
        jTile.setBounds((windowWidth-ps.width)/2,yStep,ps.width,ps.height);
    }

    private void setUpWindow() {
        windowSignIn = new JFrame("Smart Pharmacy Desktop App");
        windowWidth = PV.WINDOW_WIDTH;
        windowHeight = PV.WINDOW_HEIGHT;
        windowSignIn.setSize(windowWidth,windowHeight);
        windowSignIn.setResizable(false);
        windowSignIn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowSignIn.setLocation((screenSize.width-windowWidth)/2,(screenSize.height-windowHeight)/2);
        container = windowSignIn.getContentPane();
        container.setBackground(PV.APPLICATION_BACKGROUND);
        container.setLayout(null);
    }
}
