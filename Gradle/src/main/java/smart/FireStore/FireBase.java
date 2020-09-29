package smart.FireStore;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import smart.BackEnd.Doctor;
import smart.BackEnd.Patient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FireBase {

    public static final String PATIENTS = "Patients";
    public static final String DOCTORS = "Doctors";


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
}
