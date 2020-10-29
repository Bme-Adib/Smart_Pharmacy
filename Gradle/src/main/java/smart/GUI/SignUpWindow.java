package smart.GUI;

import smart.BackEnd.Doctor;
import smart.BackEnd.Pharmacy;
import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;

public class SignUpWindow {


    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Doctor doctor;
    //
    private JFrame windowSignUp;
    private Container container;
    //
    private JButton doctorType;
    private JButton pharmacyType;
    private JButton patientType;
    // Doctor Shit
    private JLabel doctorFixed;
    private JLabel doctor_jFirstName;
    private JTextField doctor_firstName;
    private JLabel doctor_jLastName;
    private JTextField doctor_lastName;
    private JLabel doctor_jGender;
    private JTextField doctor_gender;
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


    // Patient Shit
    private JLabel patientFixed;
    private JLabel patient_jFirstName;
    private JTextField patient_firstName;
    private JLabel patient_jLastName;
    private JTextField patient_lastName;
    private JLabel patient_jGender;
    private JTextField patient_gender;
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


    // Pharmacy Shit
    private JLabel pharmacyFixed;
    private JLabel pharmacy_jName;
    private JTextField pharmacy_Name;
    private JLabel pharmacy_jPhoneNumber;
    private JTextField pharmacy_phoneNumber;
    private JLabel pharmacy_jID;
    private JTextField pharmacy_ID;
    private JLabel pharmacy_jPassword;
    private JPasswordField pharmacy_password;




    public void runSignUp(FireBase fireBase) {
        this.fireBase = fireBase;
        setUpWindow();
        setUpAssignment();
        setPlacement();
        setClickListeners();


        windowSignUp.setVisible(true);
    }

    private void setClickListeners() {

    }

    private void setUpAssignment() {


      //Doctor Shit
        doctorType = new JButton("Doctor");
        doctorType.setFont(PV.HEADING2);
        doctorType.setForeground(PV.BLACK);
        container.add(doctorType);

        doctorFixed = new JLabel("Create New Doctor Account:");
        doctorFixed.setFont(PV.HEADING1);
        doctorFixed.setForeground(PV.BLACK);
        container.add(doctorFixed);



        doctor_jFirstName = new JLabel("First Name");
        doctor_jFirstName.setFont(PV.NORMALBOLD);
        doctor_jFirstName.setForeground(PV.BLACK);
        container.add(doctor_jFirstName);

        doctor_firstName = new JTextField("Adib Ghannam");
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

        doctor_gender = new JTextField();
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


        patientFixed = new JLabel("Create New Patient Account:");
        patientFixed.setFont(PV.HEADING1);
        patientFixed.setForeground(PV.BLACK);
        container.add(patientFixed);





        //Patient  Shit
        patientType = new JButton("Patient");
        patientType.setFont(PV.HEADING2);
        patientType.setForeground(PV.BLACK);
        container.add(patientType);


        patientFixed = new JLabel("Create New Patient Account:");
        patientFixed.setFont(PV.HEADING1);
        patientFixed.setForeground(PV.BLACK);
        container.add(patientFixed);


        patient_jFirstName = new JLabel("First Name");
        patient_jFirstName.setFont(PV.NORMALBOLD);
        patient_jFirstName.setForeground(PV.BLACK);
        container.add(patient_jFirstName);

        patient_firstName = new JTextField("Adib Ghannam");
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

        patient_gender = new JTextField();
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

        patient_jID = new JLabel("ID No.");
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



        //Pharmacy  Shit
        pharmacyType = new JButton("Pharmacy");
        pharmacyType.setFont(PV.HEADING2);
        pharmacyType.setForeground(PV.BLACK);
        container.add(pharmacyType);

        pharmacyFixed = new JLabel("Create New Pharmacy Account:");
        pharmacyFixed.setFont(PV.HEADING1);
        pharmacyFixed.setForeground(PV.BLACK);
        container.add(pharmacyFixed);

        pharmacy_jName = new JLabel("Pharmacy Name");
        pharmacy_jName.setFont(PV.NORMALBOLD);
        pharmacy_jName.setForeground(PV.BLACK);
        container.add(pharmacy_jName);

        pharmacy_Name = new JTextField("Adib Ghannam");
        pharmacy_Name.setFont(PV.NORMAL);
        pharmacy_Name.setForeground(PV.BLACK);
        container.add(pharmacy_Name);


        pharmacy_jPhoneNumber = new JLabel("Pharmacy Phone No.");
        pharmacy_jPhoneNumber.setFont(PV.NORMALBOLD);
        pharmacy_jPhoneNumber.setForeground(PV.BLACK);
        container.add(pharmacy_jPhoneNumber);

        pharmacy_phoneNumber = new JTextField();
        pharmacy_phoneNumber.setFont(PV.NORMAL);
        pharmacy_phoneNumber.setForeground(PV.BLACK);
        container.add(pharmacy_phoneNumber);

        pharmacy_jID = new JLabel("License No.");
        pharmacy_jID.setFont(PV.NORMALBOLD);
        pharmacy_jID.setForeground(PV.BLACK);
        container.add(pharmacy_jID);

        pharmacy_ID = new JTextField();
        pharmacy_ID.setFont(PV.NORMAL);
        pharmacy_ID.setForeground(PV.BLACK);
        container.add(pharmacy_ID);

        pharmacy_jPassword = new JLabel("Password");
        pharmacy_jPassword.setFont(PV.NORMALBOLD);
        pharmacy_jPassword.setForeground(PV.BLACK);
        container.add(pharmacy_jPassword);

        pharmacy_password = new JPasswordField();
        pharmacy_password.setFont(PV.NORMAL);
        pharmacy_password.setForeground(PV.BLACK);
        container.add(pharmacy_password);
    }

