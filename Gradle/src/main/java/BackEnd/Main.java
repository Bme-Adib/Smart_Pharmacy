package BackEnd;

import FireStore.WriteToFireBase;

public class Main {
    public static void main(String[] args) {
        Patient patent123 = new BackEnd.Patient("Aisha", "AlHarweel", "Female", 31, 1, 2000);
        patent123.setId("0012552");
        System.out.println("Age: " + patent123.getAgeYears() + " Years and " + patent123.getAgeMonths() + " months");


        WriteToFireBase writeToFireBase = new WriteToFireBase();
        writeToFireBase.writePatientToFireBase(patent123);

    }


}
