package com.yng.appmascotas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yng.appmascotas.R;
//import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.adapter.MiMascotaAdaptador;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.adapter.RestApiAdapter;
import com.yng.appmascotas.restApi.model.MascotaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MiMascotaAdaptador adaptador;
    public static ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */

    //public MascotaAdaptador adaptador;
    //public static ArrayList<Mascota> mascotas;


    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View v = inflater.inflate(R.layout.fragment_lista_mascota, container, false);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMiMascotas);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaMascotas.setLayoutManager(glm);

        /*LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);*/
        listaMascotas.setLayoutManager(glm);

        //if(mascotas == null) inicializarListaContactos();
        obtenerMediosRecientes();
        //inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador()
    {
        adaptador = new MiMascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();

        IEndpointsApi endpointsApi = restApiAdapter.estConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMediaMascotasPrueba();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {

                MascotaResponse mascotaResponse = response.body();
                Log.i("RestApi", "Clase PerfilFragment método obtenerMediosRecientes 1");

                mascotas = mascotaResponse.getMascotas();
                Log.i("RestApi", "Clase PerfilFragment método obtenerMediosRecientes 2");
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Log.i("SQLlog", "FALLÓ LA CONEXIÓN " + t.toString());
            }
        });
        inicializarAdaptador();
    }

    public void inicializarListaContactos(){
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
    }
}