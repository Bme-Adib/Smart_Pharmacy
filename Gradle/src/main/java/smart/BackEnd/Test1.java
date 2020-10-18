package smart.BackEnd;

import smart.FireStore.FireBase;

public class Test1 {
    public static void main(String[] args) {
        Pharmacist pharmacist = new Pharmacist("GMB","0655166888","TLG5699562");

        Doctor doctor = new Doctor("Dr. John","Doe",Doctor.MALE,15,7,1956,
                "General Practice","0115445151","5565521","johndoe@email.com","L88955623");

        Patient patient = new Patient("Richard","Bong",Patient.MALE,6,7,1993,
                "adib@email.com","+601121495594");

        FireBase fireBase = new FireBase();
        fireBase.writePatientToFireBase(patient);
        fireBase.writeDoctorToFireBase(doctor);
        fireBase.writePharmacistToFireBase(pharmacist);




    }
}
