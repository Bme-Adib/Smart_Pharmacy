package iot.safe.FireStore;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import iot.safe.BackEnd.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireBase {

    public static final String PATIENTS = "Patients";
    public static final String DOCTORS = "Doctors";
    public static final String SESSION = "Session";
    public static final String DRUGS = "Drugs";
    public static final String PHARMACY = "Pharmacy";


    public FireBase() {
        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("credentials.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://smart-pharmacy-7ec66.firebaseio.com")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FirebaseApp.initializeApp(options);
    }

    public boolean writePatientToFireBase(Patient patient) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PATIENTS).document(patient.getId());
        ApiFuture<WriteResult> result = docRef.set(patient);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
        return success;

    }

    public boolean writeDoctorToFireBase(Doctor doctor) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(DOCTORS).document(doctor.getId());
        ApiFuture<WriteResult> result = docRef.set(doctor);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean writePharmacyToFireBase(Pharmacy pharmacy) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PHARMACY).document(pharmacy.getId());
        ApiFuture<WriteResult> result = docRef.set(pharmacy);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public ArrayList<Patient> readPatients() {

        ArrayList<Patient> patientsDB = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(PATIENTS);
        ApiFuture<QuerySnapshot> query = collectionReference.get();
        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                patientsDB.add(document.toObject(Patient.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patientsDB;
    }

    public ArrayList<Doctor> readDoctors() {
        ArrayList<Doctor> doctorsDB = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(DOCTORS);

        ApiFuture<QuerySnapshot> query = collectionReference.get();

        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                doctorsDB.add(document.toObject(Doctor.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doctorsDB;
    }

    public ArrayList<Pharmacy> readPharmacies() {
        ArrayList<Pharmacy> pharmaciesDB = new ArrayList<>();

        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(PHARMACY);

        ApiFuture<QuerySnapshot> query = collectionReference.get();

        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                pharmaciesDB.add(document.toObject(Pharmacy.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pharmaciesDB;
    }

    public boolean writeSessionToPatient(String patientID, PtSession session) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PATIENTS).document(patientID).collection(SESSION).document(session.getSessionID());
        ApiFuture<WriteResult> result = docRef.set(session);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public boolean writeDrugsToSession(String patientID, String sessionID, ArrayList<Drug> drugsList) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference colRef = db.collection(PATIENTS).document(patientID).collection(SESSION).document(sessionID).collection(DRUGS);

        ApiFuture<WriteResult> result = null;
        for (int i = 0; i < drugsList.size(); i++) {
            result = colRef.document(drugsList.get(i).getDrugName()).set(drugsList.get(i));
        }

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }

    public Pharmacy readPharmacy(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference collectionReference = db.collection(PHARMACY).document(id);
        ApiFuture<DocumentSnapshot> query = collectionReference.get();
        DocumentSnapshot querySnapshot = null;
        Pharmacy pharmacy = new Pharmacy();
        try {
            querySnapshot = query.get();
            pharmacy = querySnapshot.toObject(Pharmacy.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pharmacy;
    }

    public Doctor readDoctor(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference collectionReference = db.collection(DOCTORS).document(id);
        ApiFuture<DocumentSnapshot> query = collectionReference.get();
        DocumentSnapshot querySnapshot = null;
        Doctor doctor = new Doctor();
        try {
            querySnapshot = query.get();
            doctor = querySnapshot.toObject(Doctor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public Patient readPatient(String id) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference collectionReference = db.collection(PATIENTS).document(id);
        ApiFuture<DocumentSnapshot> query = collectionReference.get();
        DocumentSnapshot querySnapshot = null;
        Patient patient = new Patient();
        try {
            querySnapshot = query.get();
            patient = querySnapshot.toObject(Patient.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patient;
    }

    public boolean checkPharmacy(String id) {
        boolean found = false;
        if (!id.isEmpty()) {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference collectionReference = db.collection(PHARMACY);
            ApiFuture<QuerySnapshot> query = collectionReference.get();
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    if (document.getId().equals(id)) found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }

    public boolean checkDoctorID(String id) {
        boolean found = false;
        if (!id.isEmpty()) {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference collectionReference = db.collection(DOCTORS);
            ApiFuture<QuerySnapshot> query = collectionReference.get();
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    if (document.getId().equals(id)) found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }

    public boolean checkDoctor(String email) {
        boolean found = false;
        if (!email.isEmpty()) {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference collectionReference = db.collection(DOCTORS);
            ApiFuture<QuerySnapshot> query = collectionReference.get();
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    if (document.getString("email").equals(email)) found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }

    public boolean checkPatient(String email) {
        boolean found = false;
        if (!email.isEmpty()) {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference collectionReference = db.collection(PATIENTS);
            ApiFuture<QuerySnapshot> query = collectionReference.get();
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    if (document.getString("email").equals(email)) found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }

    public boolean checkPatientID(String id) {
        boolean found = false;
        if (!id.isEmpty()) {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference collectionReference = db.collection(PATIENTS);
            ApiFuture<QuerySnapshot> query = collectionReference.get();
            QuerySnapshot querySnapshot = null;
            try {
                querySnapshot = query.get();
                List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
                for (QueryDocumentSnapshot document : documents) {
                    if (document.getId().equals(id)) found = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return found;
    }


    public DefaultListModel readPatientSessions(String patientID, ArrayList<PtSession> sessionsArrayList) {

        DefaultListModel listModel = new DefaultListModel();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(PATIENTS).document(patientID).collection(SESSION);
        ApiFuture<QuerySnapshot> query = collectionReference.get();
        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                sessionsArrayList.add(document.toObject(PtSession.class));
                listModel.addElement(document.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listModel;
    }

    public ArrayList<Drug> readPatientDrugs(String patientID, String sessionID) {

        ArrayList<Drug> drugArrayList = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(PATIENTS).document(patientID).collection(SESSION).document(sessionID).collection(DRUGS);
        ApiFuture<QuerySnapshot> query = collectionReference.get();
        QuerySnapshot querySnapshot = null;

        try {
            querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                drugArrayList.add(document.toObject(Drug.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return drugArrayList;
    }

    public boolean writeDrugToSession(String patientID, String sessionID, Drug drug) {
        boolean success = false;
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference colRef = db.collection(PATIENTS).document(patientID).collection(SESSION).document(sessionID).collection(DRUGS);

        ApiFuture<WriteResult> result = null;
            result = colRef.document(drug.getDrugName()).set(drug);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
            success = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return success;
    }


}
