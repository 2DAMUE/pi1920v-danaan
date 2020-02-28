package uem.dam.seg.airmadrid.ui.chat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import uem.dam.seg.airmadrid.R;


public class ChatFragment extends Fragment {

    private ChatViewModel chatViewModel;
    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        /*Uri url = Uri.parse("https://console.dialogflow.com/api-client/demo/embedded/d724ac4a-8a93-4f44-ab26-528858305d50");
        Intent navegar = new Intent(Intent.ACTION_VIEW, url);
        if (navegar.resolveActivity(getActivity().getPackageManager()) != null) {
            getActivity().startActivity(navegar);
        }*/

        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        webView = v.findViewById(R.id.wv1);
        webView.loadUrl("https://console.dialogflow.com/api-client/demo/embedded/d724ac4a-8a93-4f44-ab26-528858305d50");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return v;

        /*chatViewModel =
                ViewModelProviders.of(this).get(ChatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chat, container, false);
        final TextView textView = root.findViewById(R.id.wv1);
        chatViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;*/

    }
}