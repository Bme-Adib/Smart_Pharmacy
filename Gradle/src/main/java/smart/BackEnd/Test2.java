package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
        ArrayList<Patient> patientsDataBase = fireBase.readPatients();

        System.out.println("Patient List");
        for (int i=0;i<patientsDataBase.size();i++){
            System.out.print("Patient ID: ");
            System.out.print(patientsDataBase.get(i).getId());

            System.out.print(", Patient Name: ");
            System.out.println(patientsDataBase.get(i).getFullName());
        }
        System.out.println();


        System.out.println("Doctors List");
        ArrayList<Doctor> doctorDataBase = fireBase.readDoctors();
        for (int i=0;i<doctorDataBase.size();i++){
            System.out.print("Doctor ID: ");
            System.out.print(doctorDataBase.get(i).getId());

            System.out.print(", Doctor Name: ");
            System.out.println(doctorDataBase.get(i).getFullName());
        }

        System.out.println();


        System.out.println("Pharmacies List");
        ArrayList<Pharmacist> pharmacistsDataBase = fireBase.readPharmacist();
        for (Pharmacist pharmacist : pharmacistsDataBase) {
            System.out.print("Pharmacy ID: ");
            System.out.print(pharmacist.getId());

            System.out.print(", Pharmacy Name: ");
            System.out.println(pharmacist.getPharmacyName());
        }
    }


}
