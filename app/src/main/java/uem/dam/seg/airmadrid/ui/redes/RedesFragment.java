package uem.dam.seg.airmadrid.ui.redes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import uem.dam.seg.airmadrid.R;


public class RedesFragment extends Fragment {

    private RedesViewModel redesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        Uri url = Uri.parse("https://twitter.com/air_madrid");
        Intent navegar = new Intent(Intent.ACTION_VIEW, url);
        if (navegar.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(navegar);
        }

        redesViewModel =
                ViewModelProviders.of(this).get(RedesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_redes, container, false);
        return root;
    }
}
