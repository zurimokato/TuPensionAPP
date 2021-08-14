package job.project.com.tupension.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordenada implements Parcelable{

    private double lat;
    private double lon;

    public Coordenada() {
    }

    public Coordenada(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }


    protected Coordenada(Parcel in) {
        lat = in.readDouble();
        lon = in.readDouble();
    }

    public static final Creator<Coordenada> CREATOR = new Creator<Coordenada>() {
        @Override
        public Coordenada createFromParcel(Parcel in) {
            return new Coordenada(in);
        }

        @Override
        public Coordenada[] newArray(int size) {
            return new Coordenada[size];
        }
    };

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(lat);
        dest.writeDouble(lon);
    }
}
