package com.example.justmotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

public class PantallaCargaPrincipal extends AppCompatActivity {

    ImageView Imagen_Fondo;
    TextView Nombre_App;
    LottieAnimationView Carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga_principal);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Imagen_Fondo = findViewById(R.id.Imagen_Fondo_Principal);
        Nombre_App = findViewById(R.id.Texto_Principal);
        Carga = findViewById(R.id.Lottie_Carga_Principal);

        Imagen_Fondo.animate().translationY(-2500).setDuration(1000).setStartDelay(3800);
        Nombre_App.animate().translationY(2000).setDuration(1000).setStartDelay(3800);
        Carga.animate().translationY(1500).setDuration(1000).setStartDelay(3800);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent (PantallaCargaPrincipal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4800);
    }
}