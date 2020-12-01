package com.yng.appmascotas.presentador;

import android.content.Context;
import android.util.Log;

import com.yng.appmascotas.db.ConstructorMascota;
import com.yng.appmascotas.fragment.IRecyclerViewFragmentView;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.adapter.RestApiAdapter;
import com.yng.appmascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter{
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotaBaseDatos();
        //obtenerMediosRecientes();
    }
    @Override
    public void obtenerMascotaBaseDatos() {
        constructorMascota = new ConstructorMascota(context);
        mascotas = constructorMascota.obtenerDatos();
        //Log.i("SQLlog", "Funcion: obtenerContactosBaseDatos() -- clase RecyclerViewFragmentPresenter ");
        mostrarMascotaRV();
    }

    @Override
    public void mostrarMascotaRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {
 /*       RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndpointsApi endpointsApi = restApiAdapter.estConexionRestApiInstagram();
        Call<MascotaResponse> mascotaResponseCall =endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {

            }
        }); */
    }

    @Override
    public String getfotoInicial() {
        return null;
    }
}
