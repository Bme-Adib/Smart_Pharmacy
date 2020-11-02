package smart.BackEnd;

import smart.FireStore.FireBase;

import java.util.ArrayList;

public class Test3 {
    public static void main(String[] args) {

        PtSession ptSession = new PtSession("He is About to die", "Sa3ed el zb", "Dead man walking");
        Drug drug = new Drug("Aspirin", "Aspirin", 25, 3);
        Drug drug2 = new Drug("Panadol", "Panadol", 50, 2);
        ArrayList<Drug> drugs = new ArrayList<>();
        drugs.add(drug);
        drugs.add(drug2);

        FireBase fireBase = new FireBase();
        fireBase.writeSessionToPatient("SPPT9306059982", ptSession);
        fireBase.writeDrugsToSession("SPPT9306059982", ptSession.getSessionID(), drugs);
    }
}
