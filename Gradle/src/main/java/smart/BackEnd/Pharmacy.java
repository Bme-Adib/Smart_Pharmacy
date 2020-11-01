package smart.BackEnd;

public class Pharmacy {
    //System Information
    private String id;
    private String dateCreated;
    //Personal information
    private String pharmacyName;
    private String phoneNumber;
    private String licenseId;
    private String email;
    private String password;


    public Pharmacy() {
    }

    public Pharmacy(String firstName, String mobileNumber, String licenseId) {
        this.pharmacyName = firstName;
        this.phoneNumber = mobileNumber;
        this.licenseId = licenseId;

        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPPH" + licenseId;
    }

    public void passwordSet(String password){
        this.password = new Hashing().SHA256(password);
    }

    public void continueCreation(){
        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPPH" + licenseId;
    }



//setters and getters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
}
