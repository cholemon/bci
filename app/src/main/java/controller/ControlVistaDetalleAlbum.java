package controller;

import android.os.Bundle;
import com.pruebabci.VistaDetalleAlbum;
import java.util.ArrayList;
import adapters.AdaptadorListaCancionesAlbum;
import api.RequestAlbumDB;
import api.RequestApiAlbum;
import helpers.Global;

public class ControlVistaDetalleAlbum {

    private VistaDetalleAlbum vista;

    private String end_point;
    private int id_album;

    public ControlVistaDetalleAlbum(VistaDetalleAlbum vista){
        this.vista = vista;

        Bundle b =  vista.getIntent().getExtras();
        id_album =  b.getInt("id_album");

        loadResources();
    }


    private void loadResources(){
        vista.lista_canciones  = new ArrayList<>();
        vista.adapter = new AdaptadorListaCancionesAlbum(vista, vista.lista_canciones);

        vista.lista_canciones_album.setAdapter(vista.adapter);

        if(Global.isConncted(vista)){
            end_point = "https://itunes.apple.com/lookup?id="+id_album+"&entity=song";
            new RequestApiAlbum(vista).execute(end_point);
        }else{
            new RequestAlbumDB(vista).execute(String.valueOf(id_album));
        }
    }

}
