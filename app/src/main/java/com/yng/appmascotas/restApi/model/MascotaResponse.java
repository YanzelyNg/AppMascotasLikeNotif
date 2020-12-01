package com.yng.appmascotas.restApi.model;

import com.yng.appmascotas.pojo.Mascota;

import java.util.ArrayList;

public class MascotaResponse {

    ArrayList<Mascota> mascotas;
    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }
    public void setContactos(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
