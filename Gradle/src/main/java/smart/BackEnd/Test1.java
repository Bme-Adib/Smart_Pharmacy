package smart.BackEnd;

import smart.FireStore.FireBase;

public class Test1 {
    public static void main(String[] args) {



        FireBase fireBase = new FireBase();

        Doctor doctor = new Doctor("Dr. John","Doe",Doctor.MALE,15,7,1956,
                "General Practice","0115445151","5565521","johndoe@email.com","L88955623");
        doctor.passwordSet("aishaBootyIsAmazing");
        fireBase.writeDoctorToFireBase(doctor);


        doctor = new Doctor("Adib","Ghannam",Doctor.MALE,5,6,1993,
                "General Practice","011542345151","552365521","adib@email.com","L88955623");
        doctor.passwordSet("aishaboobiesareamazing");
        fireBase.writeDoctorToFireBase(doctor);



    }
}
