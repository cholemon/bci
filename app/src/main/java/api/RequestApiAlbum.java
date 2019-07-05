package api;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import com.pruebabci.VistaDetalleAlbum;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.annotation.Nullable;
import helpers.HelperListViewCanciones;
import helpers.JSONParser;
import models.BD;
import models.ListaCanciones;

public class RequestApiAlbum extends AsyncTask<String, Void, Void> {

    private VistaDetalleAlbum vista;

    private String nombreAlbum    = "";
    private String nombreArtista  = "";
    private String copy           = "";
    private String foto           = "";

    private BD bd;
    private boolean band = false;

    public RequestApiAlbum(VistaDetalleAlbum vista){
        this.vista = vista;
        bd         = new BD(vista);
        vista.layout_loading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Nullable
    @Override
    protected Void doInBackground(String... params) {

        JSONObject res = JSONParser.getDataFromWeb(params[0]);

        try {

            if (res != null) {

                if(res.length() > 0) {

                    JSONArray results = res.getJSONArray("results");
                    int len           = results.length();

                    if(len > 0) {

                        for(int i = 0; i < len; i++) {

                            JSONObject item           = results.getJSONObject(i);

                            final String wrapperType  = item.getString("wrapperType");
                            final int idAlbum         = item.getInt("collectionId");

                            if(wrapperType.equals("collection")){
                                nombreAlbum    = item.getString("collectionName");
                                nombreArtista  = item.getString("artistName");
                                copy           = item.getString("copyright");
                                foto           = item.getString("artworkUrl100");

                                if(!bd.exist_collection(idAlbum)){
                                    band = true;
                                    bd.insert_collection(idAlbum, nombreAlbum, nombreArtista, foto, copy);
                                }

                            }else{
                                ListaCanciones lista = new ListaCanciones();

                                final String nombreTrack = item.getString("trackName");
                                final String preview     = item.getString("previewUrl");

                                lista.setIdAlbum(idAlbum);
                                lista.setNombre(nombreTrack);
                                lista.setPreview(preview);

                                vista.lista_canciones.add(lista);

                                if(band){
                                    bd.insert_track(nombreTrack, preview, idAlbum);
                                }
                            }

                        }
                    }
                }
            }else{
                Log.i(vista.getPackageName(), "No hay datos para mostrar.");
            }
        } catch (JSONException je) {
            Log.i("Tarea Asincrona Fallida", "" + je.getLocalizedMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

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