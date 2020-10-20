package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Test3 {
    public static void main(String[] args) {
        String patientID = "SPPT9301319398";

        String medicalCondition = "The Patient suffers from a terable headache";
        String doctorNots = "Get Some Rest for 5 hours a day";
        Session session = new Session(medicalCondition,"Dr. John Doe",doctorNots);


        ArrayList<Drug> drugsList = new ArrayList<>();

        Drug drug1 = new Drug("Paracetamol","Paracetamol",50,3);
        Drug drug2 = new Drug("Aspren","aspren",10,2);

        drugsList.add(drug1);
        drugsList.add(drug2);


        FireBase fireBase = new FireBase();
        fireBase.writeSessionToPatient(patientID,session);
        fireBase.writeDrugsToSession(patientID,session.getSessionID(),drugsList);

    }
}
