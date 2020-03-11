package uem.dam.seg.airmadrid.retrofitUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import uem.dam.seg.airmadrid.javaBeans.Estaciones;

public interface APICalidadDelAireService {

    /*Dióxido de Azufre SO2
    Dióxido de Nitrógeno NO2
    Partículas < 2.5 µm PM2.5
    Partículas < 10 µm PM10*/

    public static final String BASE_URL = "http://www.mambiente.madrid.es/opendata/";
    @GET("horario.xml")
    Call<Estaciones> obtenerDatos();
}
