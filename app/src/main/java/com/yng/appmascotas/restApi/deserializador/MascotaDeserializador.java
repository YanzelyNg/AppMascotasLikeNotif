package com.yng.appmascotas.restApi.deserializador;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yng.appmascotas.pojo.Mascota;
import com.yng.appmascotas.restApi.JsonKeys;
import com.yng.appmascotas.restApi.model.MascotaResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setContactos(deserializarMascotaDeJson(mascotaResponseData));

        return mascotaResponse;
    }

    private ArrayList<Mascota> deserializarMascotaDeJson(JsonArray mascotaResponseData)
    {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        for (int i=0; i<mascotaResponseData.size(); i++)
        {
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String id = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto = mascotaResponseDataObject.get(JsonKeys.USER_NAME).getAsString();
            String urlFotos = mascotaResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();

            Mascota mascotaActual = new Mascota(urlFotos,nombreCompleto,0);
            mascotaActual.setIdInst(id);
            mascotas.add(mascotaActual);
            Log.i("RestApi", "Clase ContactoDeserializador método deserializarContactoDeJson ID Dato( "+i+") = " +mascotaActual.getIdInst());
            Log.i("RestApi", "Clase ContactoDeserializador método deserializarContactoDeJson nombreCompleto Dato( "+i+") = " +mascotaActual.getNombre());
            Log.i("RestApi", "Clase ContactoDeserializador método deserializarContactoDeJson urlFotos Dato( "+i+") = " +mascotaActual.getUrlFoto());
            Log.i("RestApi", "Clase ContactoDeserializador método deserializarContactoDeJson likes Dato( "+i+") = " +mascotaActual.getCantLikes());

        }

        return mascotas;
    }
}
