package controller;

import androidx.appcompat.widget.SearchView;
import com.pruebabci.VistaResultados;
import java.util.ArrayList;
import adapters.AdaptadorResultados;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import api.RequestApi;
import api.RequestDB;
import helpers.Global;

public class ControlVistaResultados implements SwipeRefreshLayout.OnRefreshListener,
                                    SearchView.OnQueryTextListener{

    private VistaResultados vista;
    public ControlVistaResultados(VistaResultados vista){
        this.vista = vista;
        loadResultados(Global.SEARCH);
    }

    private void loadResultados(String q){

        crearElementosResultados();

        if (Global.isConncted(vista)) {
            new RequestApi(vista).execute("https://itunes.apple.com/search?term="+q+"&mediaType=music&limit=20");
        }else{
            new RequestDB(vista).execute(Global.SEARCH);
        }


    }

    private void crearElementosResultados(){

        vista.list    = new ArrayList<>();
        vista.adapter = new AdaptadorResultados(vista, vista.list);

        vista.lista_busqueda.setAdapter(vista.adapter);
    }

    @Override
    public void onRefresh() {
        loadResultados(Global.SEARCH);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Global.SEARCH = s;
        loadResultados(Global.SEARCH);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
