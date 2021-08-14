package job.project.com.tupension.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pension implements Parcelable{

    private String nombre;
    private String imagen;
    private float valoracion;
    private String descripcion;
    private ArrayList<Plan> plan;
    private String direccion;
    private Persona persona;
    private ArrayList<Comentario>comentarios;
    private Coordenada coordenadas;

    public Pension() {
    }

    public Pension(String nombre, String imagen, float valoracion, String descripcion, ArrayList<Plan> plan, String direccion, Persona persona, ArrayList<Comentario> comentarios, Coordenada coordenadas) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.valoracion = valoracion;
        this.descripcion = descripcion;
        this.plan = plan;
        this.direccion = direccion;
        this.persona = persona;
        this.comentarios = comentarios;
        this.coordenadas = coordenadas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valoracion) {
        this.valoracion = valoracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Plan> getPlan() {
        return plan;
    }

    public void setPlan(ArrayList<Plan> plan) {
        this.plan = plan;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Coordenada getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(Coordenada coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        return "Pension{" +
                "nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", valoracion=" + valoracion +
                ", descripcion='" + descripcion + '\'' +
                ", plan=" + plan +
                ", direccion='" + direccion + '\'' +
                ", persona=" + persona +
                ", comentarios=" + comentarios +
                ", coordenadas=" + coordenadas +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.imagen);
        dest.writeFloat(this.valoracion);
        dest.writeString(this.descripcion);
        dest.writeTypedList(this.plan);
        dest.writeString(this.direccion);
        dest.writeParcelable(this.persona, flags);
        dest.writeTypedList(this.comentarios);
        dest.writeParcelable(this.coordenadas, flags);
    }

    protected Pension(Parcel in) {
        this.nombre = in.readString();
        this.imagen = in.readString();
        this.valoracion = in.readFloat();
        this.descripcion = in.readString();
        this.plan = in.createTypedArrayList(Plan.CREATOR);
        this.direccion = in.readString();
        this.persona = in.readParcelable(Persona.class.getClassLoader());
        this.comentarios = in.createTypedArrayList(Comentario.CREATOR);
        this.coordenadas = in.readParcelable(Coordenada.class.getClassLoader());
    }

    public static final Creator<Pension> CREATOR = new Creator<Pension>() {
        @Override
        public Pension createFromParcel(Parcel source) {
            return new Pension(source);
        }

        @Override
        public Pension[] newArray(int size) {
            return new Pension[size];
        }
    };
}
