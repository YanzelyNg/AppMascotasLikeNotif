package com.yng.appmascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import static com.yng.appmascotas.MainActivity.NoCuenta;
import static com.yng.appmascotas.MainActivity.userInstagram;


public class SelCuentaInstagram extends AppCompatActivity {

    Spinner spinner_cuentas;
    ArrayAdapter<String> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sel_cuenta_instagram);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner_cuentas = (Spinner) findViewById(R.id.spinner_cuentas);

        List<String> cuentas = new ArrayList<String>();
        cuentas.add("mascotaspruebas");
        cuentas.add("paisajespruebasng");
        cuentas.add("florespruebasng");

        adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cuentas);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cuentas.setAdapter(adapter2);

    }

    public void onClickGuardaCuenta(View v)
    {
        Intent intent;
        NoCuenta = spinner_cuentas.getSelectedItemPosition();
        if(NoCuenta==1)
            userInstagram = "paisajespruebasng";
        else if(NoCuenta==2)
            userInstagram = "florespruebasng";
        else
            userInstagram = "mascotaspruebas";

        Log.i("RestApi", "Clase SelCuentaInstagram método onClickGuardaCuenta"+NoCuenta);

        intent = new Intent(SelCuentaInstagram.this, MainActivity.class);
        //intent.putExtra("mascotas", mascotas);
        startActivity(intent);
    }
}