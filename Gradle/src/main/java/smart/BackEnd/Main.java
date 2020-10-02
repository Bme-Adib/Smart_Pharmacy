package smart.BackEnd;

import smart.FireStore.FireBase;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
//        fireBase.writeDoctorToFireBase(doctor);


        ArrayList<Doctor> doctorsDB = fireBase.readDoctors();
        for(int i=0;i<doctorsDB.size();i++){
            System.out.println("ID: " + doctorsDB.get(i).getId());
        }
    }
}