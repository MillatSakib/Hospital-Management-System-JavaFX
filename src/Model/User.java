package Model;

public class User {

    protected static int ID;
    protected static String Name="";
    protected static String Role="";
    protected static String ImageURL="";
    protected static String Email="";
    protected static String Password="";
    protected static String Phone="";
    protected static String Age="";
    protected static String Gender="";
    
    

    User(int ID, String Name, String Role, String ImageURL, String Email, String Password, String Phone, String Age, String Gender) {
        User.ID = ID;
        User.Name = Name;
        User.Role = Role;
        User.ImageURL = ImageURL;
        User.Email = Email;
        User.Password = Password;
        User.Phone = Phone;
        User.Age = Age;
        User.Gender = Gender;
    }
    
     public static void resetUser(){
    ID=0;
    Name = "";
    Role = "";
    ImageURL = "";
    Email ="";
    Password = "";
    Phone = "";
    Age = "";
    Gender = "";
    }

    public static String getID() {
        return String.valueOf(ID);
    }

    public static String getName() {
        return Name;
    }

    public static String getRole() {
        return Role;
    }

    public static String getImageURL() {
        return ImageURL;
    }

    public static String getEmail() {
        return Email;
    }

    public static String getPassword() {
        return Password;
    }

    public static String getPhone() {
        return Phone;
    }

    public static String getAge() {
        return Age;
    }

    public static String getGender() {
        return Gender;
    }

    public static void setID(int ID) {
        User.ID = ID;
    }

    public static void setName(String Name) {
        User.Name = Name;
    }

    public static void setRole(String Role) {
        User.Role = Role;
    }

    public static void setImageURL(String ImageURL) {
        User.ImageURL = ImageURL;
    }

    public static void setEmail(String Email) {
        User.Email = Email;
    }

    public static void setPassword(String Password) {
        User.Password = Password;
    }

    public static void setPhone(String Phone) {
        User.Phone = Phone;
    }

    public static void setAge(String Age) {
        User.Age = Age;
    }

    public static void setGender(String Gender) {
        User.Gender = Gender;
    }
    
    

}
