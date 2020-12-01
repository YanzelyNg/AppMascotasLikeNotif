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

import com.yng.appmascotas.db.ConstructorMascota;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.R;

import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreMascota.setText(mascota.getNombre());
        mascotaViewHolder.tvCantLike.setText(String.valueOf(mascota.getCantLikes()));


        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Diste like a " +mascota.getNombre(), Toast.LENGTH_LONG).show();
                ConstructorMascota constructorContactos = new ConstructorMascota(activity);
                constructorContactos.darLikeContacto(mascota);

                mascotaViewHolder.tvCantLike.setText(String.valueOf(constructorContactos.obtenerLikesMascotas(mascota)));

                Log.i("SQLlog", "Funcion: onBindViewHolder() -- clase: ContactoAdaptador cantlikes = " +constructorContactos.obtenerLikesMascotas(mascota));
            }

        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombreMascota;
        private TextView tvCantLike;
        private ImageButton btnLike;
        private ImageButton btncantLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto= (ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreMascota = (TextView) itemView.findViewById(R.id.tvNombreMascota);
            tvCantLike = (TextView) itemView.findViewById(R.id.tvCantLike);
            btnLike=(ImageButton) itemView.findViewById(R.id.btnLike);
            btncantLike=(ImageButton) itemView.findViewById(R.id.btncantLike);
        }
    }
}
