package models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BD extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "itunes";

    private final String collection  = "CREATE TABLE collection  (id_collection INTEGER, nombreAlbum TEXT, nombreArtista TEXT, imagen TEXT, copy TEXT)";
    private final String track       = "CREATE TABLE track (trackName TEXT, previewUrl TEXT, id_collection INTEGER)";

    public BD(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(collection);
        db.execSQL(track);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS collection");
        db.execSQL("DROP TABLE IF EXISTS track");

        db.execSQL(collection);
        db.execSQL(track);
    }

    public boolean exist_collection(int id_collection) {

        String countQuery = "SELECT * FROM collection WHERE id_collection = " + id_collection + "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor     = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        return (count > 0);
    }

    public boolean insert_collection(int id_collection, String nombreAlbum,  String nombreArtista, String imagen, String copy){

        SQLiteDatabase db             = this.getWritableDatabase();
        ContentValues nuevaCollection = new ContentValues();

        nuevaCollection.put("id_collection", id_collection);
        nuevaCollection.put("nombreAlbum", nombreAlbum);
        nuevaCollection.put("nombreArtista", nombreArtista);
        nuevaCollection.put("imagen", imagen);
        nuevaCollection.put("copy", copy);
        long result = db.insert("collection", null, nuevaCollection);

        if (result == -1)
            return false;

        return true;
    }

    public boolean insert_track(String trackName, String previewUrl, int id_collection){

        SQLiteDatabase db        = this.getWritableDatabase();
        ContentValues nuevoTrack = new ContentValues();

        nuevoTrack.put("trackName", trackName);
        nuevoTrack.put("previewUrl", previewUrl);
        nuevoTrack.put("id_collection", id_collection);
        long result = db.insert("track", null, nuevoTrack);

        if (result == -1)
            return false;

        return true;
    }


    public ArrayList<Itunes> allResults(String query) {

        ArrayList<Itunes> result = new ArrayList<>();
        String selectQuery       = "SELECT c.id_collection as id_collection, c.nombreAlbum as nombreAlbum, " +
                                    "c.nombreArtista as nombreArtista, c.imagen as imagen, t.trackName as trackName, " +
                                    "t.previewUrl as previewUrl FROM collection c INNER JOIN track t ON c.id_collection = t.id_collection " +
                                    "WHERE c.nombreAlbum LIKE '%"+query+"%' OR c.nombreArtista LIKE '%"+query+"%' OR t.trackName LIKE '%"+query+"%' LIMIT 20";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor     = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Itunes i = new Itunes();
                i.setIdAlbum(cursor.getInt(cursor.getColumnIndex("id_collection")));
                i.setNombreAlbum(cursor.getString(cursor.getColumnIndex("nombreAlbum")));
                i.setNombreArtista(cursor.getString(cursor.getColumnIndex("nombreArtista")));
                i.setNombreTrack(cursor.getString(cursor.getColumnIndex("trackName")));
                i.setPreview(cursor.getString(cursor.getColumnIndex("previewUrl")));
                i.setFoto(cursor.getString(cursor.getColumnIndex("imagen")));

                result.add(i);
            } while (cursor.moveToNext());
        }

        db.close();

        return result;
    }

    public ArrayList<Itunes> getAlbum(int idCollection) {

        ArrayList<Itunes> album = new ArrayList<>();
        String selectQuery       = "SELECT id_collection, nombreAlbum, " +
                                    "nombreArtista, imagen FROM collection WHERE id_collection = "+ idCollection;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor     = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Itunes i = new Itunes();
                i.setIdAlbum(cursor.getInt(cursor.getColumnIndex("id_collection")));
                i.setNombreAlbum(cursor.getString(cursor.getColumnIndex("nombreAlbum")));
                i.setNombreArtista(cursor.getString(cursor.getColumnIndex("nombreArtista")));
                i.setFoto(cursor.getString(cursor.getColumnIndex("imagen")));

                album.add(i);
            } while (cursor.moveToNext());
        }

        db.close();

        return album;
    }

    public ArrayList<ListaCanciones> getAllTrackAlbum(int idCollection) {

        ArrayList<ListaCanciones> track = new ArrayList<>();
        String selectQuery       = "SELECT id_collection, trackName, previewUrl FROM track WHERE id_collection = "+ idCollection;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor     = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ListaCanciones lista = new ListaCanciones();
                lista.setIdAlbum(cursor.getInt(cursor.getColumnIndex("id_collection")));
                lista.setNombre(cursor.getString(cursor.getColumnIndex("trackName")));
                lista.setPreview(cursor.getString(cursor.getColumnIndex("previewUrl")));

                track.add(lista);
            } while (cursor.moveToNext());
        }

        db.close();

        return track;
    }
}