/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sakib
 */
public class AllAppoinment {
    public static String DoctorID;
    public static String DoctorName;
    public static String Problem;
    public static String Visited;
    public static String ContactNumber;
    AllAppoinment(String DoctorName, String DoctorID, String Problem, String Visited, String ContactNumber){
    this.DoctorName = DoctorName;
    this.DoctorID = DoctorID;
    this.Problem = Problem;
    this.Visited = Visited;
    this.ContactNumber = ContactNumber;
    }

    public static String getDoctorID() {
        return DoctorID;
    }

    public static String getDoctorName() {
        return DoctorName;
    }

    public static void setDoctorName(String DoctorName) {
        AllAppoinment.DoctorName = DoctorName;
    }

    public static void setDoctorID(String DoctorID) {
        AllAppoinment.DoctorID = DoctorID;
    }

    public static String getProblem() {
        return Problem;
    }

    public static void setProblem(String Problem) {
        AllAppoinment.Problem = Problem;
    }

    public static String getVisited() {
        return Visited;
    }

    public static void setVisited(String Visited) {
        AllAppoinment.Visited = Visited;
    }

    public static String getContactNumber() {
        return ContactNumber;
    }

    public static void setContactNumber(String ContactNumber) {
        AllAppoinment.ContactNumber = ContactNumber;
    }
    
    
}
