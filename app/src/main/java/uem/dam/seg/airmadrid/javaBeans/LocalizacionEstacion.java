package uem.dam.seg.airmadrid.javaBeans;

import com.google.android.gms.maps.model.LatLng;

public class LocalizacionEstacion {

    private LatLng localizacion;
    private String codigo;
    private String nombre;

    public LocalizacionEstacion(LatLng localizacion, String codigo, String nombre) {
        this.localizacion = localizacion;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public LatLng getLocalizacion() {
        return localizacion;
    }

    public String getCodigo() {
        return codigo;
    }
    public String getNombre() {
        return nombre;
    }


    public void setLocalizacion(LatLng localizacion) {
        this.localizacion = localizacion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
