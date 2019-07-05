package adapters;

import android.content.Intent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import models.Itunes;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.pruebabci.R;
import com.pruebabci.VistaDetalleAlbum;
import com.pruebabci.VistaResultados;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorResultados extends ArrayAdapter<Itunes> {

    List<Itunes> modelList;
    VistaResultados context;
    private LayoutInflater mInflater;

    public AdaptadorResultados(VistaResultados context, List<Itunes> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public Itunes getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_resultados, parent, false);
            vh = ViewHolder.create((CoordinatorLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final Itunes item = getItem(position);

        vh.nombreArtista.setText(item.getNombreArtista());
        vh.nombreAlbum.setText(item.getNombreAlbum());
        vh.nombreTrack.setText(item.getNombreTrack());
        Picasso.with(context).load(item.getFoto()).fit().centerCrop().into(vh.image_album);

        vh.btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(item.getPreview()), "audio/*");
                context.startActivity(intent);
            }
        });

        vh.btn_ver_mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, VistaDetalleAlbum.class);
                i.putExtra("id_album", item.getIdAlbum());
                context.startActivity(i);
                context.overridePendingTransition(R.anim.two, R.anim.one);
            }
        });

        return vh.rootView;
    }

    private static class ViewHolder {
        final CoordinatorLayout rootView;
        final ImageView image_album;
        final TextView nombreArtista;
        final TextView nombreAlbum;
        final TextView nombreTrack;
        final Button btn_preview;
        final Button btn_ver_mas;

        private ViewHolder(CoordinatorLayout rootView, ImageView image_album, TextView nombreArtista, TextView nombreAlbum, TextView nombreTrack, Button btn_preview, Button btn_ver_mas) {
            this.rootView      = rootView;
            this.image_album   = image_album;
            this.nombreArtista = nombreArtista;
            this.nombreAlbum   = nombreAlbum;
            this.nombreTrack   = nombreTrack;
            this.btn_preview   = btn_preview;
            this.btn_ver_mas   = btn_ver_mas;
        }

        public static ViewHolder create(CoordinatorLayout rootView) {
            ImageView imagen            = rootView.findViewById(R.id.image_album);
            TextView txt_nombre_artista = rootView.findViewById(R.id.nombreArtista);
            TextView txt_nombre_album   = rootView.findViewById(R.id.nombreAlbum);
            TextView txt_track          = rootView.findViewById(R.id.nombreTrack);
            Button btn_preview          = rootView.findViewById(R.id.btn_preview);
            Button btn_ver_mas          = rootView.findViewById(R.id.btn_ver_mas);
            return new ViewHolder(rootView, imagen, txt_nombre_artista, txt_nombre_album, txt_track, btn_preview, btn_ver_mas);
        }
    }

}
