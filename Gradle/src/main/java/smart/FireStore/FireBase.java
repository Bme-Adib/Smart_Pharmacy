package smart.FireStore;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import smart.BackEnd.*;

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

    public void writePatientToFireBase(Patient patient) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PATIENTS).document(patient.getId());
        ApiFuture<WriteResult> result = docRef.set(patient);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    public void writeDoctorToFireBase(Doctor doctor) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(DOCTORS).document(doctor.getId());
        ApiFuture<WriteResult> result = docRef.set(doctor);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public void writePharmacyToFireBase(Pharmacy pharmacy) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PHARMACY).document(pharmacy.getId());
        ApiFuture<WriteResult> result = docRef.set(pharmacy);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public ArrayList<Pharmacy> readPharmacy() {
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

    public void writeSessionToPatient(String patientID, PtSession session) {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(PATIENTS).document(patientID).collection(SESSION).document(session.getSessionID());
        ApiFuture<WriteResult> result = docRef.set(session);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    public void writeDrugsToSession(String patientID, String sessionID, ArrayList<Drug> drugsList) {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference colRef = db.collection(PATIENTS).document(patientID).collection(SESSION).document(sessionID).collection(DRUGS);

        ApiFuture<WriteResult> result = null;
        for (int i=0;i<drugsList.size();i++){
            result = colRef.document(drugsList.get(i).getDrugName()).set(drugsList.get(i));
        }

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ;
        }

    }

    public boolean checkDoctor(String id) {
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
}
