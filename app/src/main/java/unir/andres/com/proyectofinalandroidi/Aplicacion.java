package unir.andres.com.proyectofinalandroidi;

import android.app.Application;
import android.content.Context;

public class Aplicacion extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
