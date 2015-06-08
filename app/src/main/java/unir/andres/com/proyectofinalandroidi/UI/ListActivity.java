package unir.andres.com.proyectofinalandroidi.UI;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

import unir.andres.com.proyectofinalandroidi.Adapters.RecyckeViewAdapter;
import unir.andres.com.proyectofinalandroidi.Model.User;
import unir.andres.com.proyectofinalandroidi.Provider.PersistManager;
import unir.andres.com.proyectofinalandroidi.R;

public class ListActivity extends ActionBarActivity {

    private RecyclerView lista;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
        getData();
    }

    private void init(){
        title = (TextView) findViewById(R.id.aclist_title);
        lista = (RecyclerView)findViewById(R.id.aclist_recView);
    }


    private void getData(){
        Bundle extras = getIntent().getExtras();
        int op = extras.getInt("op", -1);
        if(op == 0){
            title.setText("Lista de Usuarios");
            PersistManager pm = new PersistManager(this);
            createList(pm.getsaved());
        }else{
            title.setText("Usuarios en Linea");
            ArrayList<User> usuarios = new ArrayList<User>();
            usuarios.add(new User("Felipe","En Linea",""));
            usuarios.add(new User("Mario","En Linea",""));
            usuarios.add(new User("Andres","En Linea",""));
            usuarios.add(new User("Alejandro","En Linea",""));
            usuarios.add(new User("Liliana","En Linea",""));
            createList(usuarios);
        }
    }

    private void createList(ArrayList<User> list){

        //Inicializacion RecyclerView
        lista.setHasFixedSize(true);
        final RecyckeViewAdapter adaptador = new RecyckeViewAdapter(list,this);

        lista.setAdapter(adaptador);
        lista.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar

        getMenuInflater().inflate(R.menu.menu_list, menu);

        SearchView searchview = (SearchView)(menu.findItem(R.id.list_action_search).getActionView());
        searchview.setQueryHint("Buscar Usuario");
        searchview.setOnQueryTextListener(new ListController());

        return super.onCreateOptionsMenu(menu);
    }

    public class ListController implements SearchView.OnQueryTextListener{

        @Override
        public boolean onQueryTextSubmit(String query) {
            ((RecyckeViewAdapter) lista.getAdapter()).setFilter(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            ((RecyckeViewAdapter) lista.getAdapter()).setFilter(newText);
            return false;
        }
    }
}
