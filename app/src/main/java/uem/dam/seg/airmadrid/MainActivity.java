package uem.dam.seg.airmadrid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import uem.dam.seg.airmadrid.javaBeans.LocalizacionEstacion;

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

        BottomNavigationView navView = findViewById(R.id.nav_view);
        //Pasar cada ID de menú como un conjunto de ID porque cada menú debe considerarse como destinos de un nivel superior
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
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

    public void escenarios(View view) {
        Uri url = Uri.parse("https://puedocircular.com/#/");
        Intent navegar = new Intent(Intent.ACTION_VIEW, url);
        if (navegar.resolveActivity(getPackageManager()) != null) {
            startActivity(navegar);
        }
    }
}
