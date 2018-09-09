package com.example.rosmhyperezar.proyectofinal.datasource;

import com.example.rosmhyperezar.proyectofinal.modelo.Camping;
import com.example.rosmhyperezar.proyectofinal.modelo.CampingS;

import java.util.List;

public interface CampingRemoteDataSource {
    void consultarCamping(GetCampingCallback callback);
    void guardarCamping(PostCampingCallback callback, CampingS camping);

    public interface GetCampingCallback{
        void onCampingLoaded(List<Camping> camping);
        void onError();
    }

    public interface PostCampingCallback{
        void guardarCamping();
        void onError();
    }
}
