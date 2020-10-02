package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
        ArrayList<Patient> patientsDB = new ArrayList<>();
        patientsDB = fireBase.readPatients();
        boolean patientFound=false;

        Patient patient = new Patient();

        for (int i=0;i<patientsDB.size();i++){
            if(patientsDB.get(i).getId().equals("SPPT9301318770")){
                patient = patientsDB.get(i);
                patientFound=true;
            }
        }
        if (!patientFound) {
            System.out.println("Patient Not Found");
        }

        if (patientFound){
            System.out.println("First Name: " + patient.getFullName());
            System.out.println("Gender: " + patient.getGender());
            patient.setGender("Female");
            System.out.println("Gender after: " + patient.getGender());
            fireBase.writePatientToFireBase(patient);
        }


    }

    //add email and phone number for patient

    //SPPT9301312295
}