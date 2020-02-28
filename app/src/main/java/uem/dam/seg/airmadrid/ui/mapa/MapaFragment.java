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

        mapView = (MapView) root.findViewById(R.id.mapview);
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