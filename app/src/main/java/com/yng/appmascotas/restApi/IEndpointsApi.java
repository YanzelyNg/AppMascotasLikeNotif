package com.yng.appmascotas.restApi;

import com.yng.appmascotas.restApi.model.MascotaResponse;
import com.yng.appmascotas.restApi.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IEndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMediaMascotasPrueba();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER2)
    Call<MascotaResponse> getRecentMediaFloresPrueba();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER3)
    Call<MascotaResponse> getRecentMediaPaisajesPrueba();

    //Heroku
    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN)
    //Call<UsuarioResponse> registrarTokenID(@Field("token") String token);
    Call<UsuarioResponse> registrarTokenID(@Field("id_dispositivo") String token, @Field("user") String user);

    @GET(ConstantesRestApi.KEY_DISTE_LIKE)
    Call<UsuarioResponse> diLike(@Path("id") String id, @Path("user") String user);

    @GET(ConstantesRestApi.KEY_GRACIAS_LIKE)
    Call<UsuarioResponse> graciasLike(@Path("id_dispositivo") String id_dispositivo, @Path("user") String user);
}
