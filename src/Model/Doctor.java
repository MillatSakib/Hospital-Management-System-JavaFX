/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sakib
 */
public class Doctor extends User {
    
    protected static String Specialization="";
    protected static String DoctorID="";
    protected static String Address = "";
    
    public Doctor(int ID, String Name, String Role, String ImageURL, String Email, String Password, String Phone, String Age, String Gender, String Specialization, String DoctorID, String Address) {
        super(ID, Name, Role, ImageURL, Email, Password, Phone, Age, Gender);
        Doctor.Specialization = Specialization;
        Doctor.DoctorID = DoctorID;
        Doctor.Address = Address;
    }
    
}
