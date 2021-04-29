package com.example.justmotor.ui.Ayuda;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.justmotor.R;


public class AyudaFragment extends Fragment {

    ImageView LLamarAyuda;
    ImageView GmailAyuda;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ayuda, container, false);

        LLamarAyuda = v.findViewById(R.id.Ayuda_Llamar);
        GmailAyuda = v.findViewById(R.id.Ayuda_Gmail);

        //Metode de llamar
        LLamar();

        //Metode de enviar gmail
        Gmail();



        return v;
    }

    private void Gmail() {
        GmailAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Gmail = "bysomsa@gmail.com";
                String Text = "Ajuda ";

                // Defino mi Intent y hago uso del objeto ACTION_SEND
                Intent intent = new Intent(Intent.ACTION_SEND);

                // Defino los Strings Email, Asunto y Mensaje con la función putExtra
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{Gmail});
                intent.putExtra(Intent.EXTRA_SUBJECT, Text);


                // Establezco el tipo de Intent
                intent.setType("message/rfc822");

                // Lanzo el selector de cliente de Correo
                startActivity(
                        Intent
                                .createChooser(intent,
                                        "Elije un cliente de Correo:"));

            }
        });
    }

    private void LLamar() {
        LLamarAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Numero = "647346431";

                if (!TextUtils.isEmpty(Numero)) {
                    String dial = "tel:" + Numero;
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                } else {
                    Toast.makeText(getContext(), "No hi ha un numero seleccionat", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}