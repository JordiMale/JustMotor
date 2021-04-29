package com.example.justmotor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class PantallaCargaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga_principal);

        ImageView Imagen = findViewById(R.id.Logo_Pantalla_Principal);

        ImageView imageView = (ImageView)findViewById(R.id.Gif_Pantalla_Carga);
        Glide.with(getApplicationContext()).load(R.drawable.loading).into(imageView);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent (PantallaCargaPrincipal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}