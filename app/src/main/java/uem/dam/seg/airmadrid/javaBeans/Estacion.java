package uem.dam.seg.airmadrid.javaBeans;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Date;

@Root(name = "Dato_Horario")
public class Estacion {
    /*Dióxido de Azufre SO2
    Dióxido de Nitrógeno NO2
    Partículas < 2.5 µm PM2.5
    Partículas < 10 µm PM10*/

    Date fechaAct = new Date();
    String sFecAct = fechaAct.toString();

    @Element(name="estacion")
    private String nombreEstacion;
    @Element(name="magnitud")
    private String magnitud;
    @Element(name="H08")
    private String hora;
    @Element(name="LIGHT")
    private String luz;
    @Element(name="PRICE")
    private String precio;
    @Element(name="AVAILABILITY")
    private String disponibilidad;

    public Estacion() {}

    public Estacion(String nombreComun, String nomBotanico, String zona, String luz, String precio, String disponibilidad) {
        this.nombreEstacion = nombreEstacion;
        this.magnitud = magnitud;
        this.hora = hora;
        this.luz = luz;
        this.precio = precio;
        this.disponibilidad = disponibilidad;
    }

    public String getNombreComun() {
        return nombreEstacion;
    }

    public String getNomBotanico() {
        return magnitud;
    }

    public String getZona() {
        return hora;
    }

    public String getLuz() {
        return luz;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setNombreComun(String nombreComun) {
        this.nombreEstacion = nombreComun;
    }

    public void setNomBotanico(String nomBotanico) {
        this.magnitud = nomBotanico;
    }

    public void setZona(String zona) {
        this.hora = zona;
    }

    public void setLuz(String luz) {
        this.luz = luz;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
