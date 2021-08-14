package job.project.com.tupension.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Comentario implements Parcelable{
    private String usuario;
    private String comentario;

    public Comentario() {
    }

    public Comentario(String usuario, String comentario) {
        this.usuario = usuario;
        this.comentario = comentario;
    }

    protected Comentario(Parcel in) {
        usuario = in.readString();
        comentario = in.readString();
    }

    public static final Creator<Comentario> CREATOR = new Creator<Comentario>() {
        @Override
        public Comentario createFromParcel(Parcel in) {
            return new Comentario(in);
        }

        @Override
        public Comentario[] newArray(int size) {
            return new Comentario[size];
        }
    };

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(usuario);
        dest.writeString(comentario);
    }
}









