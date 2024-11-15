/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Sakib
 */
public class Admin extends User {
    protected static String Address="";
    public Admin(int ID, String Name, String Role, String ImageURL, String Email, String Password, String Phone, String Age, String Gender,String Address) {
        super(ID, Name, Role, ImageURL, Email, Password, Phone, Age, Gender);
        Admin.Address = Address;
    }
    
}
