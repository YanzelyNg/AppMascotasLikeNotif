package com.yng.appmascotas.restApi.model;

public class UsuarioResponse {

    private String id;
    private String id_dispositivo;
    private String user;

    public UsuarioResponse(String id, String id_dispositivo, String user) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.user = user;
    }

    /*public UsuarioResponse(String id_dispositivo, String user) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.user = user;
    }*/

    public UsuarioResponse()
    {

    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
