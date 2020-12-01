package com.yng.appmascotas.restApi;

public final class ConstantesRestApi {
    public static final String ROOT_URL = "https://graph.instagram.com/";

    /* Datos usuario = mascotasprueba*/
    public static final String ACCESS_TOKEN = "IGQVJVbFZAzN3BCSW11cWhwYVl5aEpnSEVKWTB3VzBTZAGZAhOGZAFdEs5d3R3c0VVLVd1N2VHMmE3WlZADYVVvR2stdGhkQ2RMNlVMamVfR05TN0g2eEJkclhab3kzV3VsQUFfTklIWkNjY1ZA3b21fRWpTcAZDZD";
    public static final String USER_ID = "17841443870136613";



    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String KEY_INFORMATION_USER = "/media";
    public static final String KEY_INFORMATION_MEDIA = "?fields=id,media_url,username";
    public static final String URL_GET_RECENT_MEDIA_USER = USER_ID+KEY_INFORMATION_USER+KEY_INFORMATION_MEDIA+KEY_ACCESS_TOKEN+ACCESS_TOKEN;


    /* Datos usuario = florespruebasng*/
    public static final String ACCESS_TOKEN2 = "IGQVJVd083TW1zSHVKRmNRN2JmUGp0NUZAwU3BNVENCUzdac3pYRmRmdzkxZA0tBNzJkQm9ucVNjeTFTQnNNTkwwaUtZAaDktZA0hzbDBxNVlTM1FPNXFZAVEhGQ2VuVTlkTWNtcXg0blY3b3JFSDM5M2hvaQZDZD";
    //public static final String ACCESS_TOKEN2 = "IGQVJWUk5lejVBRHdaV01oV2VGUFFxMHpNOG9kN2p6R1RKT0FwdXBiU3EwNFVweXhjMzZAUMXh3VVUzWTJ3eFdzOHNlM0tZAdUVaQXZAFMmtXZAjJpdXJUeVBWRHFvcGdYZAkJZAZAnFTX2hFOFYzV0k4T0sxMAZDZD";
    public static final String USER_ID2 = "17841444334111797";

    public static final String URL_GET_RECENT_MEDIA_USER2 = USER_ID2+KEY_INFORMATION_USER+KEY_INFORMATION_MEDIA+KEY_ACCESS_TOKEN+ACCESS_TOKEN2;

    /* Datos usuario = paisajespruebasng*/
    public static final String ACCESS_TOKEN3 = "IGQVJYcFdiU1AtXy1EeHFQbmFySW96WU9DTjFzRTkwXzZAzUUlXdXpCQkJGQVFTbE95YjZAqZAU5BRXNkc3FqNWdVWjB5YXowdXZARdHJuLW5hZAzdYVjJjNVN0eTU2cU11V0ZAnaGhTT283bElIczRKYVNTMAZDZD";
    //public static final String ACCESS_TOKEN3 = "IGQVJXVnM0d2x3ZAmtNZAWFBdXB1c2pVQ0poT2pfX1FOclZA2ZAjZAiMWhYQTRBNzZATLUZA4SFl1dWRNbEJ6bFJhaE9KVXNxTDRzNFZAjOUJieXltWWtibGIwQXZANQnB5Ti0tc0xoQzlBdDdZATE0zUFd6emFUYgZDZD";
    public static final String USER_ID3 = "17841444854861356";

    public static final String URL_GET_RECENT_MEDIA_USER3 = USER_ID3+KEY_INFORMATION_USER+KEY_INFORMATION_MEDIA+KEY_ACCESS_TOKEN+ACCESS_TOKEN3;

    /*https://graph.instagram.com/17841443870136613/media
            ?fields=id,media_url,username&access_token=IGQVJAD...*/


    //Servidor Heroku
    public static final String ROOT_URL_HEROKU = "https://ancient-hollows-65198.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN = "token-device/";

    //Pide información del usuario al dar like
    public static final String KEY_DISTE_LIKE= "toque-animal/{id}/{user}";

    //Devuelve el gracias Like
    public static final String KEY_GRACIAS_LIKE= "graciasLike/{id_dispositivo}/{user}";
}
