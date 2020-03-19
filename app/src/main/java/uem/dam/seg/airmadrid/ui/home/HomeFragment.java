package uem.dam.seg.airmadrid.ui.home;

import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import uem.dam.seg.airmadrid.AirMadridApplication;
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
    private TextView tvSuma;
    Location closestStationLocation;

    private LinearLayout contenedor;
    private TextView tvGradoAlerta;
    private ImageView iwVentana;
    private ImageView iwBici;
    private ImageView iwMascarilla;
    private ImageView iwAire;
    private TextView msjRecomendaciones;

    private TextView tvLocalizacion;

    private int cont = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tvPM25 =  root.findViewById(R.id.tvValorPM25);
        tvPM10 = root.findViewById(R.id.tvValorPM10);
        tvSO2 = root.findViewById(R.id.tvValorSO2);
        tvNO2 = root.findViewById(R.id.tvValorNO2);
        tvSuma = root.findViewById(R.id.tvCifraGlobalH);
        tvLocalizacion = root.findViewById(R.id.tvLocalizacionH);
        contenedor = root.findViewById(R.id.contenedor);
        tvGradoAlerta = root.findViewById(R.id.tvGradoH);
        iwVentana = root.findViewById(R.id.iwRecomendacion1);
        iwBici = root.findViewById(R.id.iwRecomendacion2);
        iwMascarilla = root.findViewById(R.id.iwRecomendacion3);
        iwAire = root.findViewById(R.id.iwRecomendacion4);
        msjRecomendaciones = root.findViewById(R.id.mensajeRecomendaciones);

        AirMadridApplication airMadridApplication = (AirMadridApplication) getActivity().getApplicationContext();
        cont = airMadridApplication.getCont();
        if (cont == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("AirMadrid dice:");
            builder.setMessage("Recogiendo datos..");
            builder.setPositiveButton(R.string.cerrar, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

            cont++;

            airMadridApplication.setCont(cont);
        }

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
                    List<Datos.DatoHorario> magnitudes6 = new ArrayList<>();
                    List<Datos.DatoHorario> magnitudes14 = new ArrayList<>();

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

                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "6", lista);
                        if (dh != null) {
                            magnitudes6.add(dh);
                        }
                    }

                    for (LocalizacionEstacion estacion : estaciones) {
                        Datos.DatoHorario dh = getSelectedItem(estacion.getCodigo(), "14", lista);
                        if (dh != null) {
                            magnitudes14.add(dh);
                        }
                    }

                    //String del codigo de la estacion por su localizacion
                    String codigoEstacion = getStationWithLocation();

                    //String del nombre de la estacion con su localizacion
                    String nombreEstacion = getNameStationWithLocation().toUpperCase();
                    //Asignamos el nombre de la estación en su TextView
                    tvLocalizacion.setText(nombreEstacion);


                    // Cogemos el valor para esa hora y Mostramos el valor

                    tvPM25.setText(getMagnitud9FromEstacion(codigoEstacion, magnitudes9));
                    int iTvPM25;
                    //Controlamos con try catch si la estacion devuelve dicho valor (hay algunas que no dan todos los valores)
                    try{
                        iTvPM25 = Integer.parseInt(tvPM25.getText().toString());
                    }catch (NumberFormatException excepcion){
                        iTvPM25 = 0;
                        tvPM25.setText(R.string.dato_no_recogido); // Hay estaciones que no recogen todos los datos
                    }

                    tvPM10.setText(getMagnitud8FromEstacion(codigoEstacion, magnitudes8));
                    int iTvPM10;
                    try{
                        iTvPM10 = Integer.parseInt(tvPM10.getText().toString());
                    }catch(NumberFormatException excepcion){
                        iTvPM10 = 0;
                        tvPM10.setText(R.string.dato_no_recogido);
                    }

                    tvSO2.setText(getMagnitud1FromEstacion(codigoEstacion, magnitudes1));
                    int iTvSO2;
                    double ipSO2 = 0;
                    try{
                        iTvSO2 = Integer.parseInt(tvSO2.getText().toString());
                        ipSO2 = iTvSO2 * 0.286;

                    }catch(NumberFormatException excepcion){
                        iTvSO2 = 0;
                        tvSO2.setText(R.string.dato_no_recogido);
                    }

                    tvNO2.setText(getMagnitud10FromEstacion(codigoEstacion, magnitudes10));
                    int iTvNO2;
                    double ipNO2 = 0;
                    try{
                        iTvNO2 = Integer.parseInt(tvNO2.getText().toString());
                        ipNO2 = iTvNO2 * 0.5;
                    }catch(NumberFormatException excepcion){
                        iTvNO2 = 0;
                        tvNO2.setText(R.string.dato_no_recogido);
                    }


                    String sCO = getMagnitud6FromEstacion(codigoEstacion, magnitudes6).toString();
                    double CO;
                    double ipCO = 0;
                    try{
                        CO = Double.parseDouble(sCO);
                        ipCO = CO * 10;
                    }catch(NumberFormatException excepcion){
                        CO = 0;
                    }

                    String sO3 = getMagnitud14FromEstacion(codigoEstacion, magnitudes14).toString();
                    int O3;
                    double ipO3 = 0;
                    try{
                        O3 = Integer.parseInt(sO3);
                        ipO3 = O3 * 0.556;
                    }catch(NumberFormatException excepcion){
                        O3 = 0;
                    }

                    double ipMax = 0;

                    if (iTvPM25 > ipMax){
                        ipMax = iTvPM25;
                    }
                    if (iTvPM10 > ipMax){
                        ipMax = iTvPM10;
                    }
                    if (ipSO2 > ipMax){
                        ipMax = ipSO2;
                    }
                    if (ipNO2 > ipMax){
                        ipMax = ipNO2;
                    }
                    if (ipCO > ipMax){
                        ipMax = ipCO;
                    }
                    if (ipO3 > ipMax){
                        ipMax = ipO3;
                    }

                    int numIpMax = (int) ipMax;

                    tvSuma.setText(Integer.toString(numIpMax));

                    // cambio del color del fondo dependiendo de los valores
                    if (numIpMax >= 0 && numIpMax <= 50) {
                        contenedor.setBackground(getResources().getDrawable(R.drawable.scrim_gradient_green));
                        tvGradoAlerta.setText(R.string.grado_buena);

                        iwVentana.setImageResource(R.drawable.ventana_verde);
                        iwVentana.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_verde));
                                msjRecomendaciones.setText(R.string.ventana_verde);
                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_verde));

                                // para que al pulsar a en otro icono, se quite el color del borde
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwBici.setImageResource(R.drawable.bici_verde);
                        iwBici.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_verde));
                                msjRecomendaciones.setText(R.string.bici_verde);
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_verde));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwMascarilla.setImageResource(R.drawable.mascarilla_verde);
                        iwMascarilla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_verde));
                                msjRecomendaciones.setText(R.string.mascarilla_verde);
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_verde));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwAire.setImageResource(R.drawable.aire_verde);
                        iwAire.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_verde));
                                msjRecomendaciones.setText(R.string.aire_verde);
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_verde));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                    } else if (numIpMax >= 51 && numIpMax <= 100) {
                        contenedor.setBackground(getResources().getDrawable(R.drawable.scrim_gradient_orange));
                        tvGradoAlerta.setText(R.string.grado_moderada);

                        iwVentana.setImageResource(R.drawable.ventana_naranja);
                        iwVentana.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_naranja));
                                msjRecomendaciones.setText(R.string.ventana_naranja);
                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_naranja));

                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwBici.setImageResource(R.drawable.bici_naranja);
                        iwBici.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_naranja));
                                msjRecomendaciones.setText(R.string.bici_naranja);
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_naranja));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwMascarilla.setImageResource(R.drawable.mascarilla_naranja);
                        iwMascarilla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_naranja));
                                msjRecomendaciones.setText(R.string.mascarilla_naranja);
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_naranja));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwAire.setImageResource(R.drawable.aire_naranja);
                        iwAire.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_naranja));
                                msjRecomendaciones.setText(R.string.aire_naranja);
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_naranja));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                    } else {
                        contenedor.setBackground(getResources().getDrawable(R.drawable.scrim_gradient_red));
                        tvGradoAlerta.setText(R.string.grado_mala);

                        iwVentana.setImageResource(R.drawable.ventana_roja);
                        iwVentana.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_rojo));
                                msjRecomendaciones.setText(R.string.ventana_roja);
                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_rojo));

                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwBici.setImageResource(R.drawable.bici_roja);
                        iwBici.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_rojo));
                                msjRecomendaciones.setText(R.string.bici_roja);
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_rojo));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwMascarilla.setImageResource(R.drawable.mascarilla_roja);
                        iwMascarilla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_rojo));
                                msjRecomendaciones.setText(R.string.mascarilla_roja);
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_rojo));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });

                        iwAire.setImageResource(R.drawable.aire_rojo);
                        iwAire.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                msjRecomendaciones.setBackground(getResources().getDrawable(R.drawable.border_rojo));
                                msjRecomendaciones.setText(R.string.aire_rojo);
                                iwAire.setBackground(getResources().getDrawable(R.drawable.border_rojo));

                                iwVentana.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwBici.setBackground(getResources().getDrawable(R.drawable.border_images));
                                iwMascarilla.setBackground(getResources().getDrawable(R.drawable.border_images));
                            }
                        });
                    }

                    /*//Ponderacion de contaminantes
                    int suma = iTvNO2 + iTvSO2 + iTvPM10 + iTvPM25 + CO + O3;
                    tvSuma.setText(Integer.toString(suma));*/

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

    //Recogida de la estacion segun localizacion
    private String getNameStationWithLocation() {
        String response = "";
        for (LocalizacionEstacion dh : ((MainActivity) getActivity()).localizaciones) {
            if (dh.getLocalizacion().latitude == closestStationLocation.getLatitude() &&
                    dh.getLocalizacion().longitude == closestStationLocation.getLongitude()) {
                response = dh.getNombre();
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

    private Object getMagnitud6FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
        String response = "";
        for (Datos.DatoHorario dato : allItems) {
            if (dato.getEstacion().equals(estacion)) {
                response = getValueForHour(dato);
            }
        }
        return response;
    }

    private Object getMagnitud14FromEstacion(String estacion, List<Datos.DatoHorario> allItems) {
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