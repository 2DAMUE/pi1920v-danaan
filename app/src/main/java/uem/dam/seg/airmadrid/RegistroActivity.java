package uem.dam.seg.airmadrid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistroActivity extends AppCompatActivity {

    private FirebaseAuth fa;
    private FirebaseUser fu;

    EditText etNomCom;
    EditText etEmail;
    EditText etPwd;

    String nomCom;
    String email;
    String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        fa = FirebaseAuth.getInstance();
        fu = fa.getCurrentUser();

        etNomCom = findViewById(R.id.etNombreCompReg);
        etEmail = findViewById(R.id.etMailReg);
        etPwd = findViewById(R.id.etPassReg);

        if (fu != null) {
            etEmail.setText(fu.getEmail());
        }
    }

    public void crearCuentaReg(View view) {
        String msj = validarDatos();

        if (msj != null) {
            Toast.makeText(this, msj, Toast.LENGTH_LONG).show();
        } else {
            // registrar al usuario
            fa.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        fu = fa.getCurrentUser();
                        /*Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(i);*/
                        Toast.makeText(RegistroActivity.this, getString(R.string.msj_registrado), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegistroActivity.this, getString(R.string.msj_no_registrado), Toast.LENGTH_SHORT).show();
                    } } });

        }
    }

    public void iniciarSesion(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    private String validarDatos() {
        //nomCom = etNomCom.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        pwd = etPwd.getText().toString().trim();

        String msj = null;
        if (/*nomCom.isEmpty() ||*/ email.isEmpty() || pwd.isEmpty()) {
            msj = getString(R.string.no_datos);
        }

        return msj;
    }
}
