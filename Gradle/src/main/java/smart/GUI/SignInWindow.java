package smart.GUI;

import smart.BackEnd.Doctor;
import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInWindow {

    private int windowWidth;
    private int windowHeight;

    private FireBase fireBase;
    private JFrame windowSignIn;
    private Container container;

    private JLabel jTile;
    private JLabel logo;
    private Image logoImage;

    private JLabel jID;
    private JTextField id;
    private JLabel jPassword;
    private JPasswordField password;
    private JButton signIn;


    public void runSignIn(FireBase fireBase) {
        this.fireBase = fireBase;
        setUpWindow();
        setUpAssignment();
        setPlacement();
        setClickListeners();


        windowSignIn.setVisible(true);
    }

    private void setClickListeners() {
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                singInMethod();
            }
        });
    }

    private void singInMethod() {
        /*
        1- Check if Doctor or Pharmacy          ✓
        2- Check if user Exist
        3- if yes get
        4- Check if Password Matches the ID
            The Methode:
            1- read id
            2- check for 3rd and 4th letters
            3- if(DR) then doctor and if (ph) then pharmacy
         */
        String idByUser = id.getText().toUpperCase();
        String idType;
        if (idByUser.length()>4){
            idType = idByUser.substring(2,4);
        }else{
            idType = "AISHA AL HARWEEL";
        }

        System.out.println(password.getPassword());

        if (idType.equals("DR")){
            if (fireBase.checkDoctor(idByUser)){
                Doctor doctor = fireBase.readDoctor(idByUser);




            }else {
                JOptionPane.showMessageDialog(null, "Doctor Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else if(idType.equals("PH")){
            if (fireBase.checkPharmacy(idByUser)){
                System.out.println("Pharmacy Found");
            }else {
                JOptionPane.showMessageDialog(null, "Pharmacy Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please Check the ID", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }


    private void setUpAssignment() {
        jTile = new JLabel("Smart Pharmacy");
        jTile.setFont(PV.HEADING);
        jTile.setForeground(PV.BLACK);
        container.add(jTile);

        logo = new JLabel();
        container.add(logo);
        logoImage = new ImageIcon(this.getClass().getResource("/sign_in_logo.jpg")).getImage();
        logo.setIcon(new ImageIcon(logoImage));

        jID = new JLabel("ID");
        jID.setFont(PV.NORMALBOLD);
        jID.setForeground(PV.BLACK);
        container.add(jID);

        id = new JTextField();
        id.setFont(PV.NORMAL);
        id.setForeground(PV.BLACK);
        container.add(id);

        jPassword = new JLabel("Password");
        jPassword.setFont(PV.NORMALBOLD);
        jPassword.setForeground(PV.BLACK);
        container.add(jPassword);

        password = new JPasswordField();
        password.setFont(PV.NORMAL);
        password.setForeground(PV.BLACK);
        container.add(password);

        signIn = new JButton("Sign In");
        signIn.setFont(PV.HEADING2);
        signIn.setForeground(PV.BLACK);
        container.add(signIn);


    }

    private void setPlacement() {
        int xMargin = 10;
        int yMargin = 20;
        int lineSpacer = 5;
        int logo_dim = 200;
        int yStep = yMargin;

        // Line 1
        Dimension ps = jTile.getPreferredSize();
        jTile.setBounds((windowWidth - ps.width) / 2, yStep, ps.width, ps.height);

        //Line 2
        yStep += ps.height + lineSpacer;
        yStep += ps.height + lineSpacer;
        logo.setBounds((windowWidth - logo_dim) / 2, yStep, logo_dim, logo_dim);

        //Line 3
        yStep += logo_dim + lineSpacer;
        jID.setBounds(300, yStep, jID.getPreferredSize().width, jID.getPreferredSize().height);
        id.setBounds(370, yStep, 130, id.getPreferredSize().height);

        //Line 4
        yStep += jID.getPreferredSize().height + lineSpacer;
        jPassword.setBounds(300, yStep, jPassword.getPreferredSize().width, jPassword.getPreferredSize().height);
        password.setBounds(370, yStep, 130, password.getPreferredSize().height);

        //Line 5
        yStep += jID.getPreferredSize().height + lineSpacer;
        yStep += jID.getPreferredSize().height + lineSpacer;
        signIn.setBounds((logo_dim - signIn.getPreferredSize().width) / 2 + 300, yStep, signIn.getPreferredSize().width, signIn.getPreferredSize().height);

    }

    private void setUpWindow() {
        windowSignIn = new JFrame("Smart Pharmacy Desktop App");
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
