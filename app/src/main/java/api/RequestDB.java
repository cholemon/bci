package api;

import android.content.Intent;
import android.os.AsyncTask;
import com.pruebabci.MainActivity;
import com.pruebabci.VistaResultados;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import models.BD;
import models.Itunes;

public class RequestDB extends AsyncTask<String, Void, Void> {

    private VistaResultados vista;
    private BD bd;

    public RequestDB(VistaResultados vista){
        this.vista = vista;
        bd         = new BD(vista);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        vista.reload.setRefreshing(true);
    }

    @Nullable
    @Override
    protected Void doInBackground(String... params) {

        String query = params[0];

        ArrayList<Itunes> results = bd.allResults(query);
        int len                   = results.size();

        if(len > 0) {
            for(int x=0; x<len;x++) {
                Itunes itunes = new Itunes();

                itunes.setIdAlbum(results.get(x).getIdAlbum());
                itunes.setNombreAlbum(results.get(x).getNombreAlbum());
                itunes.setNombreArtista(results.get(x).getNombreArtista());
                itunes.setNombreTrack(results.get(x).getNombreTrack());
                itunes.setPreview(results.get(x).getPreview());
                itunes.setFoto(results.get(x).getFoto());

                vista.list.add(itunes);

            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(vista.list.size() > 0){
            vista.adapter.notifyDataSetChanged();
        }else{
            Intent i = new Intent(vista, MainActivity.class);
            vista.startActivity(i);
        }

        vista.reload.setRefreshing(false);
    }
}