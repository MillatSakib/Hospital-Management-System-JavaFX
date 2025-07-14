/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.GivePrescription to edit this template
 */
package Model;

/**
 *
 * @author Sakib
 */
public class GivePrescription {
    String PatientName;
    String Gender;
    String Age;
    String Prolblem;
    String PatientID;
    int appointmentID;

    public GivePrescription(String PatientName, String Gender, String Age, String Problem, String PatientID, int appointmentID) {
        this.PatientName = PatientName;
        this.Gender = Gender;
        this.Age = Age;
        this.Prolblem = Problem;
        this.PatientID = PatientID;
        this.appointmentID = appointmentID;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String PatientName) {
        this.PatientName = PatientName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String Age) {
        this.Age = Age;
    }

    public String getProlblem() {
        return Prolblem;
    }

    public void setProlblem(String Prolblem) {
        this.Prolblem = Prolblem;
    }
    
}
