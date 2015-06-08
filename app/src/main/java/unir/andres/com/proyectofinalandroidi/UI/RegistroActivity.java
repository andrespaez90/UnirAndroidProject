package unir.andres.com.proyectofinalandroidi.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import unir.andres.com.proyectofinalandroidi.Model.User;
import unir.andres.com.proyectofinalandroidi.Provider.PersistManager;
import unir.andres.com.proyectofinalandroidi.R;

public class RegistroActivity extends ActionBarActivity implements View.OnClickListener {

    private Button btn_register;
    private Button btn_back;

    private TextView tv_name;
    private TextView tv_username;
    private  TextView tv_pass;
    private TextView tv_repass;
    private TextView tv_Error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();
    }

    private void init(){
        btn_register = (Button)findViewById(R.id.signin_register);
        btn_back = (Button) findViewById(R.id.signin_back);

        tv_name = (TextView) findViewById(R.id.signin_name);
        tv_username = (TextView)findViewById(R.id.signin_username);
        tv_pass = (TextView)findViewById(R.id.signin_password);
        tv_repass = (TextView)findViewById(R.id.signin_repassword);
        tv_Error =(TextView)findViewById(R.id.signin_error);

        btn_register.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btn_register.getId()){
            String name = tv_name.getText().toString();
            String username = tv_username.getText().toString();
            String pass = tv_pass.getText().toString();
            String repass = tv_repass.getText().toString();
             if(username.equals("") || pass.equals("") || name.equals("") ){
                tv_Error.setText("Datos incompletos");
            }else if(!pass.equals(repass)){
                 tv_Error.setText("Las Contrasenas no coinciden");
             }
             else{
                User u = new User(name,username, pass);
                saveUser(u);
             }
        }else if(v.getId() == btn_back.getId()){
            finish();
        }
    }

    private void saveUser(User u) {
        PersistManager pm = new PersistManager(this);
        String response  =  pm.Save(u);
        if( response.equals( "success" )){
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }else if(response.equals("id")){
            tv_Error.setText("Nombre de usuario ya existe");
        }else{
            tv_Error.setText(response);
        }
    }
}
