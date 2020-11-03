package smart.BackEnd;

public class PtSession {
    private String medicalCondition;
    private String doctorsName;
    private String sessionTimeStamp;
    private String sessionID;
    private String doctorsNotes;

    public PtSession() {
    }

    public PtSession(String medicalCondition, String doctorsName, String doctorsNotes) {
        this.medicalCondition = medicalCondition;
        this.doctorsName = doctorsName;
        this.doctorsNotes = doctorsNotes;

        sessionTimeStamp = new Time_Stamp().getCreationTime();
        sessionID = new Time_Stamp().getTimeId();
    }

    public void continueCreation(){
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


    public String getDoctorsNotes() {
        return doctorsNotes;
    }

    public void setDoctorsNotes(String doctorsNotes) {
        this.doctorsNotes = doctorsNotes;
    }
}
