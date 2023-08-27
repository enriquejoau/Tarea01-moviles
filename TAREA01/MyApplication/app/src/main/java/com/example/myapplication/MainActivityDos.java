package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityDos extends AppCompatActivity {
    TextView tvMostrarAlumno,tvMostrarEscuela,tvMostrarCarrera,tvMostrarPension,tvMostrarGastosSeleccionados,tvMostrarCuotas,tvMostrarCostoCarrera,tvMostrarMontoAdicionales,tvMostrarTotalPagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dos);

        tvMostrarAlumno = findViewById(R.id.tvMostrarNombre);
        tvMostrarEscuela = findViewById(R.id.tvMostrarEscuela);
        tvMostrarCarrera = findViewById(R.id.tvMostrarCarrera);
        tvMostrarPension = findViewById(R.id.tvMostrarPension);
        tvMostrarMontoAdicionales=findViewById(R.id.tvmonto);
        tvMostrarGastosSeleccionados=findViewById(R.id.tvgastos);
        tvMostrarCuotas=findViewById(R.id.tvcuota);
        tvMostrarTotalPagar=findViewById(R.id.tvpagar);
        tvMostrarCostoCarrera = findViewById(R.id.tvMostrarCostoCarrera);


        Bundle datosRecibidos = getIntent().getExtras();
            String info = datosRecibidos.getString("alumno");
            String escu = datosRecibidos.getString("escuela");
            String carr = datosRecibidos.getString("carrera");
            String pens = datosRecibidos.getString("pension");
            String gast = datosRecibidos.getString("gastos");
            String cuot = datosRecibidos.getString("cuotas");
            String tota = datosRecibidos.getString("total");
            String mont = datosRecibidos.getString("monto");
            String costoCarrera = datosRecibidos.getString("costoCarrera");



        tvMostrarAlumno.setText(info);
        tvMostrarEscuela.setText(escu);
        tvMostrarCarrera.setText(carr);
        tvMostrarPension.setText(pens);
        tvMostrarMontoAdicionales.setText(mont);
        tvMostrarGastosSeleccionados.setText(gast);
        tvMostrarCuotas.setText(cuot);
        tvMostrarCostoCarrera.setText(costoCarrera);
        tvMostrarTotalPagar.setText(tota);

    }
}