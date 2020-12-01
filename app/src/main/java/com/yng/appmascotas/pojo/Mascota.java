package com.yng.appmascotas.pojo;


import java.io.Serializable;

public class Mascota implements Serializable, Comparable<Mascota>{

    private int id;
    private String nombre;
    private int foto;
    private int cantLikes;
    private String urlFoto;
    private String idInst;

    public Mascota() {
    }

    public Mascota(int foto, String nombre, int cantLikes) {
        this.foto = foto;
        this.nombre = nombre;
        this.cantLikes = cantLikes;
    }

    public Mascota(String urlFoto, String nombre, int cantLikes) {
        this.urlFoto = urlFoto;
        this.nombre = nombre;
        this.cantLikes = cantLikes;
    }

    public String getIdInst() {
        return idInst;
    }

    public void setIdInst(String idInst) {
        this.idInst = idInst;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public int getCantLikes() {
        return cantLikes;
    }

    public void setCantLikes(int cantLikes) {
        this.cantLikes = cantLikes;
    }

    @Override
    public int compareTo(Mascota mascota) {
        if(mascota.getCantLikes()>cantLikes){
            return 1;
        }
        else if(mascota.getCantLikes()==cantLikes){
            return 0;
        }
        else{
            return -1;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
