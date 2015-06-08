package unir.andres.com.proyectofinalandroidi.Model;

/**
 * Created by INNSO SAS on 08/06/2015.
 */
public class User {

    private String Name;
    private String Username;
    private String Password;

    public User(String name, String username, String password) {
        Name = name;
        Username = username;
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
