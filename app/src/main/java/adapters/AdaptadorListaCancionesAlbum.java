package adapters;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import models.ListaCanciones;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.pruebabci.R;
import com.pruebabci.VistaDetalleAlbum;
import com.pruebabci.VistaResultados;

import java.util.List;

public class AdaptadorListaCancionesAlbum extends ArrayAdapter<ListaCanciones> {

    List<ListaCanciones> modelList;
    VistaDetalleAlbum context;
    private LayoutInflater mInflater;

    public AdaptadorListaCancionesAlbum(VistaDetalleAlbum context, List<ListaCanciones> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public ListaCanciones getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {

            if(position % 2 == 0){
                View view = mInflater.inflate(R.layout.layout_detalle_album_uno, parent, false);
                vh = ViewHolder.create((CoordinatorLayout) view);
                view.setTag(vh);
            }else{
                View view = mInflater.inflate(R.layout.layout_detalle_album_dos, parent, false);
                vh = ViewHolder.create((CoordinatorLayout) view);
                view.setTag(vh);
            }


        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final ListaCanciones item = getItem(position);

        vh.nombre_cancion.setText(item.getNombre());

        vh.nombre_cancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(item.getPreview()), "audio/*");
                context.startActivity(intent);
            }
        });

        return vh.rootView;
    }

    private static class ViewHolder {
        final CoordinatorLayout rootView;
        final TextView nombre_cancion;

        private ViewHolder(CoordinatorLayout rootView, TextView nombre_cancion) {
            this.rootView       = rootView;
            this.nombre_cancion = nombre_cancion;
        }

        public static ViewHolder create(CoordinatorLayout rootView) {
            TextView nombre_cancion        = rootView.findViewById(R.id.nombre_cancion);
            return new ViewHolder(rootView, nombre_cancion);
        }
    }

}