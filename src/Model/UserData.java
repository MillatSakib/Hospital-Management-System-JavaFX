package Model;

public class UserData {

    private static int ID;
    private static String Name;
    private static String Role;
    private static String ImageURL;
    private static String Email;
    private static String Password;

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
