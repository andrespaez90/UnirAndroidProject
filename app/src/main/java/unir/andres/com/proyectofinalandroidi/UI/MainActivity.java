package unir.andres.com.proyectofinalandroidi.UI;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import unir.andres.com.proyectofinalandroidi.Model.ActivityKeys;
import unir.andres.com.proyectofinalandroidi.Model.SharedPrefKeys;
import unir.andres.com.proyectofinalandroidi.Provider.SharePreference;
import unir.andres.com.proyectofinalandroidi.R;

public class MainActivity extends ActionBarActivity {

    private TextView tv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_welcome = (TextView) findViewById(R.id.tmain_welcome);
        init();
    }

    private void init(){
        String name = SharePreference.getApplicationValue(SharedPrefKeys.NAME);
        if(name.equals("") ){
            Intent intent = new Intent(this, StartActivity.class);
            startActivityForResult(intent, ActivityKeys.START_ACTIVITY.ordinal());
        }else{
            tv_welcome.setText("Bienvenido "+name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if( id == R.id.mainmenu_usuarios){
            Intent i = new Intent(this,ListActivity.class);
            i.putExtra("op", 0);
            startActivity(i);
            return true;
        }
        if (id == R.id.mainmenu_enlinea) {
            Intent i = new Intent(this,ListActivity.class);
            i.putExtra("op", 1);
            startActivity(i);
            return true;
        }
        if( id == R.id.mainmenu_logout){
            SharePreference.saveDataApplication(SharedPrefKeys.NAME,"");
            Intent intent = new Intent(this, StartActivity.class);
            startActivityForResult(intent, ActivityKeys.START_ACTIVITY.ordinal());
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityKeys.START_ACTIVITY.ordinal() && resultCode == RESULT_OK){
            String name = SharePreference.getApplicationValue(SharedPrefKeys.NAME);
            tv_welcome.setText("Bienvenido "+name);
        }else{
            finish();
        }
    }
}
