package uem.dam.seg.airmadrid;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.seg.airmadrid.javaBeans.Estacion;
import uem.dam.seg.airmadrid.javaBeans.Estaciones;
import uem.dam.seg.airmadrid.javaBeans.LocalizacionEstacion;
import uem.dam.seg.airmadrid.retrofitUtils.APICalidadDelAireService;
import uem.dam.seg.airmadrid.retrofitUtils.RetrofitClient;

public class MainActivity extends AppCompatActivity {

    public List<LocalizacionEstacion> localizaciones = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4238823, -3.7122567), "004","Plaza de España"));//codigos de estacion del xml
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4215533, -3.6823158),"008","Escuelas Aguirre"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4514734, -3.6773491), "011","Avda. Ramón y Cajal"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4400457, -3.6392422),"016","Arturo Soria"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.347147, 3.7133167),"017","Villaverde"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.3947825, -3.7318356),"018","Farolillo"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4193577, -3.7473445),"024","Casa de Campo"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4769179, -3.5800258),"027","Barajas Pueblo"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4192091, -3.7031662),"035","Pza. del Carmen"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4079517, -3.6453104),"036","Moratalaz"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4455439, -3.7071303),"038","Cuatro Caminos"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4782322, -3.7115364),"039","Barrio del Pilar"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.3881478, -3.6515286),"040","Vallecas"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.3980991, -3.6868138),"047","Mendez Alvaro"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4398904, -3.6903729),"048","Castellana"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.41444444444440, -3.6824999999999900),"049","Parque del Retiro"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4655841, -3.6887449),"050","Plaza Castilla"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.3730118, -3.6121394),"054","Ensanche de Vallecas"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4623628, -3.5805649),"055","Urb. Embajada"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.3850336, -3.7187679),"056","Pza. Elíptica"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4942012, -3.6605173),"057","Sanchinarro"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.5180701, -3.7746101),"058","El Pardo"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.4607255, -3.6163407),"059","Juan Carlos I"));
        localizaciones.add(new LocalizacionEstacion(new LatLng(40.5005477, -3.6897308),"060","Tres Olivos"));

        /*map.addMarker(new MarkerOptions().position(new LatLng(40.347147, 3.7133167)).title("C/. Juan Peñalver").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3947825, -3.7318356)).title("Calle Farolillo - C/Ervigio").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4193577, -3.7473445)).title("Casa de Campo  (Terminal del Teleférico)").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4769179, -3.5800258)).title("C/. Júpiter, 21 (Barajas)").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4192091, -3.7031662)).title("Plaza del Carmen esq. Tres Cruces.").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4079517, -3.6453104)).title("Avd. Moratalaz  esq. Camino de los Vinateros").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4455439, -3.7071303)).title("Avda. Pablo Iglesias esq. C/ Marqués de Lema").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4782322, -3.7115364)).title("Avd. Betanzos esq. C/  Monforte de Lemos").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3881478, -3.6515286)).title("C/ Arroyo del Olivar  esq. C/  Río Grande.").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3980991, -3.6868138)).title("C/ Juan de Mariana / Pza. Amanecer Mendez Alvaro").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4398904, -3.6903729)).title("C/ Jose Gutierrez Abascal").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.41444444444440, -3.6824999999999900)).title("Paseo Venezuela- Casa de Vacas").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4655841, -3.6887449)).title("Plaza Castilla (Canal)").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3730118, -3.6121394)).title("Avda La Gavia / Avda. Las Suertes").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4623628, -3.5805649)).title("C/ Riaño (Barajas)").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3850336, -3.7187679)).title("Pza. Elíptica - Avda. Oporto").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4942012, -3.6605173)).title("C/ Princesa de Eboli esq C/ Maria Tudor").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.5180701, -3.7746101)).title("Avda. La Guardia").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4607255, -3.6163407)).title("Parque Juan Carlos I (frente oficinas mantenimiento)").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(new LatLng(40.5005477, -3.6897308)).title("Plaza Tres Olivos").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));*/

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_chat, R.id.navigation_mapa, R.id.navigation_home,R.id.navigation_redes,R.id.navigation_ajustes)
                .build();*/
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    public void infoPM25(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_info_PM25);
        builder.setMessage(R.string.titulo_info_PM25);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void infoPM10(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_info_PM10);
        builder.setMessage(R.string.titulo_info_PM10);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void infoSO2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_info_SO2);
        builder.setMessage(R.string.titulo_info_SO2);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void infoNO2(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_info_NO2);
        builder.setMessage(R.string.titulo_info_NO2);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
