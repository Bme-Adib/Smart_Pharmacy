package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
        ArrayList<Doctor> patientsDB = fireBase.readDoctors();
        System.out.println(patientsDB.size());

    }
}