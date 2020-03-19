package uem.dam.seg.airmadrid;

import android.app.Application;

public class AirMadridApplication extends Application {
    private int cont;

    public void onCreate(){
        super.onCreate();
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
}
