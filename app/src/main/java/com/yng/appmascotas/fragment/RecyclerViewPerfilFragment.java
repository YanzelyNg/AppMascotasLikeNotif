package com.yng.appmascotas.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yng.appmascotas.R;
import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.adapter.MiMascotaAdaptador;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.presentador.IRecyclerViewFragmentPresenter;
import com.yng.appmascotas.presentador.RecyclerViewFragmentPresenter;
import com.yng.appmascotas.presentador.RecyclerViewPerfilFragmentPresenter;

import java.util.ArrayList;

public class RecyclerViewPerfilFragment extends Fragment implements IRecyclerViewPerfilFragment{
    //public MiMascotaAdaptador adaptador;
    public static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IRecyclerViewFragmentPresenter presenter;
    private ImageView circularImageView;
    private View v;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMiMascotas);
        //presenter = new RecyclerViewPerfilFragmentPresenter(this, getContext());
        presenter = new RecyclerViewPerfilFragmentPresenter(this, getContext());

        //listaMascotas.setLayoutManager(glm);

        //if(mascotas == null) inicializarListaContactos();
        //inicializarAdaptadorRV();

        return v;
    }


    public void setFotoPerfil()
    {
        circularImageView= (ImageView) v.findViewById(R.id.circularImageView);
        Picasso.get().load(presenter.getfotoInicial()).into(circularImageView);
    }

    @Override
    public void generarGridLayoutVertical() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 2);
        listaMascotas.setLayoutManager(glm);
    }


    @Override
    public MiMascotaAdaptador crearAdaptadorMiPet(ArrayList<Mascota> mascotas) {
        MiMascotaAdaptador adaptador = new MiMascotaAdaptador(mascotas, getActivity());
        return adaptador;

    }

    @Override
    public void inicializarAdaptadorRV(MiMascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }


/*    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMiMascotas);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaMascotas.setLayoutManager(glm);

        listaMascotas.setLayoutManager(glm);

        if(mascotas == null) inicializarListaContactos();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador()
    {
        adaptador = new MiMascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaContactos(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.snowball1,"Snowball", 1));
        mascotas.add(new Mascota(R.drawable.snowball2,"Snowball", 2));
        mascotas.add(new Mascota(R.drawable.snowball3,"Snowball", 3));
        mascotas.add(new Mascota(R.drawable.snowball4, "Snowball", 4));
        mascotas.add(new Mascota(R.drawable.snowball5,"Snowball", 1));
        mascotas.add(new Mascota(R.drawable.snowball6,"Snowball", 2));
        mascotas.add(new Mascota(R.drawable.snowball7,"Snowball", 4));
        mascotas.add(new Mascota(R.drawable.snowball8,"Snowball", 3));
        mascotas.add(new Mascota(R.drawable.snowball9,"Snowball", 3));
        mascotas.add(new Mascota(R.drawable.snowball10,"Snowball", 3));
        mascotas.add(new Mascota(R.drawable.snowball11,"Snowball", 3));
        mascotas.add(new Mascota(R.drawable.snowball12,"Snowball", 3));
    }*/
}
