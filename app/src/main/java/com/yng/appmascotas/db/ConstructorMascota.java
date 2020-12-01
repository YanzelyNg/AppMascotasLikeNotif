package com.yng.appmascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.yng.appmascotas.R;
import com.yng.appmascotas.pojo.Mascota;

import java.util.ArrayList;

public class ConstructorMascota {
    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascota(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos()
    {
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        Log.i("SQLlog", "Funcion: obtenerDatos() -- clase: ConstructorContactos");

        BaseDatos db = new BaseDatos(context);
        //context.deleteDatabase(DATABASE_NAME);

        mascotas = db.obtenerTodasLasMascotas();

        //if(mascotas.size() == 0)
        //{
            insertarMascotaDb(db);
            return db.obtenerTodasLasMascotas();
        //}
        //else return mascotas;

    }

    public void insertarMascotaDb(BaseDatos db)
    {
        db.ResetDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Susi");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.perro);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Snowball");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.snowball2);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Botas");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mono);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Saltitos");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.conejo);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Minino");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.gato);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Fisgon");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.hamster);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Nemo");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.pez);
        db.insertarMascota(contentValues);

        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Snaky");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.serpiente);
        db.insertarMascota(contentValues);

    }



    public void darLikeContacto(Mascota mascota)
    {
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
        Log.i("SQLlog", "Funcion: darLikeContacto() -- clase: ConstructorContactos contacto.getId() =" + mascota.getId());
    }

    public int obtenerLikesMascotas(Mascota mascota)
    {
        Log.i("SQLlog", "Funcion: obtenerLikesContacto() -- clase: ConstructorContactos");
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }
}
