package iot.safe.GUI;

import iot.safe.BackEnd.*;
import iot.safe.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpPatientWindow {

    Thread sendEmail = new Thread(){
      public void run(){
          sendEmail(patient.getEmail(),patient.getFullName(),patient.getId());
      }
    };

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Doctor doctor;
    private Patient patient;
    //
    private JFrame windowSignUp;
    private Container container;

    private JLabel jDoctorName;
    private JButton back;


    private JLabel Fixed1;


    // Patient Shit
    private JLabel patient_jFirstName;
    private JTextField patient_firstName;
    private JLabel patient_jLastName;
    private JTextField patient_lastName;
    private JLabel patient_jGender;
    private JComboBox<String> patient_gender;
    private JLabel patient_jDateOfBirth;
    private JTextField patient_day;
    private JTextField patient_month;
    private JTextField patient_year;
    private JLabel patient_jMobileNumber;
    private JTextField patient_mobileNumber;
    private JLabel patient_jEmail;
    private JTextField patient_email;
    private JLabel patient_jID;
    private JTextField patient_ID;
    private JLabel patient_jPassword;
    private JPasswordField patient_password;
    private JLabel patient_jConfirmPassword;
    private JPasswordField patient_confirmPassword;



    private JButton signUp;


    public void runSignUp(FireBase fireBase,Doctor doctor) {
        this.fireBase = fireBase;
        this.doctor = doctor;
        setUpWindow();
        setUpAssignment();
        setUpHeader();
        setPlacement();
        setClickListeners();
        setPatientVisibility(true);


        windowSignUp.setVisible(true);
    }

    private void setClickListeners() {

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signupPatient();
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowSignUp.dispose();
                patient = null;
                new DoctorWindow().runDoctor(fireBase,doctor,patient);
            }
        });
    }


    private void signupPatient() {
        String error = "";

        if (patient_firstName.getText().trim().isEmpty()) {
            error += "Please enter First Name\n";
        } else {
            patient.setFirstName(patient_firstName.getText().trim());
        }

        if (patient_lastName.getText().trim().isEmpty()) {
            error += "Please enter Last Name\n";
        } else {
            patient.setLastName(patient_lastName.getText().trim());
        }

        if (patient_gender.getSelectedItem().toString().trim().isEmpty()) {
            error += "Please enter Gender\n";
        } else {
            patient.setGender(patient_gender.getSelectedItem().toString().trim());
        }

        if (patient_day.getText().trim().isEmpty()) {
            error += "Please enter day\n";
        } else {
            switch (Integer.parseInt(patient_month.getText().trim())) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (Integer.parseInt(patient_day.getText().trim()) > 0 && Integer.parseInt(patient_day.getText().trim()) < 32) {
                        patient.setDay(Integer.parseInt(patient_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (Integer.parseInt(patient_day.getText().trim()) > 0 && Integer.parseInt(patient_day.getText().trim()) < 31) {
                        patient.setDay(Integer.parseInt(patient_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
                case 2:
                    if (Integer.parseInt(patient_day.getText().trim()) > 0 && Integer.parseInt(patient_day.getText().trim()) < 30 &&
                            Integer.parseInt(patient_day.getText().trim()) % 4 == 0) {
                        patient.setDay(Integer.parseInt(patient_day.getText().trim()));
                    } else if (Integer.parseInt(patient_day.getText().trim()) > 0 && Integer.parseInt(patient_day.getText().trim()) < 29 &&
                            Integer.parseInt(patient_day.getText().trim()) % 4 != 0) {
                        patient.setDay(Integer.parseInt(patient_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
            }
        }

        if (patient_month.getText().trim().isEmpty()) {
            error += "Please enter month\n";
        } else {
            if (Integer.parseInt(patient_month.getText().trim()) > 0 && Integer.parseInt(patient_month.getText().trim()) < 13) {
                patient.setMonth(Integer.parseInt(patient_month.getText().trim()));
            } else {
                error += "Invalid month\n";
            }
        }

        if (patient_year.getText().trim().isEmpty()) {
            error += "Please enter year\n";
        } else {
            if (Integer.parseInt(patient_year.getText().trim()) > 1900 && Integer.parseInt(patient_year.getText().trim()) < new Time_Stamp().getYear()) {
                patient.setYear(Integer.parseInt(patient_year.getText().trim()));
            } else {
                error += "Invalid Year\n";
            }
        }


        if (patient_mobileNumber.getText().trim().isEmpty()) {
            error += "Please enter mobile number\n";
        } else {
            patient.setPhoneNumber(patient_mobileNumber.getText().trim());
        }


        if (!patient_email.getText().trim().isEmpty()) {
            if (patient_email.getText().trim().contains("@") && patient_email.getText().trim().contains(".")) {
                patient.setEmail(patient_email.getText().trim());
            } else {
                error += "Invalid Email\n";
            }
        } else {
            error += "Please enter e-mail\n";
        }

        if (patient_ID.getText().trim().isEmpty()) {
            error += "Please enter ID\n";
        } else {
            patient.setIDN(patient_ID.getText().trim());
        }

        if (patient_password.getPassword().length >= 8) {
            if (String.valueOf(patient_password.getPassword()).equals(String.valueOf(patient_confirmPassword.getPassword()))) {
                patient.passwordSet(String.valueOf(patient_password.getPassword()));
            } else {
                error += "Password doesn't match\n";
            }
        } else {
            error += "Password must be more than 8 characters";
        }


        if (error.isEmpty()) {
            if (fireBase.checkPatient(patient_email.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Email already exist", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                patient.setCreatedBy(doctor.getFullName());
                patient.continueCreation();
                if (fireBase.writePatientToFireBase(patient)) {

                    JOptionPane.showMessageDialog(null, "Patient Account Created Successfully\nPatient ID: " + patient.getId() +
                            "\n( Please save this ID and use it to sign in )\n", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    sendEmail.start();
                    windowSignUp.dispose();
                    new DoctorWindow().runDoctor(fireBase,doctor,patient);

                } else {
                    JOptionPane.showMessageDialog(null, "Something is Wrong", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendEmail(String email, String fullName, String id) {
        new SendEmail().sendGreetingEmail(email,fullName,id);
    }

    private void setUpAssignment() {
        patient = new Patient();

        Fixed1 = new JLabel("Create new patient account");
        Fixed1.setFont(PV.HEADING1);
        Fixed1.setForeground(PV.BLACK);
        container.add(Fixed1);


        patient_jFirstName = new JLabel("First Name");
        patient_jFirstName.setFont(PV.NORMALBOLD);
        patient_jFirstName.setForeground(PV.BLACK);
        container.add(patient_jFirstName);

        patient_firstName = new JTextField("");
        patient_firstName.setFont(PV.NORMAL);
        patient_firstName.setForeground(PV.BLACK);
        container.add(patient_firstName);

        patient_jLastName = new JLabel("Last Name");
        patient_jLastName.setFont(PV.NORMALBOLD);
        patient_jLastName.setForeground(PV.BLACK);
        container.add(patient_jLastName);

        patient_lastName = new JTextField();
        patient_lastName.setFont(PV.NORMAL);
        patient_lastName.setForeground(PV.BLACK);
        container.add(patient_lastName);

        patient_jGender = new JLabel("Gender");
        patient_jGender.setFont(PV.NORMALBOLD);
        patient_jGender.setForeground(PV.BLACK);
        container.add(patient_jGender);

        patient_gender = new JComboBox<>(PV.GENDERS_LIST);
        patient_gender.setBackground(Color.WHITE);
        patient_gender.setFont(PV.NORMAL);
        patient_gender.setForeground(PV.BLACK);
        container.add(patient_gender);

        patient_jDateOfBirth = new JLabel("Birth Date");
        patient_jDateOfBirth.setFont(PV.NORMALBOLD);
        patient_jDateOfBirth.setForeground(PV.BLACK);
        container.add(patient_jDateOfBirth);

        patient_day = new JTextField();
        patient_day.setFont(PV.NORMAL);
        patient_day.setForeground(PV.BLACK);
        container.add(patient_day);


        patient_month = new JTextField();
        patient_month.setFont(PV.NORMAL);
        patient_month.setForeground(PV.BLACK);
        container.add(patient_month);


        patient_year = new JTextField();
        patient_year.setFont(PV.NORMAL);
        patient_year.setForeground(PV.BLACK);
        container.add(patient_year);


        patient_jMobileNumber = new JLabel("Mobile No.");
        patient_jMobileNumber.setFont(PV.NORMALBOLD);
        patient_jMobileNumber.setForeground(PV.BLACK);
        container.add(patient_jMobileNumber);

        patient_mobileNumber = new JTextField();
        patient_mobileNumber.setFont(PV.NORMAL);
        patient_mobileNumber.setForeground(PV.BLACK);
        container.add(patient_mobileNumber);


        patient_jEmail = new JLabel("E-Mail");
        patient_jEmail.setFont(PV.NORMALBOLD);
        patient_jEmail.setForeground(PV.BLACK);
        container.add(patient_jEmail);

        patient_email = new JTextField();
        patient_email.setFont(PV.NORMAL);
        patient_email.setForeground(PV.BLACK);
        container.add(patient_email);

        patient_jID = new JLabel("Identification No.");
        patient_jID.setFont(PV.NORMALBOLD);
        patient_jID.setForeground(PV.BLACK);
        container.add(patient_jID);

        patient_ID = new JTextField();
        patient_ID.setFont(PV.NORMAL);
        patient_ID.setForeground(PV.BLACK);
        container.add(patient_ID);

        patient_jPassword = new JLabel("Password");
        patient_jPassword.setFont(PV.NORMALBOLD);
        patient_jPassword.setForeground(PV.BLACK);
        container.add(patient_jPassword);

        patient_password = new JPasswordField();
        patient_password.setFont(PV.NORMAL);
        patient_password.setForeground(PV.BLACK);
        container.add(patient_password);

        patient_jConfirmPassword = new JLabel("Confirm Password");
        patient_jConfirmPassword.setFont(PV.NORMALBOLD);
        patient_jConfirmPassword.setForeground(PV.BLACK);
        container.add(patient_jConfirmPassword);

        patient_confirmPassword = new JPasswordField();
        patient_confirmPassword.setFont(PV.NORMAL);
        patient_confirmPassword.setForeground(PV.BLACK);
        container.add(patient_confirmPassword);



        signUp = new JButton("Sign Up");
        signUp.setFont(PV.NORMALBOLD);
        signUp.setForeground(PV.BLACK);
        container.add(signUp);

    }

    private void setPlacement() {
        int xMargin = 50;

        int yMargin = 20;
        int lineSpacer = 5;
        int logo_dim = 200;
        int yStep = yMargin;
        int jLableWidth = 120;
        int jTextFieldWidth = 200;
        int xMargin2 = xMargin + jLableWidth + 10;


//        doctorType.setBounds(50 + 440 - 300, yStep, doctorType.getPreferredSize().width, doctorType.getPreferredSize().height);
//        pharmacyType.setBounds(50 + 440 - 150, yStep, pharmacyType.getPreferredSize().width, pharmacyType.getPreferredSize().height);
//        patientType.setBounds(50 + 460, yStep, patientType.getPreferredSize().width, patientType.getPreferredSize().height);

        //Line
        yStep = 75;
        Fixed1.setBounds(xMargin, yStep, Fixed1.getPreferredSize().width, Fixed1.getPreferredSize().height);


        //Line1
        yStep += Fixed1.getPreferredSize().height + lineSpacer * 4;
        patient_jFirstName.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_firstName.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jLastName.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_lastName.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;

        patient_jGender.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_gender.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);




        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jDateOfBirth.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_day.setBounds(xMargin2, yStep, 20, patient_jFirstName.getPreferredSize().height + 3);
        patient_month.setBounds(xMargin2 + 30, yStep, 20, patient_jFirstName.getPreferredSize().height + 3);
        patient_year.setBounds(xMargin2 + 60, yStep, 40, patient_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jID.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_ID.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;

        patient_jEmail.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_email.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jMobileNumber.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_mobileNumber.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jPassword.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_password.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);

        //Line
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        patient_jConfirmPassword.setBounds(xMargin, yStep, jLableWidth, patient_jFirstName.getPreferredSize().height);
        patient_confirmPassword.setBounds(xMargin2, yStep, jTextFieldWidth, patient_jFirstName.getPreferredSize().height + 3);


        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        yStep += patient_jLastName.getPreferredSize().height + lineSpacer;
        signUp.setBounds((windowWidth - signUp.getPreferredSize().width) / 2, yStep, signUp.getPreferredSize().width, signUp.getPreferredSize().height);
    }

    private void setUpWindow() {
        windowSignUp = new JFrame(PV.APP_TITLE + " - New Patient");
        windowWidth = PV.WINDOW_WIDTH;
        windowHeight = PV.WINDOW_HEIGHT;
        windowSignUp.setSize(windowWidth, windowHeight);
        windowSignUp.setResizable(false);
//        windowSignIn.setUndecorated(true);
        windowSignUp.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowSignUp.setLocation((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2);
        container = windowSignUp.getContentPane();
        container.setBackground(PV.APPLICATION_BACKGROUND);
        container.setLayout(null);
    }

    private void setPatientVisibility(boolean v) {
        patient_jFirstName.setVisible(v);
        patient_firstName.setVisible(v);
        patient_jLastName.setVisible(v);
        patient_lastName.setVisible(v);
        patient_gender.setVisible(v);
        patient_jGender.setVisible(v);
        patient_jDateOfBirth.setVisible(v);
        patient_day.setVisible(v);
        patient_month.setVisible(v);
        patient_year.setVisible(v);
        patient_mobileNumber.setVisible(v);
        patient_jMobileNumber.setVisible(v);
        patient_ID.setVisible(v);
        patient_jID.setVisible(v);
        patient_email.setVisible(v);
        patient_jEmail.setVisible(v);
        patient_password.setVisible(v);
        patient_jPassword.setVisible(v);
        patient_confirmPassword.setVisible(v);
        patient_jConfirmPassword.setVisible(v);
    }

    private void setUpHeader() {


        jDoctorName = new JLabel("Dr. " + doctor.getFullName());
        jDoctorName.setFont(PV.HEADING2);
        jDoctorName.setForeground(Color.WHITE);
        container.add(jDoctorName);

        back = new JButton("Back");
        back.setFont(PV.HEADING2);
        back.setForeground(PV.BLACK);
        container.add(back);

        jDoctorName.setBounds(windowWidth - jDoctorName.getPreferredSize().width - 20, (50 - jDoctorName.getPreferredSize().height) / 2, jDoctorName.getPreferredSize().width, jDoctorName.getPreferredSize().height);
        back.setBounds(20, (50 - back.getPreferredSize().height) / 2, back.getPreferredSize().width, back.getPreferredSize().height);

        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setBackground(PV.HEADING_COLOR_DOCTOR);
        container.add(header);
        header.setBounds(0, 0, windowWidth, 50);
    }


}
