package com.yng.appmascotas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;
import com.yng.appmascotas.adapter.MascotaAdaptador;
import com.yng.appmascotas.adapter.PageAdapter;
//import com.yng.appmascotas.fragment.ListaMascotaFragment;
import com.yng.appmascotas.fragment.PerfilFragment;
import com.yng.appmascotas.fragment.RecyclerViewFragment;
import com.yng.appmascotas.fragment.RecyclerViewPerfilFragment;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.IEndpointsApi;
import com.yng.appmascotas.restApi.adapter.RestApiAdapter;
import com.yng.appmascotas.restApi.model.UsuarioResponse;
//import static com.yng.appmascotas.fragment.ListaMascotaFragment.mascotas;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity<onCreateOptionsMenu> extends AppCompatActivity {
    //public MascotaAdaptador adaptador;
    //public static ArrayList<Mascota> mascotas;
    //private RecyclerView listaMascotas;
    public static int NoCuenta;
    public static String userInstagram="mascotaspruebas";
    public String token="";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG = "NotifTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();

                    }
                });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        setUpViewPager();
    }


/*    public void inicializarAdaptador()
    {
        adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }*/

    /*public void inicializarMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro,"Susi", 0));
        mascotas.add(new Mascota(R.drawable.mono,"Botas", 0));
        mascotas.add(new Mascota(R.drawable.conejo,"Saltitos", 0));
        mascotas.add(new Mascota(R.drawable.gato, "Minino", 0));
        mascotas.add(new Mascota(R.drawable.hamster,"Fisgon", 0));
        mascotas.add(new Mascota(R.drawable.pez,"Nemo", 0));
        mascotas.add(new Mascota(R.drawable.serpiente,"Snaky", 0));
    }*/

   public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater1 = getMenuInflater();

       getMenuInflater().inflate(R.menu.actionsview, menu);
       return true;
 //       return super.onCreateOptionsMenu(menu1);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent intent;

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {

            case R.id.action_star:
                Toast.makeText(getApplicationContext(), "Action_Star", Toast.LENGTH_LONG).show();

                intent = new Intent(MainActivity.this, RaitingMascotas.class);
                //intent.putExtra("mascotas", mascotas);
                startActivity(intent);
                break;

            case R.id.contacto:
                intent = new Intent(MainActivity.this, ContactoActivity.class);
                //intent.putExtra("mascotas", mascotas);
                startActivity(intent);
                break;

            case R.id.acerca:
                intent = new Intent(MainActivity.this, MyBioActivity.class);
                //intent.putExtra("mascotas", mascotas);
                startActivity(intent);
                break;

            case R.id.confCuenta:
                intent = new Intent(MainActivity.this, SelCuentaInstagram.class);
                //intent.putExtra("mascotas", mascotas);
                startActivity(intent);
                break;

            case R.id.recNotif:
                //enviarUsuarioRegistro("mascotas");
                enviarUsuarioRegistro();
                break;


        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        RecyclerViewPerfilFragment recyclerViewPerfilFragment = new RecyclerViewPerfilFragment();
        //recyclerViewPerfilFragment.setFotoPerfil();
        fragments.add(new RecyclerViewFragment());
        //fragments.add(new PerfilFragment());
        fragments.add(recyclerViewPerfilFragment);

        return fragments;
    }

    private void setUpViewPager()
    {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog);
        //viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
    }


    //private void enviarUsuarioRegistro(String token)
    private void enviarUsuarioRegistro()
    {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();

                    }
                });

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        IEndpointsApi endpoints = restApiAdapter.establecerConexionRestAPIHeroku();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, userInstagram);
        //Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token, "mascota");

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d(TAG, usuarioResponse.getId());
                Log.d(TAG, usuarioResponse.getId_dispositivo());
                Log.d(TAG, usuarioResponse.getUser());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
        /*usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d(TAG, usuarioResponse.getId());
                Log.d(TAG, usuarioResponse.getId_dispositivo());
                //Log.d(TAG, usuarioResponse.getUser());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });*/

    }
}