package com.pruebabci;

import adapters.AdaptadorResultados;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import controller.ControlVistaResultados;
import models.Itunes;
import android.app.SearchManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;

public class VistaResultados extends AppCompatActivity {

    private ControlVistaResultados control;

    public SearchView searchView;
    public MenuItem searchItem;

    public SwipeRefreshLayout reload;
    public ListView lista_busqueda;

    public ArrayList<Itunes> list;
    public AdaptadorResultados adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_resultados);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init_components();
        init_events();

    }

    private void init_components(){
        reload         = findViewById(R.id.reload);
        lista_busqueda = findViewById(R.id.lista_busqueda);

        reload.setColorSchemeResources(R.color.orange, R.color.green, R.color.blue);
    }

    private void init_events(){
        control = new ControlVistaResultados(this);
        reload.setOnRefreshListener(control);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        searchItem = menu.findItem(R.id.action_search);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                @Override
                public boolean onClose() {
                    return false;
                }
            });
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            EditText searchPlate = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
            searchPlate.setHint(getString(R.string.action_search));
            View searchPlateView = searchView.findViewById(androidx.appcompat.R.id.search_plate);
            searchPlateView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));

            searchView.setOnQueryTextListener(control);
            SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        }


        return true;
    }

}