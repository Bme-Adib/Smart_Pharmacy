package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
        ArrayList<Patient> patientsDataBase = fireBase.readPatients();

        for (int i=0;i<patientsDataBase.size();i++){
            System.out.print("Patient ID: ");
            System.out.print(patientsDataBase.get(i).getId());

            System.out.print(", Patient Name: ");
            System.out.println(patientsDataBase.get(i).getFullName());
        }
    }
}
