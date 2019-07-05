package api;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.pruebabci.MainActivity;
import com.pruebabci.VistaDetalleAlbum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import helpers.HelperListViewCanciones;
import models.BD;
import models.Itunes;
import models.ListaCanciones;

public class RequestAlbumDB extends AsyncTask<String, Void, Void> {

    private VistaDetalleAlbum vista;
    private BD bd;

    private String nombreAlbum    = "";
    private String nombreArtista  = "";
    private String copy           = "";
    private String foto           = "";

    private int len_res = 0;

    public RequestAlbumDB(VistaDetalleAlbum vista){
        this.vista = vista;
        bd         = new BD(vista);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        vista.layout_loading.setVisibility(View.VISIBLE);
    }

    @Nullable
    @Override
    protected Void doInBackground(String... params) {

        int idCollection = Integer.parseInt(params[0]);

        if(bd.exist_collection(idCollection)){
            ArrayList<Itunes> results        = bd.getAlbum(idCollection);
            ArrayList<ListaCanciones> tracks = bd.getAllTrackAlbum(idCollection);

            len_res        = results.size();
            int len_tracks = tracks.size();

            for(int x=0; x<len_res; x++){
                nombreAlbum   = results.get(x).getNombreAlbum();
                nombreArtista = results.get(x).getNombreArtista();
                copy          = results.get(x).getCopy();
                foto          = results.get(x).getFoto();
            }

            for(int y=0; y<len_tracks; y++){
                ListaCanciones lista = new ListaCanciones();

                int idAlbum         = tracks.get(y).getIdAlbum();
                String nombreTrack  = tracks.get(y).getNombre();
                String preview      = tracks.get(y).getPreview() ;

                lista.setIdAlbum(idAlbum);
                lista.setNombre(nombreTrack);
                lista.setPreview(preview);

                vista.lista_canciones.add(lista);
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(len_res == 0){
            Intent inicio = new Intent(vista, MainActivity.class);
            vista.startActivity(inicio);
        }else{
            vista.txt_nombre_album.setText(nombreAlbum);
            vista.txt_nombre_artista.setText(nombreArtista);
            vista.txt_copy.setText(copy);
            Picasso.with(vista).load(foto).fit().centerCrop().into(vista.imagen);

            if(vista.lista_canciones.size() > 0) {
                vista.area_canciones.setVisibility(View.VISIBLE);
                vista.adapter.notifyDataSetChanged();
                HelperListViewCanciones.getListViewSize(vista.lista_canciones_album);
                vista.scroll_principal.smoothScrollTo(0,0);
            }

            vista.layout_loading.setVisibility(View.GONE);
            vista.scroll_principal.setVisibility(View.VISIBLE);
        }
    }
}
