package unir.andres.com.proyectofinalandroidi.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import unir.andres.com.proyectofinalandroidi.Model.ActivityKeys;
import unir.andres.com.proyectofinalandroidi.R;


public class StartActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.startmenu_informacion) {
            createDialog();
            return true;
        }else if( id == R.id.startmenu_Login){
            intent = new Intent(this,LoginActivity.class);
            startActivityForResult(intent, ActivityKeys.LOGIN_ACTIVITY.ordinal());
            return true;
        }else if( id == R.id.startmenu_registro ){
            intent = new Intent(this,RegistroActivity.class);
            startActivityForResult(intent, ActivityKeys.REGISTER_ACTIVITY.ordinal());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent;
        if( requestCode == ActivityKeys.LOGIN_ACTIVITY.ordinal()){
            if( resultCode == Activity.RESULT_OK ){
                setResult(RESULT_OK);
                finish();
            }
        }
        if( requestCode == ActivityKeys.REGISTER_ACTIVITY.ordinal()){
            if( resultCode == Activity.RESULT_OK ){
                intent = new Intent(this,LoginActivity.class);
                startActivityForResult(intent, ActivityKeys.LOGIN_ACTIVITY.ordinal());
                return;
            }
        }
    }


    private void createDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atencion!");
        builder.setMessage("Para acceder a la informacion debe estar registrado.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
}
