package smart.GUI;

import smart.BackEnd.Doctor;
import smart.BackEnd.Patient;
import smart.BackEnd.Time_Stamp;
import smart.FireStore.FireBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewSessionWindow {

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Doctor doctor;
    private Patient patient;

    //
    private JFrame windowNewSession;
    private Container container;

    private String creationTime;
    private String sessionID;

    //
    private JLabel jDoctorName;
    private JButton back;
    private JButton createSession;


    private JLabel jPatientFullName;
    private JLabel patientFullName;
    private JLabel jPatientGender;
    private JLabel patientGender;
    private JLabel jPatientDOB;
    private JLabel patientDOB;
    private JLabel jPatientIDN;
    private JLabel patientIDN;
    private JLabel jPatientEmail;
    private JLabel patientEmail;
    private JLabel jPatientPhoneNumber;
    private JLabel patientPhoneNumber;

    private JLabel jSessionDate;
    private JLabel sessionDate;
    private JLabel jSessionDoctorName;
    private JLabel sessionDoctorName;
    private JLabel jSessionMedicalCondiotion;
    private JLabel jSessionDoctorNote;
    private JTextArea sessionMedicalCondiotion;
    private JScrollPane sessionMedicalCondiotionScroll;
    private JTextArea sessionDoctorNote;
    private JScrollPane sessionDoctorNoteScroll;
    private JLabel jSessionDrugs;

    private JComboBox<String> drug1;
    private JTextField drug1Effective;
    private JTextField drug1Dosage;
    private JTextField drug1Rep;

    private JComboBox<String> drug2;
    private JTextField drug2Effective;
    private JTextField drug2Dosage;
    private JTextField drug2Rep;

    private JComboBox<String> drug3;
    private JTextField drug3Effective;
    private JTextField drug3Dosage;
    private JTextField drug3Rep;

    private JComboBox<String> drug4;
    private JTextField drug4Effective;
    private JTextField drug4Dosage;
    private JTextField drug4Rep;


    public void runNewSession(FireBase fireBase, Doctor doctor, Patient patient) {
        this.fireBase = fireBase;
        this.doctor = doctor;
        this.patient = patient;

        setUpWindow();
        setUpAssignment();
        setPlacement();
        setClickListeners();


        windowNewSession.setVisible(true);
    }

    private void setClickListeners() {
        drug1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drug1.getSelectedIndex()==0){
                    drug1Effective.setText("None");
                    drug1Effective.setEnabled(false);
                    drug1Dosage.setText("None");
                    drug1Dosage.setEnabled(false);
                    drug1Rep.setText("None");
                    drug1Rep.setEnabled(false);
                }else{
                    drug1Effective.setText(PV.DRUGS_EFFECTIVE_SUBSTANCE[drug1.getSelectedIndex()]);
                    drug1Effective.setEnabled(true);
                    drug1Effective.setEditable(false);
                    drug1Dosage.setEnabled(true);
                    drug1Dosage.setText("");
                    drug1Rep.setEnabled(true);
                    drug1Rep.setText("");
                }



            }
        });
        drug2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drug2.getSelectedIndex()==0){
                    drug2Effective.setText("None");
                    drug2Effective.setEnabled(false);
                    drug2Dosage.setText("None");
                    drug2Dosage.setEnabled(false);
                    drug2Rep.setText("None");
                    drug2Rep.setEnabled(false);
                }else{
                    drug2Effective.setText(PV.DRUGS_EFFECTIVE_SUBSTANCE[drug2.getSelectedIndex()]);
                    drug2Effective.setEnabled(true);
                    drug2Effective.setEditable(false);
                    drug2Dosage.setEnabled(true);
                    drug2Dosage.setText("");
                    drug2Rep.setEnabled(true);
                    drug2Rep.setText("");
                }



            }
        });
        drug3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drug3.getSelectedIndex()==0){
                    drug3Effective.setText("None");
                    drug3Effective.setEnabled(false);
                    drug3Dosage.setText("None");
                    drug3Dosage.setEnabled(false);
                    drug3Rep.setText("None");
                    drug3Rep.setEnabled(false);
                }else{
                    drug3Effective.setText(PV.DRUGS_EFFECTIVE_SUBSTANCE[drug3.getSelectedIndex()]);
                    drug3Effective.setEnabled(true);
                    drug3Effective.setEditable(false);
                    drug3Dosage.setEnabled(true);
                    drug3Dosage.setText("");
                    drug3Rep.setEnabled(true);
                    drug3Rep.setText("");
                }



            }
        });
        drug4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drug4.getSelectedIndex()==0){
                    drug4Effective.setText("None");
                    drug4Effective.setEnabled(false);
                    drug4Dosage.setText("None");
                    drug4Dosage.setEnabled(false);
                    drug4Rep.setText("None");
                    drug4Rep.setEnabled(false);
                }else{
                    drug4Effective.setText(PV.DRUGS_EFFECTIVE_SUBSTANCE[drug4.getSelectedIndex()]);
                    drug4Effective.setEnabled(true);
                    drug4Effective.setEditable(false);
                    drug4Dosage.setEnabled(true);
                    drug4Dosage.setText("");
                    drug4Rep.setEnabled(true);
                    drug4Rep.setText("");
                }



            }
        });
    }

    private void setUpAssignment() {

        jDoctorName = new JLabel("Dr. " + doctor.getFullName());
        jDoctorName.setFont(PV.HEADING2);
        jDoctorName.setForeground(Color.WHITE);
        container.add(jDoctorName);

        back = new JButton("Back");
        back.setFont(PV.HEADING2);
        back.setForeground(PV.BLACK);
        container.add(back);

        createSession = new JButton("Add Session");
        createSession.setFont(PV.HEADING2);
        createSession.setForeground(PV.BLACK);
        container.add(createSession);


        jPatientFullName = new JLabel("Patient Name:");
        jPatientFullName.setFont(PV.NORMALBOLD);
        jPatientFullName.setForeground(PV.BLACK);
        container.add(jPatientFullName);

        patientFullName = new JLabel(patient.getFullName());
        patientFullName.setFont(PV.NORMAL);
        patientFullName.setForeground(PV.BLACK);
        container.add(patientFullName);

        jPatientGender = new JLabel("Gender:");
        jPatientGender.setFont(PV.NORMALBOLD);
        jPatientGender.setForeground(PV.BLACK);
        container.add(jPatientGender);

        patientGender = new JLabel(patient.getGender());
        patientGender.setFont(PV.NORMAL);
        patientGender.setForeground(PV.BLACK);
        container.add(patientGender);

        jPatientDOB = new JLabel("Birth Date:");
        jPatientDOB.setFont(PV.NORMALBOLD);
        jPatientDOB.setForeground(PV.BLACK);
        container.add(jPatientDOB);

        patientDOB = new JLabel(patient.getDay() + "/" + patient.getMonth() + "/" + patient.getYear());
        patientDOB.setFont(PV.NORMAL);
        patientDOB.setForeground(PV.BLACK);
        container.add(patientDOB);

        jPatientIDN = new JLabel("Identification No.:");
        jPatientIDN.setFont(PV.NORMALBOLD);
        jPatientIDN.setForeground(PV.BLACK);
        container.add(jPatientIDN);

        patientIDN = new JLabel(patient.getIDN());
        patientIDN.setFont(PV.NORMAL);
        patientIDN.setForeground(PV.BLACK);
        container.add(patientIDN);

        jPatientEmail = new JLabel("Email:");
        jPatientEmail.setFont(PV.NORMALBOLD);
        jPatientEmail.setForeground(PV.BLACK);
        container.add(jPatientEmail);

        patientEmail = new JLabel(patient.getEmail());
        patientEmail.setFont(PV.NORMAL);
        patientEmail.setForeground(PV.BLACK);
        container.add(patientEmail);

        jPatientPhoneNumber = new JLabel("Phone No.:");
        jPatientPhoneNumber.setFont(PV.NORMALBOLD);
        jPatientPhoneNumber.setForeground(PV.BLACK);
        container.add(jPatientPhoneNumber);

        patientPhoneNumber = new JLabel(patient.getPhoneNumber());
        patientPhoneNumber.setFont(PV.NORMAL);
        patientPhoneNumber.setForeground(PV.BLACK);
        container.add(patientPhoneNumber);


        jSessionDate = new JLabel("Session Date:");
        jSessionDate.setFont(PV.NORMALBOLD);
        jSessionDate.setForeground(PV.BLACK);
        container.add(jSessionDate);

        Time_Stamp time_stamp = new Time_Stamp();
        creationTime = time_stamp.getCreationTime();
        sessionID = time_stamp.getTimeId();

        sessionDate = new JLabel(creationTime);
        sessionDate.setFont(PV.NORMAL);
        sessionDate.setForeground(PV.BLACK);
        container.add(sessionDate);

        jSessionDoctorName = new JLabel("Session Doctor:");
        jSessionDoctorName.setFont(PV.NORMALBOLD);
        jSessionDoctorName.setForeground(PV.BLACK);
        container.add(jSessionDoctorName);

        sessionDoctorName = new JLabel("Dr. " + doctor.getFullName());
        sessionDoctorName.setFont(PV.NORMAL);
        sessionDoctorName.setForeground(PV.BLACK);
        container.add(sessionDoctorName);

        jSessionMedicalCondiotion = new JLabel("Medical Condition:");
        jSessionMedicalCondiotion.setFont(PV.NORMALBOLD);
        jSessionMedicalCondiotion.setForeground(PV.BLACK);
        container.add(jSessionMedicalCondiotion);

        //Text Area
        sessionMedicalCondiotion = new JTextArea(4, 60);
        sessionMedicalCondiotion.setEditable(true);
        sessionMedicalCondiotion.setLineWrap(true);
        sessionMedicalCondiotion.setWrapStyleWord(true);
        sessionMedicalCondiotionScroll = new JScrollPane(sessionMedicalCondiotion);
        sessionMedicalCondiotionScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(sessionMedicalCondiotionScroll);


        jSessionDoctorNote = new JLabel("Doctor Note:");
        jSessionDoctorNote.setFont(PV.NORMALBOLD);
        jSessionDoctorNote.setForeground(PV.BLACK);
        container.add(jSessionDoctorNote);

        //Text Area
        sessionDoctorNote = new JTextArea(4, 60);
        sessionDoctorNote.setEditable(true);
        sessionDoctorNote.setLineWrap(true);
        sessionDoctorNote.setWrapStyleWord(true);
        sessionDoctorNoteScroll = new JScrollPane(sessionDoctorNote);
        sessionDoctorNoteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(sessionDoctorNoteScroll);

        jSessionDrugs = new JLabel("Session Drugs:");
        jSessionDrugs.setFont(PV.NORMALBOLD);
        jSessionDrugs.setForeground(PV.BLACK);
        container.add(jSessionDrugs);

        jSessionDrugs = new JLabel("Drug Name          Effective Sub.      Drug Dosage       Drug Repetition");
        jSessionDrugs.setFont(PV.NORMALBOLD);
        jSessionDrugs.setForeground(PV.BLACK);
        container.add(jSessionDrugs);

        drug1 = new JComboBox<>(PV.DRUGS);
        drug1.setFont(PV.NORMALBOLD);
        drug1.setForeground(PV.BLACK);
        drug1.setBackground(Color.WHITE);
        container.add(drug1);

        drug1Effective = new JTextField("None");
        drug1Effective.setFont(PV.NORMALBOLD);
        drug1Effective.setForeground(PV.BLACK);
        drug1Effective.setEnabled(false);
        container.add(drug1Effective);

        drug1Dosage = new JTextField("None");
        drug1Dosage.setFont(PV.NORMALBOLD);
        drug1Dosage.setForeground(PV.BLACK);
        drug1Dosage.setEnabled(false);
        container.add(drug1Dosage);

        drug1Rep = new JTextField("None");
        drug1Rep.setFont(PV.NORMALBOLD);
        drug1Rep.setForeground(PV.BLACK);
        drug1Rep.setEnabled(false);
        container.add(drug1Rep);

        drug2 = new JComboBox<>(PV.DRUGS);
        drug2.setFont(PV.NORMALBOLD);
        drug2.setBackground(Color.WHITE);
        drug2.setForeground(PV.BLACK);
        container.add(drug2);

        drug2Effective = new JTextField("None");
        drug2Effective.setFont(PV.NORMALBOLD);
        drug2Effective.setForeground(PV.BLACK);
        drug2Effective.setEnabled(false);
        container.add(drug2Effective);

        drug2Dosage = new JTextField("None");
        drug2Dosage.setFont(PV.NORMALBOLD);
        drug2Dosage.setForeground(PV.BLACK);
        drug2Dosage.setEnabled(false);
        container.add(drug2Dosage);

        drug2Rep = new JTextField("None");
        drug2Rep.setFont(PV.NORMALBOLD);
        drug2Rep.setForeground(PV.BLACK);
        drug2Rep.setEnabled(false);
        container.add(drug2Rep);

        drug3 = new JComboBox<>(PV.DRUGS);
        drug3.setFont(PV.NORMALBOLD);
        drug3.setBackground(Color.WHITE);
        drug3.setForeground(PV.BLACK);
        container.add(drug3);

        drug3Effective = new JTextField("None");
        drug3Effective.setFont(PV.NORMALBOLD);
        drug3Effective.setForeground(PV.BLACK);
        drug3Effective.setEnabled(false);
        container.add(drug3Effective);

        drug3Dosage = new JTextField("None");
        drug3Dosage.setFont(PV.NORMALBOLD);
        drug3Dosage.setForeground(PV.BLACK);
        drug3Dosage.setEnabled(false);
        container.add(drug3Dosage);

        drug3Rep = new JTextField("None");
        drug3Rep.setFont(PV.NORMALBOLD);
        drug3Rep.setForeground(PV.BLACK);
        drug3Rep.setEnabled(false);
        container.add(drug3Rep);

        drug4 = new JComboBox<>(PV.DRUGS);
        drug4.setFont(PV.NORMALBOLD);
        drug4.setBackground(Color.WHITE);
        drug4.setForeground(PV.BLACK);
        container.add(drug4);

        drug4Effective = new JTextField("None");
        drug4Effective.setFont(PV.NORMALBOLD);
        drug4Effective.setForeground(PV.BLACK);
        drug4Effective.setEnabled(false);
        container.add(drug4Effective);

        drug4Dosage = new JTextField("None");
        drug4Dosage.setFont(PV.NORMALBOLD);
        drug4Dosage.setForeground(PV.BLACK);
        drug4Dosage.setEnabled(false);
        container.add(drug4Dosage);

        drug4Rep = new JTextField("None");
        drug4Rep.setFont(PV.NORMALBOLD);
        drug4Rep.setForeground(PV.BLACK);
        drug4Rep.setEnabled(false);
        container.add(drug4Rep);


    }

    private void setPlacement() {
        int xMargin11 = 30;
        int xMargin12 = 155;
        int xMargin21 = 335;
        int xMargin22 = 420;
        int xMargin31 = 580;
        int xMargin32 = 655;
        int yMargin = 20;
        int lineSpacer = 5;
        int logo_dim = 200;
        int yStep = yMargin;

        setUpHeader();
        jDoctorName.setBounds(windowWidth - jDoctorName.getPreferredSize().width - 20, (50 - jDoctorName.getPreferredSize().height) / 2, jDoctorName.getPreferredSize().width, jDoctorName.getPreferredSize().height);
        back.setBounds(20, (50 - back.getPreferredSize().height) / 2, back.getPreferredSize().width, back.getPreferredSize().height);


        yStep = 50 + 20;


        jPatientFullName.setBounds(xMargin11, yStep, 120, jPatientFullName.getPreferredSize().height);
        patientFullName.setBounds(xMargin12, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientDOB.setBounds(xMargin21, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientDOB.setBounds(xMargin22, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientGender.setBounds(xMargin31, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientGender.setBounds(xMargin32, yStep, 170, jPatientFullName.getPreferredSize().height);

        yStep += jPatientFullName.getPreferredSize().height + lineSpacer;
        jPatientIDN.setBounds(xMargin11, yStep, 120, jPatientFullName.getPreferredSize().height);
        patientIDN.setBounds(xMargin12, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientEmail.setBounds(xMargin21, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientEmail.setBounds(xMargin22 - 35, yStep, 180, jPatientFullName.getPreferredSize().height);

        jPatientPhoneNumber.setBounds(xMargin31, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientPhoneNumber.setBounds(xMargin32, yStep, 170, jPatientFullName.getPreferredSize().height);


        yStep += jPatientPhoneNumber.getPreferredSize().height + lineSpacer;
        yStep += jPatientPhoneNumber.getPreferredSize().height + lineSpacer;
        jSessionDate.setBounds(xMargin11, yStep, 140, jSessionDate.getPreferredSize().height);
        sessionDate.setBounds(xMargin12, yStep, 300, jSessionDate.getPreferredSize().height);

        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDoctorName.setBounds(xMargin11, yStep, 150, jSessionDate.getPreferredSize().height);
        sessionDoctorName.setBounds(xMargin12, yStep, sessionDoctorName.getPreferredSize().width, jSessionDate.getPreferredSize().height);

        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionMedicalCondiotion.setBounds(xMargin11, yStep, 300, jSessionDate.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        sessionMedicalCondiotionScroll.setBounds(xMargin11, yStep, 400, 70);
        yStep += 70 + lineSpacer;
        jSessionDoctorNote.setBounds(xMargin11, yStep, 300, jSessionDoctorNote.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        sessionDoctorNoteScroll.setBounds(xMargin11, yStep, 400, 70);
        yStep += 70 + lineSpacer;
        jSessionDrugs.setBounds(xMargin11, yStep, jSessionDrugs.getPreferredSize().width, jSessionDoctorNote.getPreferredSize().height);

        yStep += jSessionDrugs.getPreferredSize().height + lineSpacer;
        drug1.setBounds(xMargin11, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug1Effective.setBounds(xMargin11 + 110, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug1Dosage.setBounds(xMargin11 + 220, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug1Rep.setBounds(xMargin11 + 330, yStep, 100, jSessionDoctorNote.getPreferredSize().height);

        yStep += jSessionDrugs.getPreferredSize().height + lineSpacer;
        drug2.setBounds(xMargin11, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug2Effective.setBounds(xMargin11 + 110, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug2Dosage.setBounds(xMargin11 + 220, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug2Rep.setBounds(xMargin11 + 330, yStep, 100, jSessionDoctorNote.getPreferredSize().height);

        yStep += jSessionDrugs.getPreferredSize().height + lineSpacer;
        drug3.setBounds(xMargin11, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug3Effective.setBounds(xMargin11 + 110, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug3Dosage.setBounds(xMargin11 + 220, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug3Rep.setBounds(xMargin11 + 330, yStep, 100, jSessionDoctorNote.getPreferredSize().height);

        yStep += jSessionDrugs.getPreferredSize().height + lineSpacer;
        drug4.setBounds(xMargin11, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug4Effective.setBounds(xMargin11 + 110, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug4Dosage.setBounds(xMargin11 + 220, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        drug4Rep.setBounds(xMargin11 + 330, yStep, 100, jSessionDoctorNote.getPreferredSize().height);
        createSession.setBounds(xMargin11 + 460, yStep-(createSession.getPreferredSize().height/2), createSession.getPreferredSize().width, createSession.getPreferredSize().height);

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

    private void setUpHeader() {
        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setBackground(PV.HEADINGCOLOR);
        container.add(header);
        header.setBounds(0, 0, windowWidth, 50);
    }


}
