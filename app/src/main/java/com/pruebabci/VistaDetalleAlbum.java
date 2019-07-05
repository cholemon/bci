package com.pruebabci;

import adapters.AdaptadorListaCancionesAlbum;
import androidx.appcompat.app.AppCompatActivity;
import controller.ControlVistaDetalleAlbum;
import models.ListaCanciones;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.ArrayList;

public class VistaDetalleAlbum extends AppCompatActivity {

    private ControlVistaDetalleAlbum control;

    public LinearLayout layout_loading;
    public ScrollView scroll_principal;
    public ImageView imagen;

    public TextView txt_nombre_artista;
    public TextView txt_nombre_album;
    public TextView txt_copy;

    public LinearLayout area_canciones;
    public ListView lista_canciones_album;

    public ArrayList<ListaCanciones> lista_canciones;
    public AdaptadorListaCancionesAlbum adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_detalle_album);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        init_components();
        init_events();
    }

    private void init_components(){

        layout_loading        = findViewById(R.id.layout_loading);
        scroll_principal      = findViewById(R.id.scroll_principal);
        imagen                = findViewById(R.id.imagen);
        txt_nombre_artista    = findViewById(R.id.txt_nombre_artista);
        txt_nombre_album      = findViewById(R.id.txt_nombre_album);
        txt_copy              = findViewById(R.id.txt_copy);
        area_canciones        = findViewById(R.id.area_canciones);
        lista_canciones_album = findViewById(R.id.lista_canciones_album);

    }

    private void init_events(){
        control = new ControlVistaDetalleAlbum(this);
    }

}
