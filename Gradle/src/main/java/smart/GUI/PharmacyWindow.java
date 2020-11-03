package smart.GUI;

import smart.BackEnd.Drug;
import smart.BackEnd.Patient;
import smart.BackEnd.Pharmacy;
import smart.BackEnd.PtSession;
import smart.FireStore.FireBase;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PharmacyWindow {

    private int windowWidth;
    private int windowHeight;
    private FireBase fireBase;
    private Pharmacy pharmacy;
    private Patient patient;

    //
    private JFrame windowDoctor;
    private Container container;

    //
    private JLabel jPharmacyName;
    private JLabel jPatientID;
    private JTextField patientID;
    private JLabel jFamilyName;
    private JTextField familyName;
    private JButton searchPatient;
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
    private JLabel jFixed_SessionsList;
    private JLabel jFixed_SessionsDetails;

    //
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

    private JLabel jSessionDrug1;
    private JLabel jSessionDrug2;
    private JLabel jSessionDrug3;
    private JLabel jSessionDrug4;
    private ArrayList<JLabel> drugsLabels;

    private JButton drugDispense1;
    private JButton drugDispense2;
    private JButton drugDispense3;
    private JButton drugDispense4;
    private ArrayList<JButton> dispenseButtons;


    private JList jSessionsList;
    private Border blackline;
    private ArrayList<PtSession> sessionsArrayList = new ArrayList<>();
    private ArrayList<Drug> drugArrayList = new ArrayList<>();


    public void runPharmacy(FireBase fireBase, Pharmacy pharmacy, Patient patient) {
        this.fireBase = fireBase;
        this.pharmacy = pharmacy;
        this.patient = patient;
        setUpWindow();
        setUpAssignment();
        setPatientVisibility(false);
        setPlacement();
        setClickListeners();
        setIfHasPreviousPatient();


        windowDoctor.setVisible(true);
    }

    private void setIfHasPreviousPatient() {
        if (patient != null) {
            patientID.setText(patient.getId());
            familyName.setText(patient.getLastName());
            jFamilyName.setEnabled(false);
            jPatientID.setEnabled(false);
            patientID.setEnabled(false);
            searchPatientMethod();
        }
    }

    private void setClickListeners() {
        searchPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchPatientMethod();
            }
        });

    }

    private void searchPatientMethod() {
        if (searchPatient.getText().equals("Search New Patient")) {
            windowDoctor.dispose();
            patient = null;
            new PharmacyWindow().runPharmacy(fireBase, pharmacy, patient);
        } else {
            if (fireBase.checkPatientID(patientID.getText().trim())) {
                patient = fireBase.readPatient(patientID.getText().trim());
                if (familyName.getText().trim().toLowerCase().equals(patient.getLastName().toLowerCase())) {
                    patientFullName.setText(patient.getFullName());
                    patientGender.setText(patient.getGender());
                    patientDOB.setText(patient.getDay() + "/" + patient.getMonth() + "/" + patient.getYear());
                    patientEmail.setText(patient.getEmail());
                    patientIDN.setText(patient.getIDN());
                    patientPhoneNumber.setText(patient.getPhoneNumber());
                    sessionDate.setText("");
                    sessionDoctorName.setText("");
                    sessionMedicalCondiotion.setText("");
                    sessionDoctorNote.setText("");


                    setPatientVisibility(true);
                    setSessionsList();
                    jPatientID.setEnabled(false);
                    jFamilyName.setEnabled(false);
                    patientID.setEnabled(false);
                    familyName.setEnabled(false);
                    searchPatient.setText("Search New Patient");
                    searchPatient.setBounds(492, 80, searchPatient.getPreferredSize().width, searchPatient.getPreferredSize().height);
                } else {

                }
            } else {

            }
        }

    }

    private void setUpAssignment() {
        blackline = BorderFactory.createLineBorder(Color.black, 1, true);

        jPharmacyName = new JLabel(pharmacy.getPharmacyName());
        jPharmacyName.setFont(PV.HEADING2);
        jPharmacyName.setForeground(Color.WHITE);
        container.add(jPharmacyName);

        jPatientID = new JLabel("Patient ID");
        jPatientID.setFont(PV.HEADING2);
        jPatientID.setForeground(PV.BLACK);
        container.add(jPatientID);

        patientID = new JTextField("SPPT9306059982");
        patientID.setFont(PV.HEADING2);
        patientID.setForeground(PV.BLACK);
        container.add(patientID);

        jFamilyName = new JLabel("Family Name");
        jFamilyName.setFont(PV.HEADING2);
        jFamilyName.setForeground(PV.BLACK);
        container.add(jFamilyName);

        familyName = new JTextField("Ghannam");
        familyName.setFont(PV.HEADING2);
        familyName.setForeground(PV.BLACK);
        container.add(familyName);

        searchPatient = new JButton("Search Patient");
        searchPatient.setFont(PV.HEADING2);
        searchPatient.setForeground(PV.BLACK);
        container.add(searchPatient);

        jPatientFullName = new JLabel("Patient Name:");
        jPatientFullName.setFont(PV.NORMALBOLD);
        jPatientFullName.setForeground(PV.BLACK);
        container.add(jPatientFullName);

        patientFullName = new JLabel("");
        patientFullName.setFont(PV.NORMAL);
        patientFullName.setForeground(PV.BLACK);
        container.add(patientFullName);

        jPatientGender = new JLabel("Gender:");
        jPatientGender.setFont(PV.NORMALBOLD);
        jPatientGender.setForeground(PV.BLACK);
        container.add(jPatientGender);

        patientGender = new JLabel("");
        patientGender.setFont(PV.NORMAL);
        patientGender.setForeground(PV.BLACK);
        container.add(patientGender);

        jPatientDOB = new JLabel("Birth Date:");
        jPatientDOB.setFont(PV.NORMALBOLD);
        jPatientDOB.setForeground(PV.BLACK);
        container.add(jPatientDOB);

        patientDOB = new JLabel("");
        patientDOB.setFont(PV.NORMAL);
        patientDOB.setForeground(PV.BLACK);
        container.add(patientDOB);

        jPatientIDN = new JLabel("Identification No.:");
        jPatientIDN.setFont(PV.NORMALBOLD);
        jPatientIDN.setForeground(PV.BLACK);
        container.add(jPatientIDN);

        patientIDN = new JLabel("");
        patientIDN.setFont(PV.NORMAL);
        patientIDN.setForeground(PV.BLACK);
        container.add(patientIDN);

        jPatientEmail = new JLabel("Email:");
        jPatientEmail.setFont(PV.NORMALBOLD);
        jPatientEmail.setForeground(PV.BLACK);
        container.add(jPatientEmail);

        patientEmail = new JLabel("");
        patientEmail.setFont(PV.NORMAL);
        patientEmail.setForeground(PV.BLACK);
        container.add(patientEmail);

        jPatientPhoneNumber = new JLabel("Phone No.:");
        jPatientPhoneNumber.setFont(PV.NORMALBOLD);
        jPatientPhoneNumber.setForeground(PV.BLACK);
        container.add(jPatientPhoneNumber);

        patientPhoneNumber = new JLabel("");
        patientPhoneNumber.setFont(PV.NORMAL);
        patientPhoneNumber.setForeground(PV.BLACK);
        container.add(patientPhoneNumber);

        jFixed_SessionsList = new JLabel("Sessions List");
        jFixed_SessionsList.setFont(PV.HEADING2);
        jFixed_SessionsList.setForeground(PV.HEADING_COLOR_PHARMACY);
        container.add(jFixed_SessionsList);

        jFixed_SessionsDetails = new JLabel("Session Details");
        jFixed_SessionsDetails.setFont(PV.HEADING2);
        jFixed_SessionsDetails.setForeground(PV.HEADING_COLOR_PHARMACY);
        container.add(jFixed_SessionsDetails);

        jSessionDate = new JLabel("Session Date:");
        jSessionDate.setFont(PV.NORMALBOLD);
        jSessionDate.setForeground(PV.BLACK);
        container.add(jSessionDate);

        sessionDate = new JLabel("05/12/2020, 11:50");
        sessionDate.setFont(PV.NORMAL);
        sessionDate.setForeground(PV.BLACK);
        container.add(sessionDate);

        jSessionDoctorName = new JLabel("Session Doctor:");
        jSessionDoctorName.setFont(PV.NORMALBOLD);
        jSessionDoctorName.setForeground(PV.BLACK);
        container.add(jSessionDoctorName);

        sessionDoctorName = new JLabel("DR. Ery");
        sessionDoctorName.setFont(PV.NORMAL);
        sessionDoctorName.setForeground(PV.BLACK);
        container.add(sessionDoctorName);

        jSessionMedicalCondiotion = new JLabel("Medical Condition:");
        jSessionMedicalCondiotion.setFont(PV.NORMALBOLD);
        jSessionMedicalCondiotion.setForeground(PV.BLACK);
        container.add(jSessionMedicalCondiotion);

        //Text Area
        sessionMedicalCondiotion = new JTextArea(4, 60);
        sessionMedicalCondiotion.setEditable(false);
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
        sessionDoctorNote.setEditable(false);
        sessionDoctorNote.setLineWrap(true);
        sessionDoctorNote.setWrapStyleWord(true);
        sessionDoctorNoteScroll = new JScrollPane(sessionDoctorNote);
        sessionDoctorNoteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        container.add(sessionDoctorNoteScroll);


        jSessionDrugs = new JLabel("Session Drugs:");
        jSessionDrugs.setFont(PV.NORMALBOLD);
        jSessionDrugs.setForeground(PV.BLACK);
        container.add(jSessionDrugs);

        jSessionDrug1 = new JLabel("ASDASD");
        jSessionDrug1.setFont(PV.NORMALBOLD);
        jSessionDrug1.setForeground(PV.BLACK);
        jSessionDrug1.setVisible(false);
        container.add(jSessionDrug1);

        jSessionDrug2 = new JLabel();
        jSessionDrug2.setFont(PV.NORMALBOLD);
        jSessionDrug2.setForeground(PV.BLACK);
        jSessionDrug2.setVisible(false);
        container.add(jSessionDrug2);

        jSessionDrug3 = new JLabel();
        jSessionDrug3.setFont(PV.NORMALBOLD);
        jSessionDrug3.setForeground(PV.BLACK);
        jSessionDrug3.setVisible(false);
        container.add(jSessionDrug3);

        jSessionDrug4 = new JLabel();
        jSessionDrug4.setFont(PV.NORMALBOLD);
        jSessionDrug4.setVisible(false);
        jSessionDrug4.setForeground(PV.BLACK);
        container.add(jSessionDrug4);

        drugsLabels = new ArrayList<>();
        drugsLabels.add(jSessionDrug1);
        drugsLabels.add(jSessionDrug2);
        drugsLabels.add(jSessionDrug3);
        drugsLabels.add(jSessionDrug4);

        drugDispense1 = new JButton("Dispense");
        drugDispense1.setFont(PV.HEADING2);
        drugDispense1.setVisible(false);
        drugDispense1.setForeground(PV.BLACK);
        container.add(drugDispense1);

        drugDispense2 = new JButton("Dispense");
        drugDispense2.setFont(PV.HEADING2);
        drugDispense2.setVisible(false);
        drugDispense2.setForeground(PV.BLACK);
        container.add(drugDispense2);

        drugDispense3 = new JButton("Dispense");
        drugDispense3.setFont(PV.HEADING2);
        drugDispense3.setVisible(false);
        drugDispense3.setForeground(PV.BLACK);
        container.add(drugDispense3);

        drugDispense4 = new JButton("Dispense");
        drugDispense4.setFont(PV.HEADING2);
        drugDispense4.setVisible(false);
        drugDispense4.setForeground(PV.BLACK);
        container.add(drugDispense4);

        dispenseButtons = new ArrayList<>();
        dispenseButtons.add(drugDispense1);
        dispenseButtons.add(drugDispense2);
        dispenseButtons.add(drugDispense3);
        dispenseButtons.add(drugDispense4);


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

        //Header
        setUpHeader();
        jPharmacyName.setBounds(windowWidth - jPharmacyName.getPreferredSize().width - 20, (50 - jPharmacyName.getPreferredSize().height) / 2, jPharmacyName.getPreferredSize().width, jPharmacyName.getPreferredSize().height);

        //Other Window

        yStep = 50 + 20;
        jPatientID.setBounds((windowWidth - 455) / 2, yStep, 100, jPatientID.getPreferredSize().height);
        patientID.setBounds((windowWidth - 455) / 2 + 100, yStep, 200, jPatientID.getPreferredSize().height);
        searchPatient.setBounds((windowWidth - 455) / 2 + 320, yStep + 10, searchPatient.getPreferredSize().width, searchPatient.getPreferredSize().height);

        yStep += patientID.getPreferredSize().height + lineSpacer;
        jFamilyName.setBounds((windowWidth - 455) / 2, yStep, 100, jPatientID.getPreferredSize().height);
        familyName.setBounds((windowWidth - 455) / 2 + 100, yStep, 200, jPatientID.getPreferredSize().height);

        yStep += familyName.getPreferredSize().height + lineSpacer;
        yStep += familyName.getPreferredSize().height + lineSpacer;
        jPatientFullName.setBounds(xMargin11, yStep, 120, jPatientFullName.getPreferredSize().height);
        patientFullName.setBounds(xMargin12, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientDOB.setBounds(xMargin21, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientDOB.setBounds(xMargin22, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientGender.setBounds(xMargin31, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientGender.setBounds(xMargin32, yStep, 170, jPatientFullName.getPreferredSize().height);

        yStep += familyName.getPreferredSize().height + lineSpacer;
        jPatientIDN.setBounds(xMargin11, yStep, 120, jPatientFullName.getPreferredSize().height);
        patientIDN.setBounds(xMargin12, yStep, 170, jPatientFullName.getPreferredSize().height);

        jPatientEmail.setBounds(xMargin21, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientEmail.setBounds(xMargin22 - 35, yStep, 180, jPatientFullName.getPreferredSize().height);

        jPatientPhoneNumber.setBounds(xMargin31, yStep, 90, jPatientFullName.getPreferredSize().height);
        patientPhoneNumber.setBounds(xMargin32, yStep, 170, jPatientFullName.getPreferredSize().height);

        yStep += familyName.getPreferredSize().height + lineSpacer + 5;
        jFixed_SessionsList.setBounds(xMargin11, yStep, jFixed_SessionsList.getPreferredSize().width, jFixed_SessionsList.getPreferredSize().height);

        yStep += jFixed_SessionsList.getPreferredSize().height + lineSpacer;
        int XmarginSession11 = xMargin11 + 150 + 20;
        int XmarginSession12 = xMargin11 + 150 + 20 + 130;
        jFixed_SessionsDetails.setBounds(XmarginSession11, yStep, jFixed_SessionsDetails.getPreferredSize().width, jFixed_SessionsDetails.getPreferredSize().height);


        yStep += jFixed_SessionsDetails.getPreferredSize().height + lineSpacer;
        jSessionDate.setBounds(XmarginSession11, yStep, 140, jSessionDate.getPreferredSize().height);
        sessionDate.setBounds(XmarginSession12, yStep, 300, jSessionDate.getPreferredSize().height);

        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDoctorName.setBounds(XmarginSession11, yStep, 150, jSessionDate.getPreferredSize().height);
        sessionDoctorName.setBounds(XmarginSession12, yStep, 300, jSessionDate.getPreferredSize().height);

        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionMedicalCondiotion.setBounds(XmarginSession11, yStep, 140, jSessionDate.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        sessionMedicalCondiotionScroll.setBounds(XmarginSession11, yStep, 400, 70);
        yStep += 70 + lineSpacer;
        jSessionDoctorNote.setBounds(XmarginSession11, yStep, 140, jSessionDoctorNote.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        sessionDoctorNoteScroll.setBounds(XmarginSession11, yStep, 400, 70);
        yStep += 70 + lineSpacer;
        jSessionDrugs.setBounds(XmarginSession11, yStep, 140, jSessionDoctorNote.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDrug1.setBounds(XmarginSession11, yStep, 350, jSessionDrug1.getPreferredSize().height);
        drugDispense1.setBounds(XmarginSession11 +350 +20, yStep, drugDispense1.getPreferredSize().width, jSessionDrug1.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDrug2.setBounds(XmarginSession11, yStep, 350, jSessionDrug1.getPreferredSize().height);
        drugDispense2.setBounds(XmarginSession11 +350 +20, yStep, drugDispense1.getPreferredSize().width, jSessionDrug1.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDrug3.setBounds(XmarginSession11, yStep, 350, jSessionDrug1.getPreferredSize().height);
        drugDispense3.setBounds(XmarginSession11 +350 +20, yStep, drugDispense1.getPreferredSize().width, jSessionDrug1.getPreferredSize().height);
        yStep += jSessionDate.getPreferredSize().height + lineSpacer;
        jSessionDrug4.setBounds(XmarginSession11, yStep, 350, jSessionDrug1.getPreferredSize().height);
        drugDispense4.setBounds(XmarginSession11 +350 +20, yStep, drugDispense1.getPreferredSize().width, jSessionDrug1.getPreferredSize().height);

    }

    private void setUpHeader() {
        JLabel header = new JLabel();
        header.setOpaque(true);
        header.setBackground(PV.HEADING_COLOR_PHARMACY);
        container.add(header);
        header.setBounds(0, 0, windowWidth, 50);
    }

    private void setUpWindow() {
        windowDoctor = new JFrame(PV.APP_TITLE + " - Pharmacy Portal");
        windowWidth = PV.WINDOW_WIDTH;
        windowHeight = PV.WINDOW_HEIGHT + 100;
        windowDoctor.setSize(windowWidth, windowHeight);
        windowDoctor.setResizable(false);
//        windowSignIn.setUndecorated(true);
        windowDoctor.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        windowDoctor.setLocation((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2);
        container = windowDoctor.getContentPane();
        container.setBackground(PV.APPLICATION_BACKGROUND);
        container.setLayout(null);
    }

    private void setPatientVisibility(boolean v) {
        jPatientFullName.setVisible(v);
        patientFullName.setVisible(v);
        jPatientDOB.setVisible(v);
        patientDOB.setVisible(v);
        jPatientGender.setVisible(v);
        patientGender.setVisible(v);
        jPatientIDN.setVisible(v);
        patientIDN.setVisible(v);
        jPatientEmail.setVisible(v);
        patientEmail.setVisible(v);
        jPatientPhoneNumber.setVisible(v);
        patientPhoneNumber.setVisible(v);
        jFixed_SessionsList.setVisible(v);
        jFixed_SessionsDetails.setVisible(v);
        jSessionDate.setVisible(v);
        sessionDate.setVisible(v);
        jSessionDoctorName.setVisible(v);
        sessionDoctorName.setVisible(v);
        jSessionMedicalCondiotion.setVisible(v);
        sessionMedicalCondiotionScroll.setVisible(v);
        jSessionDoctorNote.setVisible(v);
        sessionDoctorNoteScroll.setVisible(v);
        jSessionDrugs.setVisible(v);

    }

    private void setSessionsList() {
        jSessionsList = new JList();
        DefaultListModel sessionsList = new DefaultListModel();
        sessionsList = fireBase.readPatientSessions(patientID.getText().trim(), sessionsArrayList);
        System.out.println("Session: " + sessionsList.size());
//        sessionsList = fireBase.readPatientSessions("SPPT9306059982", sessionsArrayList);
        jSessionsList = new JList(sessionsList);
        jSessionsList.setBorder(blackline);
        jSessionsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        jSessionsList.setLayoutOrientation(JList.VERTICAL);
        jSessionsList.setFont(PV.NORMALBOLD);
        container.add(jSessionsList);
        jSessionsList.setBounds(30, 257, 150, 367);

        jSessionsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    sessionDate.setText("");
                    sessionDoctorName.setText("");
                    sessionMedicalCondiotion.setText("");
                    sessionDoctorNote.setText("");


                    int index = jSessionsList.getSelectedIndex();
                    sessionDate.setText(sessionsArrayList.get(index).getSessionTimeStamp());
                    sessionDoctorName.setText(sessionsArrayList.get(index).getDoctorsName());
                    sessionMedicalCondiotion.setText(sessionsArrayList.get(index).getMedicalCondition());
                    sessionDoctorNote.setText(sessionsArrayList.get(index).getDoctorsNotes());
                    drugArrayList = fireBase.readPatientDrugs(patientID.getText().trim(), sessionsArrayList.get(index).getSessionID());
//                    drugArrayList = fireBase.readPatientDrugs("SPPT9306059982",sessionsArrayList.get(index).getSessionID());

                    for (int i = 0; i < drugsLabels.size(); i++) {
                        drugsLabels.get(i).setText("");
                        drugsLabels.get(i).setVisible(false);
                        dispenseButtons.get(i).setVisible(false);
                    }

                    if (drugArrayList.size() != 0) {


                        for (int i = 0; i < drugArrayList.size(); i++) {
                            drugsLabels.get(i).setVisible(true);
                            drugsLabels.get(i).setText((i+1) + "- " +drugArrayList.get(i).getDrugName() + " (" + drugArrayList.get(i).getDrugEffectiveSubstance()
                                    + " )  Dosage: " + drugArrayList.get(i).getDosage() + " mg  Rep: (" + drugArrayList.get(i).getRepetitionUsed() +
                                    "/" + drugArrayList.get(i).getRepetition() + ")");
                            dispenseButtons.get(i).setVisible(true);
                        }
                    }else {
                        drugsLabels.get(0).setVisible(true);
                        drugsLabels.get(0).setText("No drugs are prescribed");
                    }


                }
            }
        });

    }

}
