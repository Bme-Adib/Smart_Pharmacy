package smart.BackEnd;

import java.util.ArrayList;

public class Session {
    private String medicalCondition;
    private String doctorsName;
    private String sessionTimeStamp;
    private String sessionID;
    private String getDoctorsNotes;

    public Session() {
    }

    public Session(String medicalCondition, String doctorsName, String getDoctorsNotes) {
        this.medicalCondition = medicalCondition;
        this.doctorsName = doctorsName;
        this.getDoctorsNotes = getDoctorsNotes;

        sessionTimeStamp = new Time_Stamp().getCreationTime();
        sessionID = new Time_Stamp().getTimeId();
    }

    //geters and setters

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public String getSessionTimeStamp() {
        return sessionTimeStamp;
    }

    public void setSessionTimeStamp(String sessionTimeStamp) {
        this.sessionTimeStamp = sessionTimeStamp;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }


    public String getGetDoctorsNotes() {
        return getDoctorsNotes;
    }

    public void setGetDoctorsNotes(String getDoctorsNotes) {
        this.getDoctorsNotes = getDoctorsNotes;
    }
}
