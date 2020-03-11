package uem.dam.seg.airmadrid.javaBeans;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "Datos")  //<Datos xmlns="http://bdca" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
public class Estaciones {

    @ElementList(name = "Dato_Horario", inline = true)
    private ArrayList<Estacion> listaEstaciones;

    public ArrayList<Estacion> getListaEstaciones() {
        return listaEstaciones;
    }

    public void setListaEstaciones(ArrayList<Estacion> listaEstaciones) {
        this.listaEstaciones = listaEstaciones;
    }
}
