package uem.dam.seg.airmadrid.ui.ajustes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import uem.dam.seg.airmadrid.LoginActivity;
import uem.dam.seg.airmadrid.R;

public class AjustesFragment extends Fragment {

    private AjustesViewModel ajustesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ajustesViewModel =
                ViewModelProviders.of(this).get(AjustesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ajustes, container, false);
        return root;
    }
}
