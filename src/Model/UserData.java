package Model;

public class UserData {

    protected static int ID;
    protected static String Name="";
    protected static String Role="";
    protected static String ImageURL="";
    protected static String Email="";
    protected static String Password="";
    

    UserData(int ID, String Name, String Role, String ImageURL, String Email, String Password) {
        UserData.ID = ID;
        UserData.Name = Name;
        UserData.Role = Role;
        UserData.ImageURL = ImageURL;
        UserData.Email = Email;
        UserData.Password = Password;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getRole() {
        return Role;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

}
