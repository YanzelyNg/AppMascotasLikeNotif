package com.yng.appmascotas.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yng.appmascotas.restApi.ConstantesRestApi;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.deserializador.MascotaDeserializador;
import com.yng.appmascotas.restApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {
    public IEndpointsApi estConexionRestApiInstagram(Gson gson)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(IEndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent()
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

        return gsonBuilder.create();
    }

    public IEndpointsApi establecerConexionRestAPIHeroku(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_HEROKU )
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(IEndpointsApi.class);
    }
}
