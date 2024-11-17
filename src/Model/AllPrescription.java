/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sakib
 */
public class AllPrescription {
    public static String doctorName;
    public static String doctorID;
    public static String problem;
    public static String prescription;

    public AllPrescription(String doctorName, String doctorID,String problem, String prescription ) {
        this.doctorName = doctorName; 
        this.doctorID = doctorID; 
        this.problem = problem; 
        this.prescription = prescription; 
        
    }

    public static String getDoctorName() {
        return doctorName;
    }

    public static void setDoctorName(String doctorName) {
        AllPrescription.doctorName = doctorName;
    }

    public static String getDoctorID() {
        return doctorID;
    }

    public static void setDoctorID(String doctorID) {
        AllPrescription.doctorID = doctorID;
    }

    public static String getProblem() {
        return problem;
    }

    public static void setProblem(String problem) {
        AllPrescription.problem = problem;
    }

    public static String getPrescription() {
        return prescription;
    }

    public static void setPrescription(String prescription) {
        AllPrescription.prescription = prescription;
    }
    
    
    
}
