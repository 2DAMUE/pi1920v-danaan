package uem.dam.seg.airmadrid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView iv;
    ImageView ivHelice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        iv = findViewById(R.id.ivAirMadrid);
        ivHelice = findViewById(R.id.ivHelices);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        iv.startAnimation(animation);
        ivHelice.startAnimation(animation);

        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotation);
        ivHelice.startAnimation(rotation);

        openApp(true);
    }

    private void openApp(boolean locationPermission){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}

