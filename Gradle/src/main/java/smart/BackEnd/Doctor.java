package smart.BackEnd;

import java.util.Random;

public class Doctor {
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    //System Information
    private String id;
    private String dateCreated;
    //Personal information
    private String firstName;
    private String lastName;
    private String gender;
    private int day;
    private int month;
    private int year;
    private String specialty;
    private String mobileNumber;
    private String clinicPhoneNumber;
    private String email;
    private String licenseId;
    private String password = "123456789";
    //Auto Complete
    private String fullName;


    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String gender, int day, int month, int year, String specialty,
                  String mobileNumber, String clinicPhoneNumber, String email, String licenseId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.day = day;
        this.month = month;
        this.year = year;
        this.specialty = specialty;
        this.mobileNumber = mobileNumber;
        this.clinicPhoneNumber = clinicPhoneNumber;
        this.email = email;
        this.licenseId = licenseId;
        this.fullName = this.firstName + " " + this.lastName;

        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPDR" + String.valueOf(year).substring(2) + String.format("%02d", month) + String.format("%02d", day) + new Random().nextInt(10000);
    }


//setters and getters


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getClinicPhoneNumber() {
        return clinicPhoneNumber;
    }

    public void setClinicPhoneNumber(String clinicPhoneNumber) {
        this.clinicPhoneNumber = clinicPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