    private void setPlacement() {
        int xMargin = 50;

        int yMargin = 20;
        int lineSpacer = 5;
        int logo_dim = 200;
        int yStep = yMargin;
        int jLableWidth = 110;
        int jTextFieldWidth = 200;
        int xMargin2 = xMargin+ jLableWidth +10;


        doctorType.setBounds(50 + 440 - 300, yStep, doctorType.getPreferredSize().width, doctorType.getPreferredSize().height);
        pharmacyType.setBounds(50 + 440 - 150, yStep, pharmacyType.getPreferredSize().width, pharmacyType.getPreferredSize().height);
        patientType.setBounds(50 + 460, yStep, patientType.getPreferredSize().width, patientType.getPreferredSize().height);

        //Line
        yStep+=doctorType.getPreferredSize().height+lineSpacer;
        yStep+=doctorType.getPreferredSize().height+lineSpacer;
        doctorFixed.setBounds(xMargin,yStep,doctorFixed.getPreferredSize().width,doctorFixed.getPreferredSize().height);

        //Line
        yStep+=doctorFixed.getPreferredSize().height+lineSpacer*4;
        doctor_jFirstName.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_firstName.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jLastName.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_lastName.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jGender.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_gender.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jDateOfBirth.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_day.setBounds(xMargin2,yStep,20,doctor_jFirstName.getPreferredSize().height+3);
        doctor_month.setBounds(xMargin2 + 30,yStep,20,doctor_jFirstName.getPreferredSize().height+3);
        doctor_year.setBounds(xMargin2 +60,yStep,40,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jSpeciality.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_speciality.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jMobileNumber.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_mobileNumber.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jclinicNumber.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_clinicNumber.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jLicenceID.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_licenceID.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);

        //Line
        yStep+=doctor_jFirstName.getPreferredSize().height+lineSpacer;
        doctor_jPassword.setBounds(xMargin,yStep,jLableWidth,doctor_jFirstName.getPreferredSize().height);
        doctor_password.setBounds(xMargin2,yStep,jTextFieldWidth,doctor_jFirstName.getPreferredSize().height+3);
    }

    private void setUpWindow() {
        windowSignUp = new JFrame(PV.APP_TITLE);
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

}
