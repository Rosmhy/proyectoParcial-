package com.example.rosmhyperezar.proyectofinal.dominio;

import com.example.rosmhyperezar.proyectofinal.R;
import com.example.rosmhyperezar.proyectofinal.datasource.CampingRemoteDataSource;
import com.example.rosmhyperezar.proyectofinal.modelo.Camping;
import com.example.rosmhyperezar.proyectofinal.modelo.CampingS;

import java.util.List;


public class CampingDominio {
    private View view;
    private CampingRemoteDataSource campingRemoteDataSource;

    public CampingDominio(View view, CampingRemoteDataSource campingRemoteDataSource) {
        this.view = view;
        this.campingRemoteDataSource = campingRemoteDataSource;
    }

    public void consultarCampings() {
        campingRemoteDataSource.consultarCamping(new CampingRemoteDataSource.GetCampingCallback() {
            @Override
            public void onCampingLoaded(List<Camping> camping) {
                view.showCampings(camping);
            }

            @Override
            public void onError() {
                view.showMensaje(R.string.error_generico);
            }
        });
    }

    public void guardarCampings(CampingS camping) {
        campingRemoteDataSource.guardarCamping(new CampingRemoteDataSource.PostCampingCallback() {
            @Override
            public void guardarCamping() {
                view.showMensaje(R.string.mensaje_generico_guardar);
            }

            @Override
            public void onError() {
                view.showMensaje(R.string.error_generico);
            }
        }, camping);
    }

    public interface View {
        void showCampings(List<Camping> campings);

        void showMensaje(int mensaje);
    }
}
