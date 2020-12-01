package com.yng.appmascotas.fragment;

import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.adapter.MiMascotaAdaptador;
import com.yng.appmascotas.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewPerfilFragment {

    public void generarGridLayoutVertical();
    public MiMascotaAdaptador crearAdaptadorMiPet(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MiMascotaAdaptador adaptador);
}
