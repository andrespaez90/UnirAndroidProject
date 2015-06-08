package unir.andres.com.proyectofinalandroidi.Model;

/**
 * Created by INNSO SAS on 08/06/2015.
 */
public enum SharedPrefKeys {

    USERDATA("USER_DATA"),

    NAME("NAME"),
    USERNAME("USER_NAME"),
    USERPASS("USER_PASS");

    private String text;

    private SharedPrefKeys(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
