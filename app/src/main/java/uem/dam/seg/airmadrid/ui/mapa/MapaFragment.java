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

import java.util.ArrayList;
import java.util.List;

import uem.dam.seg.airmadrid.MainActivity;
import uem.dam.seg.airmadrid.R;
import uem.dam.seg.airmadrid.javaBeans.LocalizacionEstacion;


public class MapaFragment extends Fragment implements OnMapReadyCallback {// Implementar oncallback

    private MapaViewModel mapaViewModel;
    private MapView mapView;
    private GoogleMap map;
    public List<LocalizacionEstacion> localizaciones = new ArrayList<>();

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

        localizaciones = ((MainActivity) getActivity()).localizaciones;
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


        /*localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));
        localizaciones.add(new LatLng(40.4238823, -3.7122567));*/


        map.addMarker(new MarkerOptions().position(localizaciones.get(0).getLocalizacion()).title("Plaza de España").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(1).getLocalizacion()).title("Escuelas Aguirre").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(2).getLocalizacion()).title("Avda. Ramón y Cajal").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(3).getLocalizacion()).title("Arturo Soria").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(4).getLocalizacion()).title("Villaverde").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(5).getLocalizacion()).title("Farolillo").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(6).getLocalizacion()).title("Casa de Campo").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(7).getLocalizacion()).title("Barajas Pueblo").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(8).getLocalizacion()).title("Pza. del Carmen").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(9).getLocalizacion()).title("Moratalaz").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(10).getLocalizacion()).title("Cuatro Caminos").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(11).getLocalizacion()).title("Barrio del Pilar").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(12).getLocalizacion()).title("Vallecas").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(13).getLocalizacion()).title("Mendez Alvaro").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(14).getLocalizacion()).title("Castellana").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(15).getLocalizacion()).title("Parque del Retiro").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(16).getLocalizacion()).title("Plaza Castilla").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(17).getLocalizacion()).title("Ensanche de Vallecas").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(18).getLocalizacion()).title("Urb. Embajada").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(19).getLocalizacion()).title("Pza. Elíptica").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(20).getLocalizacion()).title("Sanchinarro").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(21).getLocalizacion()).title("El Pardo").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(22).getLocalizacion()).title("Juan Carlos I").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
        map.addMarker(new MarkerOptions().position(localizaciones.get(23).getLocalizacion()).title("Tres Olivos").icon(BitmapDescriptorFactory.fromResource(R.drawable.molino3)));
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
                        .snippet("Lat: " + latLng.latitude + ", Long: " + latLng.longitude)
                        .icon(BitmapDescriptorFactory.defaultMarker(
                                BitmapDescriptorFactory.HUE_GREEN))
                );
            }
        });
    }

}