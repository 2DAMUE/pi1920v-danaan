package uem.dam.seg.airmadrid.retrofitUtils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import uem.dam.seg.airmadrid.javaBeans.Datos;

public interface APICalidadDelAireService {

    /*Lectura de Magnitudes
    Dióxido de Azufre SO2
    Dióxido de Nitrógeno NO2
    Partículas < 2.5 µm PM2.5
    Partículas < 10 µm PM10*/

    String BASE_URL = "http://www.mambiente.madrid.es/opendata/";

    @Headers({
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36"
    })
    @GET("horario.xml")
    Call<Datos> obtenerDatos();
}
