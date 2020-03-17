package uem.dam.seg.airmadrid.ui.home;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.seg.airmadrid.MainActivity;
import uem.dam.seg.airmadrid.R;
import uem.dam.seg.airmadrid.javaBeans.Datos;
import uem.dam.seg.airmadrid.javaBeans.LocalizacionEstacion;
import uem.dam.seg.airmadrid.retrofitUtils.APICalidadDelAireService;
import uem.dam.seg.airmadrid.retrofitUtils.RetrofitClient;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView tvPM25;
    private TextView tvPM10;
    private TextView tvSO2;
    private TextView tvNO2;
    Location closestStationLocation;

    private TextView tvLocalizacion;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvPM25 =  root.findViewById(R.id.tvValorPM25);
        tvPM10 = root.findViewById(R.id.tvValorPM10);
        tvSO2 = root.findViewById(R.id.tvValorSO2);
        tvNO2 = root.findViewById(R.id.tvValorNO2);
        tvLocalizacion = root.findViewById(R.id.tvLocalizacionH);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LocationServices.getFusedLocationProviderClient(getContext()).getLastLocation()
                .addOnSuccessListener(getActivity(), location -> {
                    if (location != null) {
                        //Obtencion de la localizacion
                        closestStationLocation = getClosestLocation(location);
                        //obtenida la localizacion recogemos datos del xml
                        getXmlData();
                    }
                });
    }
    //Obtencion de datos del xml
    private void getXmlData() {
        Retrofit r = new RetrofitClient().getClient();
        APICalidadDelAireService api = r.create(APICalidadDelAireService.class);
        Call<Datos> call = api.obtenerDatos();

        call.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                if (response.isSuccessful()) {
                    Datos catalogo = response.body();
                    List<Datos.DatoHorario> lista = catalogo.getDatoHorario();
                    //Cogemos el item concreto
                    List<LocalizacionEstacion> estaciones = ((MainActivity) getActivity()).localizaciones;

                    //Listas con las magnitudes
                    List<Datos.DatoHorario> magnitudes1 = new ArrayList<>();
                    List<Datos.DatoHorario> magnitudes8 = new ArrayList<>();
                    List<Datos.DatoHorario> magnitudes9 = new ArrayList<>();
                    List<Datos.DatoHorario> magnitudes10 = new ArrayList<>();

                    //Add de magnitudes a las listas
                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "1", lista);
                        if (dh != null) {
                            magnitudes1.add(dh);
                        }
                    }
                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "8", lista);
                        if (dh != null) {
                            magnitudes8.add(dh);
                        }
                    }
                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "9", lista);
                        if (dh != null) {
                            magnitudes9.add(dh);
                        }
                    }
                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "10", lista);
                        if (dh != null) {
                            magnitudes10.add(dh);
                        }
                    }

                    //String del codigo de la estacion por su localizacion
                    String codigoEstacion = getStationWithLocation();

                    // Cogemos el valor para esa hora y Mostramos el valor
                    tvPM25.setText(getMagnitud9FromEstacion(codigoEstacion, magnitudes9));
                    tvPM10.setText(getMagnitud8FromEstacion(codigoEstacion, magnitudes8));
                    tvSO2.setText(getMagnitud1FromEstacion(codigoEstacion, magnitudes1));
                    tvNO2.setText(getMagnitud10FromEstacion(codigoEstacion, magnitudes10));
                } else {
                    Log.e("ERROR", String.valueOf(response.code()));
                    Toast.makeText(getContext(),
                            R.string.error_vuelve + response.code(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Toast.makeText(getContext(),
                        R.string.error_vuelve + t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }
    //Recogida de la estacion segun localizacion
    private String getStationWithLocation() {
        String response = "";
        for (LocalizacionEstacion dh : ((MainActivity) getActivity()).localizaciones) {
            if (dh.getLocalizacion().latitude == closestStationLocation.getLatitude() &&
                    dh.getLocalizacion().longitude == closestStationLocation.getLongitude()) {
                response = dh.getCodigo();
            }
        }

        return response;
    }
    //Recogida de la ubicacion  mas cercana
    private Location getClosestLocation(Location myLocation) {
        //Devuelve localizacion mas cercana a usuario de la lista de main activity
        //https://stackoverflow.com/a/35704721/4860142
        //Convertir location a lat long
        List<Location> stations = new ArrayList<>();
        for (LocalizacionEstacion ll : ((MainActivity) getActivity()).localizaciones) {
            Location location = new Location(LocationManager.GPS_PROVIDER);
            location.setLatitude(ll.getLocalizacion().latitude);
            location.setLongitude(ll.getLocalizacion().longitude);
            stations.add(location);
        }
        Location closestLocation = null;
        float smallestDistance = -1;

        for (Location location : stations) {
            float distance = myLocation.distanceTo(location);
            if (smallestDistance == -1 || distance < smallestDistance) {
                closestLocation = location;
                smallestDistance = distance;
            }
        }

        return closestLocation;
    }
    //Obtenemos magnitudes de la lista de los datos horarios
    private String getMagnitud9FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
        String response = "";
        for (Datos.DatoHorario dato : allItems) {
            if (dato.getEstacion().equals(estacion)) {
                response = getValueForHour(dato);
            }
        }
        return response;
    }

    private String getMagnitud1FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
        String response = "";
        for (Datos.DatoHorario dato : allItems) {
            if (dato.getEstacion().equals(estacion)) {
                response = getValueForHour(dato);
            }
        }
        return response;
    }

    private String getMagnitud8FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
        String response = "";
        for (Datos.DatoHorario dato : allItems) {
            if (dato.getEstacion().equals(estacion)) {
                response = getValueForHour(dato);
            }
        }
        return response;
    }

    private String getMagnitud10FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
        String response = "";
        for (Datos.DatoHorario dato : allItems) {
            if (dato.getEstacion().equals(estacion)) {
                response = getValueForHour(dato);
            }
        }
        return response;
    }

    /*private List<String> getAllStations(List<Datos.DatoHorario> allItems) {
        List<String> response = new ArrayList<>();
        for (Datos.DatoHorario dh : allItems) {
            if (!response.contains(dh.getEstacion())) {
                response.add(dh.getEstacion());
            }
        }
        return response;
    }*/

    /*private List<Datos.DatoHorario> getSelectedItems(List<Datos.DatoHorario> allItems) {
        List<Datos.DatoHorario> response = new ArrayList<>();
        for (Datos.DatoHorario dh : allItems) {

    Magnitudes a recoger
    Dióxido de Azufre SO2
    Dióxido de Nitrógeno NO2
    Partículas < 2.5 µm PM2.5
    Partículas < 10 µm PM10

            if (dh.getMagnitud().equals("1") ||
                    dh.getMagnitud().equals("8") ||
                    dh.getMagnitud().equals("9") ||
                    dh.getMagnitud().equals("10")) {
                response.add(dh);
            }
        }
        return response;
    }*/

    private Datos.DatoHorario getSelectedItem(String estacion, String magnitud, List<Datos.DatoHorario> allItems) {
        Datos.DatoHorario response = new Datos.DatoHorario();
        for (Datos.DatoHorario dh : allItems) {
            if (dh.getEstacion().equals(estacion) && dh.getMagnitud().equals(magnitud)) {
                response = dh;
            }
        }
        if (response.getEstacion() != null) {
            return response;
        } else {
            return null;
        }
    }

    private String getValueForHour(Datos.DatoHorario dato) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String response = "Dato horario";
        switch (hour) {
            case 1:
                response = dato.getH01();
                break;
            case 2:
                response = dato.getH01();
                break;
            case 3:
                response = dato.getH02();
                break;
            case 4:
                response = dato.getH03();
                break;
            case 5:
                response = dato.getH04();
                break;
            case 6:
                response = dato.getH05();
                break;
            case 7:
                response = dato.getH06();
                break;
            case 8:
                response = dato.getH07();
                break;
            case 9:
                response = dato.getH08();
                break;
            case 10:
                response = dato.getH09();
                break;
            case 11:
                response = dato.getH10();
                break;
            case 12:
                response = dato.getH11();
                break;
            case 13:
                response = dato.getH12();
                break;
            case 14:
                response = dato.getH13();
                break;
            case 15:
                response = dato.getH14();
                break;
            case 16:
                response = dato.getH15();
                break;
            case 17:
                response = dato.getH16();
                break;
            case 18:
                response = dato.getH17();
                break;
            case 19:
                response = dato.getH18();
                break;
            case 20:
                response = dato.getH19();
                break;
            case 21:
                response = dato.getH20();
                break;
            case 22:
                response = dato.getH21();
                break;
            case 23:
                response = dato.getH22();
                break;
            case 24:
                response = dato.getH23();
                break;
            default:
                break;
        }

        return response;
    }
}