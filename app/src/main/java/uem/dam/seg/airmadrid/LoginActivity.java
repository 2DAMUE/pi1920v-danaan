package uem.dam.seg.airmadrid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etEmail;
    EditText etPwd;
    ProgressBar pb;

    String email;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        etEmail = findViewById(R.id.etEmail);
        etPwd = findViewById(R.id.etContrasena);
        pb = findViewById(R.id.progressBar);

        pb.setVisibility(View.GONE);

        if (fu != null) {
            etEmail.setText(fu.getEmail());
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
