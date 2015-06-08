package unir.andres.com.proyectofinalandroidi.UI;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import unir.andres.com.proyectofinalandroidi.Model.SharedPrefKeys;
import unir.andres.com.proyectofinalandroidi.Model.User;
import unir.andres.com.proyectofinalandroidi.Provider.PersistManager;
import unir.andres.com.proyectofinalandroidi.Provider.SharePreference;
import unir.andres.com.proyectofinalandroidi.R;

public class LoginActivity extends ActionBarActivity implements View.OnClickListener{

    private TextView tv_username;
    private TextView tv_password;
    private TextView tv_error;

    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        tv_username = (TextView) findViewById(R.id.login_username);
        tv_password = (TextView) findViewById(R.id.login_password);
        tv_error = (TextView) findViewById(R.id.login_error);

        btn_login = (Button) findViewById(R.id.login_Login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() ==  btn_login.getId() ){
            String user = tv_username.getText().toString();
            String pass = tv_password.getText().toString();
             if( user.equals("") || pass.equals("") ){
                 tv_error.setText("Datos incompletos");
             }else{
                 User u = new User("",user,pass);
                 if( validateUser(u) ){
                     setResult(RESULT_OK);
                     finish();
                 }
             }
        }
    }

    private boolean validateUser(User u) {
        PersistManager pm = new PersistManager(this);
        u = pm.getUser(u);
        if( u != null ){
            SharePreference.saveDataApplication(SharedPrefKeys.NAME,u.getName());
            SharePreference.saveDataApplication(SharedPrefKeys.USERNAME,u.getUsername());
            SharePreference.saveDataApplication(SharedPrefKeys.USERPASS,u.getPassword());
            return true;
        }
        tv_error.setText("Usuario o contrasena invalida");
        return false;
    }
}
