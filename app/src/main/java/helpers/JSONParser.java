package helpers;

import androidx.annotation.NonNull;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JSONParser {


    private static Response response;

    public static JSONObject getDataFromWeb(String url_json) {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url_json)
                    .build();
            response = client.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e("Error desde JSONParser", "" + e.getLocalizedMessage());
        }
        return null;
    }

}