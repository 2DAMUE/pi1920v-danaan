package uem.dam.seg.airmadrid.ui.mapa;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import uem.dam.seg.airmadrid.R;


public class MapaFragment extends Fragment implements OnMapReadyCallback {// Implementar oncallback

    private MapaViewModel mapaViewModel;
    private MapView mapView;
    private GoogleMap map;


    private FusedLocationProviderClient flClient;
    private Location miLoc;
    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mapa, container, false);

        mapView = root.findViewById(R.id.mapview);
        flClient = LocationServices.getFusedLocationProviderClient(getContext());

        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        } else {
            Log.i("LOC", "con permisos");
            flClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Log.i("LOC", "onSuccess de location");
                    if (location != null) {
                        miLoc = location;
                    }

                }

            });
        }

        /*SupportMapFragment mapFragment = (SupportMapFragment)getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.navigation_mapa);
        mapFragment.getMapAsync(this);*/

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapaViewModel = ViewModelProviders.of(this).get(MapaViewModel.class);
        mapaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        mapView.onCreate(savedInstanceState);//Llamada a creado de mapa Oncreate de la vista
        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);//Conecta con Server Google
    }

    //Continua despues de pausarse se va actualizando
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng uem = null;
        if (miLoc == null) {
            uem = new LatLng(40.535162, -3.6168482);
        } else {
            uem = new LatLng(miLoc.getLatitude(), miLoc.getLongitude());
        }
        /*private GoogleMap map, plazaDeEspaña, alcala, ramonYCajal, arturoSoria, juanPenialver, farolillo, casaDeCampo, jupiter, plazaDelCarmen, moratalaz;
        private GoogleMap pabloIglesias, betanzos, arrolloOlivar, juanDeMariana, joseGutierrez, venezuela, plazaCastilla, laGavia, rianio, plazaEliptica;
        private GoogleMap princesaDeEboli, laGuardia, juanCarlosI, tresOlivos;*/


        map.addMarker(new MarkerOptions().position(new LatLng(40.4238823, -3.7122567)).title("Plaza de España"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4215533, -3.6823158)).title("C/ Alcalá y C/ O'Donell"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4514734, -3.6773491)).title("Avda. Ramón y Cajal  esq. C/ Príncipe de Vergara"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4400457, -3.6392422)).title("C/ Arturo Soria  esq. C/  Vizconde de los Asilos"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.347147, 3.7133167)).title("C/. Juan Peñalver"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3947825, -3.7318356)).title("Calle Farolillo - C/Ervigio"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4193577, -3.7473445)).title("Casa de Campo  (Terminal del Teleférico)"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4769179, -3.5800258)).title("C/. Júpiter, 21 (Barajas)"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4192091, -3.7031662)).title("Plaza del Carmen esq. Tres Cruces."));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4079517, -3.6453104)).title("Avd. Moratalaz  esq. Camino de los Vinateros"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4455439, -3.7071303)).title("Avda. Pablo Iglesias esq. C/ Marqués de Lema"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4782322, -3.7115364)).title("Avd. Betanzos esq. C/  Monforte de Lemos"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3881478, -3.6515286)).title("C/ Arroyo del Olivar  esq. C/  Río Grande."));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3980991, -3.6868138)).title("C/ Juan de Mariana / Pza. Amanecer Mendez Alvaro"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4398904, -3.6903729)).title("C/ Jose Gutierrez Abascal"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.41444444444440, -3.6824999999999900)).title("Paseo Venezuela- Casa de Vacas"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4655841, -3.6887449)).title("Plaza Castilla (Canal)"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3730118, -3.6121394)).title("Avda La Gavia / Avda. Las Suertes"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4623628, -3.5805649)).title("C/ Riaño (Barajas)"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.3850336, -3.7187679)).title("Pza. Elíptica - Avda. Oporto"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4942012, -3.6605173)).title("C/ Princesa de Eboli esq C/ Maria Tudor"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.5180701, -3.7746101)).title("Avda. La Guardia"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.4607255, -3.6163407)).title("Parque Juan Carlos I (frente oficinas mantenimiento)"));
        map.addMarker(new MarkerOptions().position(new LatLng(40.5005477, -3.6897308)).title("Plaza Tres Olivos"));

        map.addMarker(new MarkerOptions().position(uem).title("UEM"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(uem, 14));

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);


        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14));
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Nueva posición")
                        .snippet("Lat: " +latLng.latitude + ", Long: " + latLng.longitude)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN))
                );
            }
        });
    }

}