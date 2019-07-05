package models;

public class Itunes {

    private int idAlbum;
    private String nombreAlbum;
    private String nombreArtista;
    private String nombreTrack;
    private String preview;
    private String copy;
    private String foto;
    private String listaCanciones;

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public String getNombreArtista() {
        return nombreArtista;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public String getNombreTrack() {
        return nombreTrack;
    }

    public void setNombreTrack(String nombreTrack) {
        this.nombreTrack = nombreTrack;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getListaCanciones() {
        return listaCanciones;
    }

    public void setListaCanciones(String listaCanciones) {
        this.listaCanciones = listaCanciones;
    }


    @Override
    public String toString() {
        return "Itunes{" +
                "idAlbum=" + idAlbum +
                ", nombreAlbum='" + nombreAlbum + '\'' +
                ", nombreArtista='" + nombreArtista + '\'' +
                ", foto='" + foto + '\'' +
                ", listaCanciones='" + listaCanciones + '\'' +
                '}';
    }
}
