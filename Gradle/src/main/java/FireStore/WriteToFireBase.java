package FireStore;

import BackEnd.Patient;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriteToFireBase {


    public WriteToFireBase() {
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
        DocumentReference docRef = db.collection("Patients").document(patient.getId());
        ApiFuture<WriteResult> result = docRef.set(patient);

        try {
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
