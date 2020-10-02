package smart.BackEnd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Patient {
    //System Information
    private String id;
    private String dateCreated;

    //Personal Information
    private String firstName;
    private String lastName;
    private String fullName;
    private String gender;
    private int day;
    private int month;
    private int year;
    private long ageYears;
    private long ageMonths;

    public Patient() {
    }

    public Patient(String firstName, String lastName, String gender, int day, int month, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.day = day;
        this.month = month;
        this.year = year;
        this.fullName = this.firstName + " " + this.lastName;

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        this.ageYears = ChronoUnit.YEARS.between(dateOfBirth, now);
        this.ageMonths = ChronoUnit.MONTHS.between(dateOfBirth, now) % 12;

        Time_Stamp time_stamp = new Time_Stamp();
        dateCreated = time_stamp.getCreationTime();

        id = "SPPT" + String.valueOf(year).substring(2) + String.format("%02d", month) + String.format("%02d", day) + new Random().nextInt(10000);
    }


    //getter and setters

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
