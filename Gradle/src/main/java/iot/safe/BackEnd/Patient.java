package iot.safe.BackEnd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Patient {
    public static final String MALE = "Male";
    public static final String FEMALE = "Female";
    //System Information
    private String id;
    private String dateCreated;
    //Personal Information
    private String firstName;
    private String lastName;
    private String gender;
    private int day;
    private int month;
    private int year;
    private String email;
    private String phoneNumber;
    private String IDN;
    //Auto Complete
    private String fullName;
    private long ageYears;
    private long ageMonths;
    private String password;
    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Patient() {
    }

    public Patient(String firstName, String lastName, String gender, int day, int month, int year, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.day = day;
        this.month = month;
        this.year = year;
        this.phoneNumber = phoneNumber;
        this.email = email;

        this.fullName = this.firstName + " " + this.lastName;

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        this.ageYears = ChronoUnit.YEARS.between(dateOfBirth, now);
        this.ageMonths = ChronoUnit.MONTHS.between(dateOfBirth, now) % 12;

        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPPT" + String.valueOf(year).substring(2) + String.format("%02d", month) + String.format("%02d", day) + new Random().nextInt(10000);
    }

    public void continueCreation(){
        this.fullName = this.firstName + " " + this.lastName;

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        this.ageYears = ChronoUnit.YEARS.between(dateOfBirth, now);
        this.ageMonths = ChronoUnit.MONTHS.between(dateOfBirth, now) % 12;

        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPPT" + String.valueOf(year).substring(2) + String.format("%02d", month) + String.format("%02d", day) + new Random().nextInt(10000);
    }

    public void passwordSet(String password) {
        this.password = new Hashing().SHA256(password);
    }

    //getter and setters


    public String getIDN() {
        return IDN;
    }

    public void setIDN(String IDN) {
        this.IDN = IDN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAgeYears(long ageYears) {
        this.ageYears = ageYears;
    }

    public void setAgeMonths(long ageMonths) {
        this.ageMonths = ageMonths;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getId() {
        return id;
    }

    public long getAgeMonths() {
        return ageMonths;
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

    public long getAgeYears() {
        return ageYears;
    }

}
