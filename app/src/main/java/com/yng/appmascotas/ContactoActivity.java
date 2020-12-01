package com.yng.appmascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;

public class ContactoActivity extends AppCompatActivity {

    TextInputLayout tfNombre;
    TextInputLayout tfEmail;
    TextInputLayout tfMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tfNombre = (TextInputLayout) findViewById(R.id.tfNombre);
        tfEmail = (TextInputLayout) findViewById(R.id.tfEmail);
        tfMensaje = (TextInputLayout) findViewById(R.id.tfMensaje);
    }

    public void onClicSendMsn(View v)
    {
        String strtfNombre = "Comentario de: "+tfNombre.getEditText().getText().toString().trim();
        String strtfEmail = tfEmail.getEditText().getText().toString().trim();
        String strtfMensaje = tfMensaje.getEditText().getText().toString().trim();

        Intent email = new Intent(Intent.ACTION_SEND);

        email.putExtra(Intent.EXTRA_EMAIL, new String[]{strtfEmail});
        email.putExtra(Intent.EXTRA_SUBJECT, strtfNombre);
        email.putExtra(Intent.EXTRA_TEXT, strtfMensaje);

        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, getResources().getString(R.string.selecciona_email)));
    }
}