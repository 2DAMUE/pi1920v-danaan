package uem.dam.seg.airmadrid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private static final int PETICION_PERMISO_LOCALIZACION = 101;

    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etEmail;
    EditText etPwd;
    ProgressBar pb;
    Button btnEntrar;

    String email;
    String pwd;

    private FusedLocationProviderClient flClient;
    private Location miLoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        locPermission();

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etContrasena);
        pb = findViewById(R.id.progressBar);
        btnEntrar = findViewById(R.id.btnEntrar);

        pb.setVisibility(View.GONE);

        if (fu != null) {
            etEmail.setText(fu.getEmail());
        }
    }

    private void locPermission() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        }
    }

    public void entrar(View view) {
        String msj = validarDatos();

        if (msj != null) {
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
        } else {
            fa.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        fu = fa.getCurrentUser();

                        btnEntrar.setEnabled(false);

                        pb.setVisibility(View.VISIBLE);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                pb.setVisibility(View.GONE);
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }, 3000);

                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.msj_no_accede), Toast.LENGTH_SHORT).show();
                    } } });
        }

    }

    public void crearCuenta(View view) {
        Intent i = new Intent(this, RegistroActivity.class);
        startActivity(i);
    }

    private String validarDatos() {
        email = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();

        String msj = null;
        if (email.isEmpty() || pwd.isEmpty()) {
            msj = getString(R.string.no_datos);
        }

        return msj;
    }
}
