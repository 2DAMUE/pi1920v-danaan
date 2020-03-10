package uem.dam.seg.airmadrid.ui.redes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
        // final TextView textView = root.findViewById(R.id.text_redes);
        /*redesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
