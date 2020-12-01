package com.yng.appmascotas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.yng.appmascotas.R;
import com.yng.appmascotas.db.ConstructorMascota;
import com.yng.appmascotas.fragment.IRecyclerViewFragmentView;
import com.yng.appmascotas.fragment.IRecyclerViewPerfilFragment;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.adapter.RestApiAdapter;
import com.yng.appmascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.yng.appmascotas.MainActivity.NoCuenta;

public class RecyclerViewPerfilFragmentPresenter implements IRecyclerViewFragmentPresenter{
    private IRecyclerViewPerfilFragment iRecyclerViewPerfilFragment;
    private Context context;
    private ConstructorMascota constructorMascota;
    private ArrayList<Mascota> mascotas;
    private ImageView circularImageView;

    /*public RecyclerViewPerfilFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        obtenerMascotaBaseDatos();
        //obtenerMediosRecientes();
    } */

    public RecyclerViewPerfilFragmentPresenter(IRecyclerViewPerfilFragment iRecyclerViewPerfilFragment, Context context)
    {
        this.iRecyclerViewPerfilFragment = iRecyclerViewPerfilFragment;
        this.context = context;

        //circularImageView= (ImageView) context.findViewById(R.id.imgFoto);
        obtenerMascotaBaseDatos();
    }

    @Override
    public void obtenerMascotaBaseDatos() {
        this.iRecyclerViewPerfilFragment = iRecyclerViewPerfilFragment;
        this.context = context;
        //obtenerMiMascotaBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void mostrarMascotaRV() {
        iRecyclerViewPerfilFragment.inicializarAdaptadorRV(iRecyclerViewPerfilFragment.crearAdaptadorMiPet(mascotas));
        iRecyclerViewPerfilFragment.generarGridLayoutVertical();
    }

    @Override
    public void obtenerMediosRecientes() {
        Call<MascotaResponse> mascotaResponseCall;
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();

        IEndpointsApi endpointsApi = restApiAdapter.estConexionRestApiInstagram(gsonMediaRecent);
        if(NoCuenta==1)
            mascotaResponseCall = endpointsApi.getRecentMediaPaisajesPrueba();
        else if(NoCuenta==2)
            mascotaResponseCall = endpointsApi.getRecentMediaFloresPrueba();
        else
            mascotaResponseCall = endpointsApi.getRecentMediaMascotasPrueba();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponse = response.body();
                Log.i("RestApi", "Clase PerfilFragment método obtenerMediosRecientes 1");

                mascotas = mascotaResponse.getMascotas();
                Log.i("RestApi", "Clase PerfilFragment método obtenerMediosRecientes 2");
                mostrarMascotaRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Log.i("SQLlog", "FALLÓ LA CONEXIÓN " + t.toString());
            }
        });


    }

    public String getfotoInicial()
    {
        Mascota mascotaInicial =mascotas.get(1);
        mascotaInicial.getUrlFoto();
        return mascotaInicial.getUrlFoto();
    }

    public void obtenerMiMascotaBaseDatos(){
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

        mostrarMascotaRV();
    }
}
