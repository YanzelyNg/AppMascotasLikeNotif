package com.yng.appmascotas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.db.BaseDatos;
import com.yng.appmascotas.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;


public class RaitingMascotas extends AppCompatActivity {
    public MascotaAdaptador adaptador;
    ArrayList<Mascota> mascotas;
    Context context;
    ArrayList<Mascota> mascotasOrder;
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raiting_mascotas);
        context = this;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotasTop5);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        top5Mascotas();
        inicializarAdaptador();
    }

    public void top5Mascotas()
    {
        BaseDatos db = new BaseDatos(context);
        mascotas= db.obtenerTodasLasMascotas();
    }

    public void inicializarAdaptador()
    {
        int i = 0;
        mascotasOrder = new ArrayList<Mascota>();
        Collections.sort(mascotas);


        for(i=0;i<5;i++) mascotasOrder.add(mascotas.get(i));

        adaptador = new MascotaAdaptador(mascotasOrder, this);
        listaMascotas.setAdapter(adaptador);
    }

}