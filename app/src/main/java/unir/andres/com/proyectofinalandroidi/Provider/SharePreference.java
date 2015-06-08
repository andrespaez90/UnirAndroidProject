package unir.andres.com.proyectofinalandroidi.Provider;

import android.content.SharedPreferences;

import unir.andres.com.proyectofinalandroidi.Aplicacion;
import unir.andres.com.proyectofinalandroidi.Model.SharedPrefKeys;

/**
 * Created by INNSO SAS on 08/06/2015.
 */
public class SharePreference {

    public static void saveDataApplication(SharedPrefKeys preKey, String value){
        SharedPreferences sharedPreference = getCustomizePref();
        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(preKey.toString(), value);
        editor.commit();
    }

    public static String getApplicationValue(SharedPrefKeys prefKey){
        SharedPreferences user_data = getCustomizePref();
        return user_data.getString(prefKey.toString(), "");
    }

    private static SharedPreferences getCustomizePref() {
        return Aplicacion.getAppContext().getSharedPreferences(SharedPrefKeys.USERDATA.toString(), 0);
    }
}
