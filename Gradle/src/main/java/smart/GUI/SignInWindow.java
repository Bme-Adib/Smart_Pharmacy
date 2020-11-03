package smart.GUI;

import smart.BackEnd.Doctor;
import smart.BackEnd.Hashing;
import smart.BackEnd.Patient;
import smart.BackEnd.Pharmacy;
import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInWindow {

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Doctor doctor;
    private Patient patient;
    private String idType;
    //
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
    private JButton signUp;

    private JLabel jSignature;


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
        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpMethod();
            }
        });
    }

    private void signUpMethod() {
        windowSignIn.dispose();
        new SignUpOtherWindow().runSignUp(fireBase);
    }

    private void singInMethod() {
        /*
        1- Check if Doctor or Pharmacy          ✓
        2- Check if user Exist                  ✓
        3- if yes get                           ✓
        4- Check if Password Matches the ID
            The Methode:
            1- read id                                          ✓
            2- check for 3rd and 4th letters                    ✓
            3- if(DR) then doctor and if (ph) then pharmacy     ✓
         */
        String errorMessage = "ID or Password is wrong";
        String idByUser = id.getText();
        String temp = idByUser.substring(0, 4).toUpperCase();
        idByUser = temp + idByUser.substring(4);


        if (idByUser.length() > 4) {
            idType = idByUser.substring(2, 4);
        } else {
            idType = "AISHA ALHARWEEL";
        }
        if (idType.equals("DR")) {
            if (fireBase.checkDoctorID(idByUser)) {
                doctor = fireBase.readDoctor(idByUser);
                if (new Hashing().SHA256(String.valueOf(password.getPassword())).equals(doctor.getPassword())) {
                    startDoctorWindow(fireBase,doctor);
                } else {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (idType.equals("PH")) {
            if (fireBase.checkPharmacy(idByUser)) {
                pharmacy = fireBase.readPharmacy(idByUser);
                if (new Hashing().SHA256(String.valueOf(password.getPassword())).equals(pharmacy.getPassword())) {
                    startPharmacyWindow(fireBase,pharmacy);
                } else {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (idType.equals("PT")) {
            if (fireBase.checkPatientID(idByUser)) {

                patient = fireBase.readPatient(idByUser);
                if (new Hashing().SHA256(String.valueOf(password.getPassword())).equals(patient.getPassword())) {
                    startPatientWindow(fireBase,patient);
                } else {
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void startDoctorWindow(FireBase fireBase, Doctor doctor) {
        windowSignIn.dispose();
        patient=null;
        new DoctorWindow().runDoctor(fireBase,doctor,patient);
    }

    private void startPatientWindow(FireBase fireBase, Patient patient) {
    }

    private void startPharmacyWindow(FireBase fireBase, Pharmacy pharmacy) {
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

        id = new JTextField("SPDR9306057462");
        id.setFont(PV.NORMAL);
        id.setForeground(PV.BLACK);
        container.add(id);

        jPassword = new JLabel("Password");
        jPassword.setFont(PV.NORMALBOLD);
        jPassword.setForeground(PV.BLACK);
        container.add(jPassword);

        password = new JPasswordField("12345678");
        password.setFont(PV.NORMAL);
        password.setForeground(PV.BLACK);
        container.add(password);

        signIn = new JButton("Sign In");
        signIn.setFont(PV.HEADING2);
        signIn.setForeground(PV.BLACK);
        container.add(signIn);

        signUp = new JButton("Sign Up");
        signUp.setFont(PV.HEADING2);
        signUp.setForeground(PV.BLACK);
        container.add(signUp);

        jSignature = new JLabel("By: Aisha AlHarweel (aishaalharweel@gmail.com)");
        jSignature.setFont(PV.NORMAL);
        jSignature.setForeground(PV.BLACK);
        container.add(jSignature);

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
        signIn.setBounds((logo_dim - signUp.getPreferredSize().width) / 2 + 300, yStep, signUp.getPreferredSize().width, signIn.getPreferredSize().height);

        //Line 6
        yStep += signIn.getPreferredSize().height + lineSpacer;
        signUp.setBounds((logo_dim - signUp.getPreferredSize().width) / 2 + 300, yStep, signUp.getPreferredSize().width, signUp.getPreferredSize().height);

        ps = jSignature.getPreferredSize();
        jSignature.setBounds(windowWidth - ps.width - 20, 540, ps.width, ps.height);
    }

    private void setUpWindow() {
        windowSignIn = new JFrame(PV.APP_TITLE + " - Sign In");
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
