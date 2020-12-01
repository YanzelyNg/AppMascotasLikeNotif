package com.yng.appmascotas.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.yng.appmascotas.R;
//import com.yng.appmascotas.db.ConstructorMascota;
import static com.yng.appmascotas.MainActivity.userInstagram;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.adapter.RestApiAdapter;
import com.yng.appmascotas.restApi.model.UsuarioResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiMascotaAdaptador extends RecyclerView.Adapter<MiMascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;
    private static final String TAG1 = "MiMascotaAdaptadorTag";

    public MiMascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_miperfil, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        Picasso.get().load(mascota.getUrlFoto()).into(mascotaViewHolder.imgFoto);
        //mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvCantLike.setText(String.valueOf(mascota.getCantLikes()));

        mascotaViewHolder.btnLikePerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a una foto perfil se envía notificación a " +userInstagram, Toast.LENGTH_LONG).show();
                UsuarioResponse usuarioResponseOrg = new UsuarioResponse("tokenCelYng","123",userInstagram);
                //UsuarioResponse usuarioResponseOrg = new UsuarioResponse("tokenAppetize","123",userInstagram);
                RestApiAdapter restApiAdapter = new RestApiAdapter();
                IEndpointsApi endpoints = restApiAdapter.establecerConexionRestAPIHeroku();

                Call<UsuarioResponse> usuarioResponseCall = endpoints.diLike(usuarioResponseOrg.getId(),usuarioResponseOrg.getUser());
                Log.d(TAG1, "Diste Like a " + usuarioResponseOrg.getId());
                Log.d(TAG1, "Diste Like a " +usuarioResponseOrg.getId_dispositivo());

                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse = response.body();
                        String id_device = usuarioResponse.getId_dispositivo();
                        String user_gracias = usuarioResponse.getUser();
                        Log.d(TAG1, usuarioResponse.getId());
                        Log.d(TAG1, id_device);
                        Log.d(TAG1, user_gracias);

                        UsuarioResponse usuarioResponseThank = new UsuarioResponse("123",id_device,userInstagram);
                        RestApiAdapter restApiAdapter = new RestApiAdapter();
                        IEndpointsApi endpoints = restApiAdapter.establecerConexionRestAPIHeroku();

                        Call<UsuarioResponse> usuarioResponseCall1 = endpoints.graciasLike(id_device,userInstagram);
                        Log.d(TAG1, "Usuario gracias Diste Like a " + usuarioResponseThank.getId());
                        Log.d(TAG1, "Usuario gracias Diste Like a " +usuarioResponseThank.getId_dispositivo());
                        usuarioResponseCall1.enqueue(new Callback<UsuarioResponse>() {
                            @Override
                            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {

                            }

                            @Override
                            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });
                /*usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse1 = response.body();
                        String id1 = usuarioResponse1.getUser();
                        Log.d(TAG, "Diste Like a " + id1);
                        //Log.d(TAG, "Diste Like a " +usuarioResponse1.getId_dispositivo());
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });*/

            }

        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvCantLike;
        private ImageButton btncantLike;
        private ImageButton btnLikePerfil;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto= (ImageView) itemView.findViewById(R.id.imgFoto);
            tvCantLike = (TextView) itemView.findViewById(R.id.tvCantLike);
            btncantLike=(ImageButton) itemView.findViewById(R.id.btncantLike);
            btnLikePerfil=(ImageButton) itemView.findViewById(R.id.btnLikePerfil);
        }
    }
}
