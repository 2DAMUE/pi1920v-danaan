package uem.dam.seg.airmadrid.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import uem.dam.seg.airmadrid.R;


public class ChatFragment extends Fragment {

    private WebView webView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        webView = v.findViewById(R.id.wv1);
        webView.loadUrl("https://console.dialogflow.com/api-client/demo/embedded/d724ac4a-8a93-4f44-ab26-528858305d50");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        return v;

    }
}