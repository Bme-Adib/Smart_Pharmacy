package smart.BackEnd;

import smart.FireStore.FireBase;

public class Test2 {
    public static void main(String[] args) {
//        Pharmacist pharmacist = new Pharmacist("GMB","0655166888","TLG5699562");
//        FireBase fireBase = new FireBase();
//        fireBase.writePharmacistToFireBase(pharmacist);

        new FireBase().writePharmacistToFireBase(new Pharmacist("GMB","0655166888","TLG56995fgfg62"));
    }
}
