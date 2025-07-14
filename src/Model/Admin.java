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
    public Admin(int ID, String Name, String ImageURL, String Email, String Password, String Phone, String Age, String Gender,String Address) {
        super(ID, Name, ImageURL, Email, Password, Phone, Age, Gender);
        Admin.Address = Address;
    }
    
  
    
    public static void resetAdmin(){
    ID=0;
    Address = "";
    Name = "";
    ImageURL ="";
    Email = "";
    Password = "";
    Phone = "";
    Age = "";
    Gender = "";
    }

    public static String getAddress() {
        return Address;
    }

    public static void setAddress(String Address) {
        Admin.Address = Address;
    }

    public static void setID(int ID) {
        User.ID = ID;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String Name) {
        User.Name = Name;
    }

    public static String getImageURL() {
        return ImageURL;
    }

    public static void setImageURL(String ImageURL) {
        User.ImageURL = ImageURL;
    }

    public static String getEmail() {
        return Email;
    }

    public static void setEmail(String Email) {
        User.Email = Email;
    }

    public static String getPassword() {
        return Password;
    }

    public static void setPassword(String Password) {
        User.Password = Password;
    }

    public static String getPhone() {
        return Phone;
    }

    public static void setPhone(String Phone) {
        User.Phone = Phone;
    }

    public static String getAge() {
        return Age;
    }

    public static void setAge(String Age) {
        User.Age = Age;
    }

    public static String getGender() {
        return Gender;
    }

    public static void setGender(String Gender) {
        User.Gender = Gender;
    }
    
}
