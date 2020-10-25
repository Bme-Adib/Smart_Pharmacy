package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;
import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {

        FireBase fireBase = new FireBase();
        ArrayList<Doctor> doctorDB = fireBase.readDoctors();

        for (int i=0;i<doctorDB.size();i++){
            System.out.println(i + ":" + doctorDB.get(i).getPassword());
        }


        Scanner scanner = new Scanner(System.in);
        System.out.println("PLease Enter ID");
        String id = scanner.nextLine();




        boolean found = false;
        int doctorIndex = 0;
        for (int i = 0; i < doctorDB.size(); i++) {
            if (id.equals(doctorDB.get(i).getId())) {
                found = true;
                doctorIndex = i;
                break;
            }
        }

        if (found) {
            System.out.println("PLease Enter Password");
            String psw = scanner.nextLine();
            psw = new Hashing().SHA256(psw);
            if (psw.equals(doctorDB.get(doctorIndex).getPassword())) {
                System.out.println("Welcome " + doctorDB.get(doctorIndex).getFullName());
            }else {
                System.out.println("Password Wrong");
            }

        } else {
            System.out.println("Please Check your ID");
        }


    }
}
