package uem.dam.seg.airmadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    public void crearCuenta(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }
}
