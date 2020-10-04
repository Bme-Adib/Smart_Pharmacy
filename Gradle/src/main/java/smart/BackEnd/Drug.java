package smart.BackEnd;

public class Drug {
    private String drugName;
    private String drugEffectiveSubstance;
    private double dosage;
    private int repetition;
    private int repetitionUsed = 0;


    public Drug() {
    }

    public Drug(String drugName, String drugEffective, double dosage, int repetition) {
        this.drugName = drugName;
        this.drugEffectiveSubstance = drugEffective;
        this.dosage = dosage;
        this.repetition = repetition;
    }

    // setters and getters
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugEffectiveSubstance() {
        return drugEffectiveSubstance;
    }

    public void setDrugEffectiveSubstance(String drugEffectiveSubstance) {
        this.drugEffectiveSubstance = drugEffectiveSubstance;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }

    public int getRepetitionUsed() {
        return repetitionUsed;
    }

    public void setRepetitionUsed(int repetitionUsed) {
        this.repetitionUsed = repetitionUsed;
    }
}
