package job.project.com.tupension.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Plan implements Parcelable{
    private String tipo;
    private ArrayList<String> servicio;
    private int precio;

    public Plan() {
    }

    public Plan(String tipo, ArrayList<String> servicio, int precio) {
        this.tipo = tipo;
        this.servicio = servicio;
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getServicio() {
        return servicio;
    }

    public void setServicio(ArrayList<String> servicio) {
        this.servicio = servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.tipo);
        dest.writeStringList(this.servicio);
        dest.writeInt(this.precio);
    }

    protected Plan(Parcel in) {
        this.tipo = in.readString();
        this.servicio = in.createStringArrayList();
        this.precio = in.readInt();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel source) {
            return new Plan(source);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };
}
