package com.ayoush;


public class Main {
    public static void main(String[] args) {
        Patient patent123 = new Patient("Aisha", "AlHarweel", "Female", 31, 1, 2000);
        System.out.println("Age: " + patent123.getAgeYears() + " Years and " + patent123.getAgeMonths() + " months");
    }
}
