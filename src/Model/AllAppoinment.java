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
    public String DoctorID;
    public String DoctorName;
    public String Problem;
    public String Visited;
    public String ContactNumber;
    AllAppoinment(String DoctorName, String DoctorID, String Problem, String Visited, String ContactNumber){
    this.DoctorName = DoctorName;
    this.DoctorID = DoctorID;
    this.Problem = Problem;
    this.Visited = Visited;
    this.ContactNumber = ContactNumber;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getProblem() {
        return Problem;
    }

    public void setProblem(String Problem) {
        this.Problem = Problem;
    }

    public String getVisited() {
        return Visited;
    }

    public void setVisited(String Visited) {
        this.Visited = Visited;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }
    
    
}
