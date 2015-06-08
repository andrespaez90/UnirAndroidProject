package unir.andres.com.proyectofinalandroidi.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

import unir.andres.com.proyectofinalandroidi.Model.User;

/**
 * Created by INNSO SAS on 08/06/2015.
 */
public class PersistManager extends SQLiteOpenHelper {
    public PersistManager(Context context) {
        super(context, "DataBase", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (user TEXT, pass TEXT, PRIMARY KEY(user) );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String Save(User p) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("INSERT INTO User VALUES ('" + p.getUsername() + "'," + "'" + p.getPassword() + "')");
            return "success";
        } catch (SQLiteConstraintException ex) {
            return "id";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Delete(String id){
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM User WHERE user ='"+id+"';");
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String Edit(String id, String name) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("UPDATE  User SET name = '"+name+"' WHERE DNI ='"+id+"';");
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Vector<User> getsaved() {
        Vector<User> result = new Vector<User>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursosr = db.rawQuery("SELECT * FROM User", null);
        while(cursosr.moveToNext())
        {
            User u = new User(cursosr.getString(0), cursosr.getString(1));
            result.add(u);
        }

        cursosr.close();


        return result;
    }

}
