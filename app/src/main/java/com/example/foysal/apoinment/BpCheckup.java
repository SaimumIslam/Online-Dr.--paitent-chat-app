package com.example.foysal.apoinment;

/**
 * Created by Foysal on 12/19/2017.
 */
public class BpCheckup {

    public int systolic;
    public int diastolic;
    public int pulse;
    public long timestamp;

    public BpCheckup()
    {
        this.systolic=0;
        this.systolic=0;
        this.pulse=0;
        this.timestamp=0;
    }
    public BpCheckup(int systolic, int diastolic, long timestamp) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.timestamp = timestamp;
    }

    public BpCheckup(int systolic, int diastolic, int pulse, long timestamp) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.pulse = pulse;
        this.timestamp = timestamp;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
