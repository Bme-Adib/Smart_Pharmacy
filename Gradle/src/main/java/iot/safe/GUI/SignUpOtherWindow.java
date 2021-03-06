package iot.safe.GUI;

import iot.safe.BackEnd.*;
import iot.safe.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpOtherWindow {
    private Thread sendEmailPharmacy = new Thread(){
      public void run(){
          sendEmail(pharmacy.getEmail(),pharmacy.getPharmacyName(),pharmacy.getId());
      }
    };

    private Thread sendEmailDoctor = new Thread(){
        public void run(){
            sendEmail(doctor.getEmail(),"Dr. " + doctor.getFullName(),doctor.getId());
        }
    };

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Doctor doctor;
    private Patient patient;
    //
    private JFrame windowSignUp;
    private Container container;
    //
//    private JButton doctorType;
//    private JButton pharmacyType;
//    private JButton patientType;

    private JButton back;

    private JComboBox<String> accountType;
    private String[] accountTypeStrings = {"Please choose account type", "Doctor Account", "Pharmacy Account"};

    private JLabel Fixed1;


    // Doctor Shit
    private JLabel doctor_jFirstName;
    private JTextField doctor_firstName;
    private JLabel doctor_jLastName;
    private JTextField doctor_lastName;
    private JLabel doctor_jGender;
    private JComboBox<String> doctor_gender;
    private JLabel doctor_jDateOfBirth;
    private JTextField doctor_day;
    private JTextField doctor_month;
    private JTextField doctor_year;
    private JLabel doctor_jSpeciality;
    private JTextField doctor_speciality;
    private JLabel doctor_jMobileNumber;
    private JTextField doctor_mobileNumber;
    private JLabel doctor_jclinicNumber;
    private JTextField doctor_clinicNumber;
    private JLabel doctor_jEmail;
    private JTextField doctor_email;
    private JLabel doctor_jLicenceID;
    private JTextField doctor_licenceID;
    private JLabel doctor_jPassword;
    private JPasswordField doctor_password;
    private JLabel doctor_jConfirmPassword;
    private JPasswordField doctor_confirmPassword;



    // Pharmacy Shit
    private JLabel pharmacy_jName;
    private JTextField pharmacy_name;
    private JLabel pharmacy_jPhoneNumber;
    private JTextField pharmacy_phoneNumber;
    private JLabel pharmacy_jlicenceID;
    private JTextField pharmacy_licenceID;
    private JLabel pharmacy_jEmail;
    private JTextField pharmacy_email;
    private JLabel pharmacy_jPassword;
    private JPasswordField pharmacy_password;
    private JLabel pharmacy_jConfirmPassword;
    private JPasswordField pharmacy_confirmPassword;

    private JButton signUp;

    private int whichButton = 0;


    public void runSignUp(FireBase fireBase) {
        this.fireBase = fireBase;
        setUpWindow();
        setUpAssignment();
        setPlacement();
        setUpHeader();
        setClickListeners();
        setDoctorVisibility(false);
        setPharmacyVisibility(false);


        windowSignUp.setVisible(true);
    }

    private void setClickListeners() {

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpMethod();
            }
        });
        accountType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (accountType.getSelectedIndex()) {
                    case 0:
                        setDoctorVisibility(false);
                        setPharmacyVisibility(false);
                        break;
                    case 1:
                        setPharmacyVisibility(false);
                        setDoctorVisibility(true);
                        break;
                    case 2:
                        setDoctorVisibility(false);
                        setPharmacyVisibility(true);
                        break;
                }
            }
        });
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                windowSignUp.dispose();
                new SignInWindow().runSignIn(fireBase);
            }
        });
    }

    private void signUpMethod() {
        System.out.println(whichButton);
        switch (whichButton) {
            case 1:
                signUpDoctor();
                break;
            case 2:
                signUpPharmacy();
                break;
        }
    }

    private void signUpPharmacy() {
        String error = "";

        if (pharmacy_name.getText().trim().isEmpty()) {
            error += "Please enter pharmacy Name\n";
        } else {
            pharmacy.setPharmacyName(pharmacy_name.getText().trim());
        }

        if (pharmacy_licenceID.getText().trim().isEmpty()) {
            error += "Please enter licence ID\n";
        } else {
            pharmacy.setLicenseId(pharmacy_licenceID.getText().trim());
        }

        if (!pharmacy_email.getText().trim().isEmpty()) {
            if (pharmacy_email.getText().trim().contains("@") && pharmacy_email.getText().trim().contains(".")) {
                pharmacy.setEmail(pharmacy_email.getText().trim());
            } else {
                error += "Invalid Email\n";
            }
        } else {
            error += "Please enter e-mail\n";
        }

        if (pharmacy_phoneNumber.getText().trim().isEmpty()) {
            error += "Please enter phone Number\n";
        } else {
            pharmacy.setPhoneNumber(pharmacy_phoneNumber.getText().trim());
        }

        if (pharmacy_password.getPassword().length >= 8) {
            if (String.valueOf(pharmacy_password.getPassword()).equals(String.valueOf(pharmacy_confirmPassword.getPassword()))) {
                pharmacy.passwordSet(String.valueOf(pharmacy_password.getPassword()));
            } else {
                error += "Password doesn't match\n";
            }
        } else {
            error += "Password must be more than 8 characters";
        }


        if (error.isEmpty()) {
            if (!fireBase.checkPharmacy("SPPH" + pharmacy_licenceID.getText().trim())) {
                pharmacy.continueCreation();
                if (fireBase.writePharmacyToFireBase(pharmacy)) {
                    sendEmailPharmacy.start();
                    JOptionPane.showMessageDialog(null, "Pharmacy Account Created Successfully\nPharmacy ID: " + pharmacy.getId() +
                            "\n( Please save this ID and use it to sign in )\n", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    moveToSignInWindow();
                }
            } else {
                JOptionPane.showMessageDialog(null, "This Pharmacy is already signed up with\n the ID: SPPH" + pharmacy_licenceID.getText().trim(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void signUpDoctor() {
        String error = "";

        if (doctor_firstName.getText().trim().isEmpty()) {
            error += "Please enter First Name\n";
        } else {
            doctor.setFirstName(doctor_firstName.getText().trim());
        }

        if (doctor_lastName.getText().trim().isEmpty()) {
            error += "Please enter Last Name\n";
        } else {
            doctor.setLastName(doctor_lastName.getText().trim());
        }

        if (doctor_gender.getSelectedItem().toString().trim().isEmpty()) {
            error += "Please enter Gender\n";
        } else {
            doctor.setGender(doctor_gender.getSelectedItem().toString().trim());
        }

        if (doctor_day.getText().trim().isEmpty()) {
            error += "Please enter day\n";
        } else {
            switch (Integer.parseInt(doctor_month.getText().trim())) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (Integer.parseInt(doctor_day.getText().trim()) > 0 && Integer.parseInt(doctor_day.getText().trim()) < 32) {
                        doctor.setDay(Integer.parseInt(doctor_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (Integer.parseInt(doctor_day.getText().trim()) > 0 && Integer.parseInt(doctor_day.getText().trim()) < 31) {
                        doctor.setDay(Integer.parseInt(doctor_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
                case 2:
                    if (Integer.parseInt(doctor_day.getText().trim()) > 0 && Integer.parseInt(doctor_day.getText().trim()) < 30 &&
                            Integer.parseInt(doctor_year.getText().trim()) % 4 == 0) {
                        doctor.setDay(Integer.parseInt(doctor_day.getText().trim()));
                    } else if (Integer.parseInt(doctor_day.getText().trim()) > 0 && Integer.parseInt(doctor_day.getText().trim()) < 29 &&
                            Integer.parseInt(doctor_year.getText().trim()) % 4 != 0) {
                        doctor.setDay(Integer.parseInt(doctor_day.getText().trim()));
                    } else {
                        error += "Invalid day\n";
                    }
                    break;
            }
        }

        if (doctor_month.getText().trim().isEmpty()) {
            error += "Please enter month\n";
        } else {
            if (Integer.parseInt(doctor_month.getText().trim()) > 0 && Integer.parseInt(doctor_month.getText().trim()) < 13) {
                doctor.setMonth(Integer.parseInt(doctor_month.getText().trim()));
            } else {
                error += "Invalid month\n";
            }
        }

        if (doctor_year.getText().trim().isEmpty()) {
            error += "Please enter year\n";
        } else {
            if (Integer.parseInt(doctor_year.getText().trim()) > 1900 && Integer.parseInt(doctor_year.getText().trim()) < new Time_Stamp().getYear()) {
                doctor.setYear(Integer.parseInt(doctor_year.getText().trim()));
            } else {
                error += "Invalid Year\n";
            }
        }

        if (doctor_speciality.getText().trim().isEmpty()) {
            error += "Please enter speciality\n";
        } else {
            doctor.setSpecialty(doctor_speciality.getText().trim());
        }

        if (doctor_mobileNumber.getText().trim().isEmpty()) {
            error += "Please enter mobile number\n";
        } else {
            doctor.setMobileNumber(doctor_mobileNumber.getText().trim());
        }

        if (doctor_clinicNumber.getText().trim().isEmpty()) {
            error += "Please enter clinic phone number\n";
        } else {
            doctor.setClinicPhoneNumber(doctor_clinicNumber.getText().trim());
        }

        if (!doctor_email.getText().trim().isEmpty()) {
            if (doctor_email.getText().trim().contains("@") && doctor_email.getText().trim().contains(".")) {
                doctor.setEmail(doctor_email.getText().trim());
            } else {
                error += "Invalid Email\n";
            }
        } else {
            error += "Please enter e-mail\n";
        }

        if (doctor_licenceID.getText().trim().isEmpty()) {
            error += "Please enter licence ID\n";
        } else {
            doctor.setLicenseId(doctor_licenceID.getText().trim());
        }


        if (doctor_password.getPassword().length >= 8) {
            if (String.valueOf(doctor_password.getPassword()).equals(String.valueOf(doctor_confirmPassword.getPassword()))) {
                doctor.passwordSet(String.valueOf(doctor_password.getPassword()));
            } else {
                error += "Password doesn't match\n";
            }
        } else {
            error += "Password must be more than 8 characters";
        }


        if (error.isEmpty()) {
            if (fireBase.checkDoctor(doctor_email.getText().trim())) {
                JOptionPane.showMessageDialog(null, "Email already exist", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                doctor.continueCreation();
                if (fireBase.writeDoctorToFireBase(doctor)) {
                    sendEmailDoctor.start();
                    JOptionPane.showMessageDialog(null, "Doctor Account Created Successfully\nDoctor ID: " + doctor.getId() +
                            "\n( Please save this ID and use it to sign in )\n", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
                    moveToSignInWindow();
                } else {
                    JOptionPane.showMessageDialog(null, "Something is Wrong", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, error, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void moveToSignInWindow() {
        windowSignUp.dispose();
        new SignInWindow().runSignIn(fireBase);
    }

    private void sendEmail(String email, String fullName, String id) {
        new SendEmail().sendGreetingEmail(email,fullName,id);
    }

    private void setUpAssignment() {
        pharmacy = new Pharmacy();
        doctor = new Doctor();
        patient = new Patient();

        Fixed1 = new JLabel("Create New ");
        Fixed1.setFont(PV.HEADING1);
        Fixed1.setForeground(PV.BLACK);
        container.add(Fixed1);

        accountType = new JComboBox<>(accountTypeStrings);
        accountType.setBackground(Color.WHITE);
        accountType.setFont(PV.HEADING2);
        accountType.setForeground(PV.BLACK);
        container.add(accountType);


        //Doctor Shit
//        doctorType = new JButton("Doctor");
//        doctorType.setFont(PV.HEADING2);
//        doctorType.setForeground(PV.BLACK);
//        container.add(doctorType);


        doctor_jFirstName = new JLabel("First Name");
        doctor_jFirstName.setFont(PV.NORMALBOLD);
        doctor_jFirstName.setForeground(PV.BLACK);
        container.add(doctor_jFirstName);

        doctor_firstName = new JTextField("");
        doctor_firstName.setFont(PV.NORMAL);
        doctor_firstName.setForeground(PV.BLACK);
        container.add(doctor_firstName);

        doctor_jLastName = new JLabel("Last Name");
        doctor_jLastName.setFont(PV.NORMALBOLD);
        doctor_jLastName.setForeground(PV.BLACK);
        container.add(doctor_jLastName);

        doctor_lastName = new JTextField();
        doctor_lastName.setFont(PV.NORMAL);
        doctor_lastName.setForeground(PV.BLACK);
        container.add(doctor_lastName);

        doctor_jGender = new JLabel("Gender");
        doctor_jGender.setFont(PV.NORMALBOLD);
        doctor_jGender.setForeground(PV.BLACK);
        container.add(doctor_jGender);

        doctor_gender = new JComboBox<>(PV.GENDERS_LIST);
        doctor_gender.setBackground(Color.WHITE);
        doctor_gender.setFont(PV.NORMAL);
        doctor_gender.setForeground(PV.BLACK);
        container.add(doctor_gender);

        doctor_jDateOfBirth = new JLabel("Birth Date");
        doctor_jDateOfBirth.setFont(PV.NORMALBOLD);
        doctor_jDateOfBirth.setForeground(PV.BLACK);
        container.add(doctor_jDateOfBirth);

        doctor_day = new JTextField();
        doctor_day.setFont(PV.NORMAL);
        doctor_day.setForeground(PV.BLACK);
        container.add(doctor_day);


        doctor_month = new JTextField();
        doctor_month.setFont(PV.NORMAL);
        doctor_month.setForeground(PV.BLACK);
        container.add(doctor_month);


        doctor_year = new JTextField();
        doctor_year.setFont(PV.NORMAL);
        doctor_year.setForeground(PV.BLACK);
        container.add(doctor_year);

        doctor_jSpeciality = new JLabel("Speciality");
        doctor_jSpeciality.setFont(PV.NORMALBOLD);
        doctor_jSpeciality.setForeground(PV.BLACK);
        container.add(doctor_jSpeciality);

        doctor_speciality = new JTextField();
        doctor_speciality.setFont(PV.NORMAL);
        doctor_speciality.setForeground(PV.BLACK);
        container.add(doctor_speciality);

        doctor_jMobileNumber = new JLabel("Mobile No.");
        doctor_jMobileNumber.setFont(PV.NORMALBOLD);
        doctor_jMobileNumber.setForeground(PV.BLACK);
        container.add(doctor_jMobileNumber);

        doctor_mobileNumber = new JTextField();
        doctor_mobileNumber.setFont(PV.NORMAL);
        doctor_mobileNumber.setForeground(PV.BLACK);
        container.add(doctor_mobileNumber);

        doctor_jclinicNumber = new JLabel("Clinic Phone No.");
        doctor_jclinicNumber.setFont(PV.NORMALBOLD);
        doctor_jclinicNumber.setForeground(PV.BLACK);
        container.add(doctor_jclinicNumber);

        doctor_clinicNumber = new JTextField();
        doctor_clinicNumber.setFont(PV.NORMAL);
        doctor_clinicNumber.setForeground(PV.BLACK);
        container.add(doctor_clinicNumber);

        doctor_jEmail = new JLabel("E-Mail");
        doctor_jEmail.setFont(PV.NORMALBOLD);
        doctor_jEmail.setForeground(PV.BLACK);
        container.add(doctor_jEmail);

        doctor_email = new JTextField();
        doctor_email.setFont(PV.NORMAL);
        doctor_email.setForeground(PV.BLACK);
        container.add(doctor_email);

        doctor_jLicenceID = new JLabel("Licence No.");
        doctor_jLicenceID.setFont(PV.NORMALBOLD);
        doctor_jLicenceID.setForeground(PV.BLACK);
        container.add(doctor_jLicenceID);

        doctor_licenceID = new JTextField();
        doctor_licenceID.setFont(PV.NORMAL);
        doctor_licenceID.setForeground(PV.BLACK);
        container.add(doctor_licenceID);

        doctor_jPassword = new JLabel("Password");
        doctor_jPassword.setFont(PV.NORMALBOLD);
        doctor_jPassword.setForeground(PV.BLACK);
        container.add(doctor_jPassword);

        doctor_password = new JPasswordField();
        doctor_password.setFont(PV.NORMAL);
        doctor_password.setForeground(PV.BLACK);
        container.add(doctor_password);

        doctor_jConfirmPassword = new JLabel("Confirm Password");
        doctor_jConfirmPassword.setFont(PV.NORMALBOLD);
        doctor_jConfirmPassword.setForeground(PV.BLACK);
        container.add(doctor_jConfirmPassword);

        doctor_confirmPassword = new JPasswordField();
        doctor_confirmPassword.setFont(PV.NORMAL);
        doctor_confirmPassword.setForeground(PV.BLACK);
        container.add(doctor_confirmPassword);


        pharmacy_jName = new JLabel("Pharmacy Name");
        pharmacy_jName.setFont(PV.NORMALBOLD);
        pharmacy_jName.setForeground(PV.BLACK);
        container.add(pharmacy_jName);

        pharmacy_name = new JTextField("");
        pharmacy_name.setFont(PV.NORMAL);
        pharmacy_name.setForeground(PV.BLACK);
        container.add(pharmacy_name);


        pharmacy_jPhoneNumber = new JLabel("Phone No.");
        pharmacy_jPhoneNumber.setFont(PV.NORMALBOLD);
        pharmacy_jPhoneNumber.setForeground(PV.BLACK);
        container.add(pharmacy_jPhoneNumber);

        pharmacy_phoneNumber = new JTextField();
        pharmacy_phoneNumber.setFont(PV.NORMAL);
        pharmacy_phoneNumber.setForeground(PV.BLACK);
        container.add(pharmacy_phoneNumber);

        pharmacy_jlicenceID = new JLabel("License No.");
        pharmacy_jlicenceID.setFont(PV.NORMALBOLD);
        pharmacy_jlicenceID.setForeground(PV.BLACK);
        container.add(pharmacy_jlicenceID);

        pharmacy_licenceID = new JTextField();
        pharmacy_licenceID.setFont(PV.NORMAL);
        pharmacy_licenceID.setForeground(PV.BLACK);
        container.add(pharmacy_licenceID);

        pharmacy_jEmail = new JLabel("Email");
        pharmacy_jEmail.setFont(PV.NORMALBOLD);
        pharmacy_jEmail.setForeground(PV.BLACK);
        container.add(pharmacy_jEmail);

        pharmacy_email = new JTextField();
        pharmacy_email.setFont(PV.NORMAL);
        pharmacy_email.setForeground(PV.BLACK);
        container.add(pharmacy_email);

        pharmacy_jPassword = new JLabel("Password");
        pharmacy_jPassword.setFont(PV.NORMALBOLD);
        pharmacy_jPassword.setForeground(PV.BLACK);
        container.add(pharmacy_jPassword);

        pharmacy_password = new JPasswordField();
        pharmacy_password.setFont(PV.NORMAL);
        pharmacy_password.setForeground(PV.BLACK);
        container.add(pharmacy_password);

        pharmacy_jConfirmPassword = new JLabel("Confirm Password");
        pharmacy_jConfirmPassword.setFont(PV.NORMALBOLD);
        pharmacy_jConfirmPassword.setForeground(PV.BLACK);
        container.add(pharmacy_jConfirmPassword);

        pharmacy_confirmPassword = new JPasswordField();
        pharmacy_confirmPassword.setFont(PV.NORMAL);
        pharmacy_confirmPassword.setForeground(PV.BLACK);
        container.add(pharmacy_confirmPassword);

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
        accountType.setBounds(xMargin + Fixed1.getPreferredSize().width + 5, yStep, accountType.getPreferredSize().width, accountType.getPreferredSize().height);


        //Line1
        yStep += Fixed1.getPreferredSize().height + lineSpacer * 4;
        doctor_jFirstName.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_firstName.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jName.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_name.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jLastName.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_lastName.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jEmail.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_email.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jGender.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_gender.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jlicenceID.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_licenceID.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jDateOfBirth.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_day.setBounds(xMargin2, yStep, 20, doctor_jFirstName.getPreferredSize().height + 3);
        doctor_month.setBounds(xMargin2 + 30, yStep, 20, doctor_jFirstName.getPreferredSize().height + 3);
        doctor_year.setBounds(xMargin2 + 60, yStep, 40, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jPhoneNumber.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_phoneNumber.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jSpeciality.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_speciality.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jPassword.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_password.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jMobileNumber.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_mobileNumber.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);
        pharmacy_jConfirmPassword.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        pharmacy_confirmPassword.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);


        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jclinicNumber.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_clinicNumber.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);



        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jLicenceID.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_licenceID.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);

        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jEmail.setBounds(xMargin, yStep, jLableWidth, doctor_jEmail.getPreferredSize().height);
        doctor_email.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jEmail.getPreferredSize().height + 3);

        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jPassword.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_password.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);

        //Line
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        doctor_jConfirmPassword.setBounds(xMargin, yStep, jLableWidth, doctor_jFirstName.getPreferredSize().height);
        doctor_confirmPassword.setBounds(xMargin2, yStep, jTextFieldWidth, doctor_jFirstName.getPreferredSize().height + 3);

        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        yStep += doctor_jFirstName.getPreferredSize().height + lineSpacer;
        signUp.setBounds((windowWidth - signUp.getPreferredSize().width) / 2, yStep, signUp.getPreferredSize().width, signUp.getPreferredSize().height);
    }

    private void setUpWindow() {
        windowSignUp = new JFrame(PV.APP_TITLE + " - Sign Up");
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

    private void setDoctorVisibility(boolean v) {
        if (v) {
            whichButton = 1;
        }
        doctor_jFirstName.setVisible(v);
        doctor_firstName.setVisible(v);
        doctor_jLastName.setVisible(v);
        doctor_lastName.setVisible(v);
        doctor_gender.setVisible(v);
        doctor_jGender.setVisible(v);
        doctor_jDateOfBirth.setVisible(v);
        doctor_day.setVisible(v);
        doctor_month.setVisible(v);
        doctor_year.setVisible(v);
        doctor_speciality.setVisible(v);
        doctor_jSpeciality.setVisible(v);
        doctor_mobileNumber.setVisible(v);
        doctor_jMobileNumber.setVisible(v);
        doctor_clinicNumber.setVisible(v);
        doctor_jclinicNumber.setVisible(v);
        doctor_licenceID.setVisible(v);
        doctor_jLicenceID.setVisible(v);
        doctor_email.setVisible(v);
        doctor_jEmail.setVisible(v);
        doctor_password.setVisible(v);
        doctor_jPassword.setVisible(v);
        doctor_confirmPassword.setVisible(v);
        doctor_jConfirmPassword.setVisible(v);
        signUp.setVisible(v);
    }

    private void setPharmacyVisibility(boolean v) {
        if (v) {
            whichButton = 2;
        }
        pharmacy_jName.setVisible(v);
        pharmacy_name.setVisible(v);
        pharmacy_phoneNumber.setVisible(v);
        pharmacy_jPhoneNumber.setVisible(v);
        pharmacy_email.setVisible(v);
        pharmacy_jEmail.setVisible(v);
        pharmacy_licenceID.setVisible(v);
        pharmacy_jlicenceID.setVisible(v);
        pharmacy_password.setVisible(v);
        pharmacy_jPassword.setVisible(v);
        pharmacy_confirmPassword.setVisible(v);
        pharmacy_jConfirmPassword.setVisible(v);
        signUp.setVisible(v);
    }

    private void setUpHeader() {

        back = new JButton("Back");
        back.setFont(PV.HEADING2);
        back.setForeground(PV.BLACK);
        container.add(back);

        back.setBounds(20, (50 - back.getPreferredSize().height) / 2, back.getPreferredSize().width, back.getPreferredSize().height);

        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setBackground(PV.HEADING_COLOR_DOCTOR);
        container.add(header);
        header.setBounds(0, 0, windowWidth, 50);
    }

}
