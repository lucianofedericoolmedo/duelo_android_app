package org.uqbar.duelo.service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by luciano on 27/11/15.
 */


public class DueloServiceInstance {

    public static DueloService createDueloService() {

        String BASE_URL = "http://10.9.1.37:9000/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DueloService dueloService;
        dueloService = retrofit.create(DueloService.class);
        return  dueloService;

    }

}
