package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
//        Patient patient = new Patient("Abdo","AlHarweel","Male",3,4,1995,"abd@gmail.com","+4564561");

        Session session = new Session("parkinson", "Doctor Adib", "Not Healthy");

        ArrayList<Drug> drugList = new ArrayList<>();
        drugList.add(new Drug("Aspirin", "", 10, 3));
        drugList.add(new Drug("Cetamol", "", 20, 1));
        drugList.add(new Drug("Baby oil", "", 20, 10));


//        FireBase fireBase = new FireBase();
//        fireBase.writePatientToFireBase(patient);
//        fireBase.writeSessionToPatient(patient.getId(),session);
//        fireBase.writeDrugsToSession(patient.getId(),session.getSessionID(),drugList);

        String patientID = "SPPT9504036010";
        FireBase fireBase = new FireBase();
        fireBase.writeSessionToPatient(patientID, session);
        fireBase.writeDrugsToSession(patientID, session.getSessionID(), drugList);
    }
}
