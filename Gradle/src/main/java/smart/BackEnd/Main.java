package smart.BackEnd;

import smart.FireStore.WriteToFireBase;

public class Main {
    public static void main(String[] args) {
        Patient patient = new Patient("Aisha","AlHarweel","Female",31,1,1993);

        WriteToFireBase writeToFireBase = new WriteToFireBase();
        writeToFireBase.writePatientToFireBase(patient);


    }

}
