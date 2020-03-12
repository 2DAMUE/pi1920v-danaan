package uem.dam.seg.airmadrid.ui.home;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import uem.dam.seg.airmadrid.R;
import uem.dam.seg.airmadrid.javaBeans.Datos;
import uem.dam.seg.airmadrid.javaBeans.Estacion;
import uem.dam.seg.airmadrid.javaBeans.Estaciones;
import uem.dam.seg.airmadrid.retrofitUtils.APICalidadDelAireService;
import uem.dam.seg.airmadrid.retrofitUtils.RetrofitClient;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Retrofit r = new RetrofitClient().getClient();
        APICalidadDelAireService api = r.create(APICalidadDelAireService.class);
        Call<Datos> call = api.obtenerDatos();

        call.enqueue(new Callback<Datos>() {
            @Override
            public void onResponse(Call<Datos> call, Response<Datos> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Llega",
                            Toast.LENGTH_LONG).show();
                    Datos catalogo = response.body();
                    List<Datos.DatoHorario> lista = catalogo.getDatoHorario();
                    // cargarRv(lista);
                } else {
                    Log.e("ERROR", String.valueOf(response.code()));
                    Toast.makeText(getContext(),
                            "error: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Datos> call, Throwable t) {
                Toast.makeText(getContext(),
                        "error: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();

            }
        });
    }

    private List<Datos.DatoHorario> getSelectedItems(List<Datos.DatoHorario> allItems) {
        List<Datos.DatoHorario> response = new ArrayList<>();
        for (Datos.DatoHorario dh : allItems) {
            if (dh.getMagnitud().equals("01") ||
                    dh.getMagnitud().equals("02") ||
                    dh.getMagnitud().equals("06") ||
                    dh.getMagnitud().equals("09")) {
                response.add(dh);
            }
        }
        return response;
    }
}