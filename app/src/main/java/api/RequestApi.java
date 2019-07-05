package api;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.pruebabci.MainActivity;
import com.pruebabci.VistaResultados;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import androidx.annotation.Nullable;
import helpers.JSONParser;
import models.Itunes;

public class RequestApi extends AsyncTask<String, Void, Void> {

    private VistaResultados vista;

    public RequestApi(VistaResultados vista){
        this.vista = vista;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        vista.reload.setRefreshing(true);
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

                            Itunes itunes = new Itunes();

                            JSONObject item   = results.getJSONObject(i);

                            final String kind = item.getString("kind");

                            if(kind.equals("song")){

                                final int idAlbum           = item.getInt("collectionId");
                                final String nombreAlbum    = item.getString("collectionName");
                                final String nombreArtista  = item.getString("artistName");
                                final String nombreTrack    = item.getString("trackName");
                                final String preview        = item.getString("previewUrl");
                                final String foto           = item.getString("artworkUrl100");

                                itunes.setIdAlbum(idAlbum);
                                itunes.setNombreAlbum(nombreAlbum);
                                itunes.setNombreArtista(nombreArtista);
                                itunes.setNombreTrack(nombreTrack);
                                itunes.setPreview(preview);
                                itunes.setFoto(foto);

                                vista.list.add(itunes);

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

        if(vista.list.size() > 0){
            vista.adapter.notifyDataSetChanged();
        }else{
            Intent i = new Intent(vista, MainActivity.class);
            vista.startActivity(i);
        }

        vista.reload.setRefreshing(false);
    }

}