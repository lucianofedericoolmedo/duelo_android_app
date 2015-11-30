package org.uqbar.duelo.service;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by luciano on 27/11/15.
 */


public class DueloServiceInstance {

    public static DueloService createDueloService() {

        // Esta URL apunta al proyecto con ORM de Grails
        // 		val API_URL = "http://10.0.2.2:8080/videoclub-ui-orm-grails"
        // Esta URL apunta a la solución en Grails con Homes hechos en Xtend
        //String SERVER_IP = "10.0.2.2";

        // IMPORTANTE
        // Por un bug de retrofit 2.0, la BASE_URL debe tener una / al final
        // y la dirección del service debe comenzar sin /, como un path relativo
        String BASE_URL = "http://192.168.1.69:9000/";
        //"http://10.12.6.10:9000/"
        //String BASE_URL = "http://10.12.6.10:9000/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DueloService dueloService;
        dueloService = retrofit.create(DueloService.class);
        return  dueloService;

    }

}
