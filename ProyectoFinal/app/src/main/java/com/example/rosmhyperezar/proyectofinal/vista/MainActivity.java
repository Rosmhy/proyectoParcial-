package com.example.rosmhyperezar.proyectofinal.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.rosmhyperezar.proyectofinal.R;
import com.example.rosmhyperezar.proyectofinal.datasource.CampingDataSource;
import com.example.rosmhyperezar.proyectofinal.dominio.CampingDominio;
import com.example.rosmhyperezar.proyectofinal.modelo.Camping;
import com.example.rosmhyperezar.proyectofinal.modelo.CampingS;
import com.example.rosmhyperezar.proyectofinal.vista.adapter.CampingAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CampingDominio.View {

    private CampingAdapter campingAdapter;
    private CampingDominio campingDominio;
    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (RecyclerView) findViewById(R.id.lista);
        campingDominio = new CampingDominio(this, CampingDataSource.getInstanece(this));
        consultarCamping();
        guardarCamping();
    }

    private void consultarCamping() {
        campingDominio.consultarCampings();
    }

    private void guardarCamping(){
        CampingS camping = new CampingS();
        camping.setNombre("JUAN");
        camping.setPhone(1234567);
        campingDominio.guardarCampings(camping);
    }

    @Override
    public void showCampings(List<Camping> campings) {
        campingAdapter = new CampingAdapter(getApplicationContext(), campings, this);
        lista.setAdapter(campingAdapter);
    }

    @Override
    public void showMensaje(int mensaje) {
        Log.d("Error", getString(mensaje));
    }
}
