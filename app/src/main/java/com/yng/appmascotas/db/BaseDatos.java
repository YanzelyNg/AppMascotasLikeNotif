package com.yng.appmascotas.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
import com.yng.appmascotas.pojo.Mascota;

import java.util.ArrayList;

public class BaseDatos extends SQLiteOpenHelper {
    private Context context;

    public BaseDatos(@Nullable Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("SQLlog", "Funcion: INICIO onCreate() -- clase BaseDatos ");

        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "("+
                ConstantesBaseDatos.TABLE_MASCOTAS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE+" TEXT, "+
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO+" INTEGER"+
                ")";

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA + "("+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA+" INTEGER, "+
                ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+" INTEGER, "+
                "FOREIGN KEY ("+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA+") "+
                "REFERENCES "+ConstantesBaseDatos.TABLE_MASCOTAS+"("+ConstantesBaseDatos.TABLE_MASCOTAS_ID+")"+
                ")";


        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);

        Log.i("SQLlogEsp", "Funcion: onCreate() -- clase BaseDatos ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        Log.i("SQLlogEsp", "Funcion: onUpgrade() -- clase BaseDatos ");
        onCreate(db);
    }

    public void ResetDatabase()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA);
        Log.i("SQLlogEsp", "Funcion: onUpgrade() -- clase BaseDatos ");
        onCreate(db);
    }


    public ArrayList<Mascota> obtenerTodasLasMascotas()
    {
        Log.i("SQLlog", "Funcion: obtenerTodosLosContactos() -- clase BaseDatos ");
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor registros = db.rawQuery(query, null);

        Log.i("SQLlog", "Funcion: obtenerTodosLosContactos() -- clase BaseDatos ---registros = "+registros);

        while (registros.moveToNext())
        {
            Mascota mascotaActual = new Mascota();
            String nombreRegistro = registros.getString(1);
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes = "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES +") as likes"+
                    " FROM "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                    " WHERE "+ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + " =" + mascotaActual.getId();

            Log.i("SQLlog", "Funcion: obtenerTodosLosContactos() -- clase BaseDatos busca cantidad de likes query = \n" +queryLikes);

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext())
            {
                mascotaActual.setCantLikes(registrosLikes.getInt(0));
            }
            else
            {
                mascotaActual.setCantLikes(0);
            }
            Log.i("SQLlog", "Funcion: obtenerTodosLosContactos() -- clase BaseDatos Dentro del while");
            Log.i("SQLlog", nombreRegistro);
            //contactoActual.setLikes();

            mascotas.add(mascotaActual);
        }

        db.close();

        return mascotas;
    }


    public void insertarMascota(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);

        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTA, null, contentValues);

        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota)
    {
        int likes = 0;

        String query = "SELECT COUNT(" +ConstantesBaseDatos.TABLE_LIKES_MASCOTA_NUMERO_LIKES+ ")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTA_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();

        Log.i("SQLlog", "Funcion: obtenerLikesContacto() -- clase BaseDatos  query = " + query);

        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext())
        {
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }
}
