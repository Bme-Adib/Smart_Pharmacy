package BackEnd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Patient {
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private int day;
    private int month;
    private int year;
    private long ageYears;
    private long ageMonths;
    private String medicalCondition;

    public Patient(String firstName, String lastName, String gender, int day, int month, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.day = day;
        this.month = month;
        this.year = year;

        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        this.ageYears = ChronoUnit.YEARS.between(dateOfBirth, now);
        this.ageMonths = ChronoUnit.MONTHS.between(dateOfBirth, now) % 12;
    }


    //getter and setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAgeMonths() {
        return ageMonths;
    }

    public void setAgeMonths(long ageMonths) {
        this.ageMonths = ageMonths;
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

    public void setAgeYears(long ageYears) {
        this.ageYears = ageYears;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }
}
