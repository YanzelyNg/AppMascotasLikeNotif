package com.yng.appmascotas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yng.appmascotas.R;
import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.adapter.MiMascotaAdaptador;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.presentador.IRecyclerViewFragmentPresenter;
import com.yng.appmascotas.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lista_mascota, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }
    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }


    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> contactos) {
        MascotaAdaptador adaptador = new MascotaAdaptador(contactos, getActivity());
        return adaptador;
    }


    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
