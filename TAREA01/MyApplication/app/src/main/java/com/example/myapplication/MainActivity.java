package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText etalumno,etaescuela,etacarrera;
    Button btnImprime, btnCalcular;
    RadioButton rbtn4, rbtn5, rbtn6;
    CheckBox chkcBiblioteca, chkcMedio;

    TextView tvGastosAdicionales,tvCostoCarrera, tvTotalPagar,tvPension;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etalumno=findViewById(R.id.edtAlumno);
        etaescuela=findViewById(R.id.spnEscuela);
        etacarrera=findViewById(R.id.spnCarrera);
        btnImprime=findViewById(R.id.btnImprimir);
        btnCalcular = findViewById(R.id.btnCalcular);
        chkcBiblioteca = findViewById(R.id.chkcBiblioteca);
        chkcMedio = findViewById(R.id.chkcMedio);
        tvGastosAdicionales = findViewById(R.id.tvGastosAdicionales);
        tvCostoCarrera = findViewById(R.id.tvCostoCarrera);
        tvTotalPagar = findViewById(R.id.tvTotalPagar);
        tvPension=findViewById(R.id.tvPension);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        rbtn6 = findViewById(R.id.rbtn6);

        tvGastosAdicionales.setText("$0");
        tvCostoCarrera.setText("$0");
        tvTotalPagar.setText("$0");
        tvPension.setText("$0");


        chkcBiblioteca.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chkcMedio.setChecked(false); // Desmarca la otra opción si estaba marcada
                }
            }
        });

        chkcMedio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chkcBiblioteca.setChecked(false); // Desmarca la otra opción si estaba marcada
                }
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularTotalPagar();

            }
        });

        btnImprime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Bundle enviarDatos=new Bundle();
                enviarDatos.putString("alumno", etalumno.getText().toString());
                enviarDatos.putString("escuela", etaescuela.getText().toString());
                enviarDatos.putString("carrera", etacarrera.getText().toString());
                enviarDatos.putString("monto",tvGastosAdicionales.getText().toString());
                enviarDatos.putString("pension",tvPension.getText().toString());
                enviarDatos.putString("total",tvTotalPagar.getText().toString());

                ////////////////////////////////////
                String costoCarrera = tvCostoCarrera.getText().toString();
                enviarDatos.putString("costoCarrera", costoCarrera);
                //////////////////////////////////

                String gastosSeleccionados = "";
                if (chkcBiblioteca.isChecked()) {
                    gastosSeleccionados += "Biblioteca, ";
                }
                if (chkcMedio.isChecked()) {
                    gastosSeleccionados += "Medio de transporte, ";
                }

                // Eliminar la última coma y espacio si hay gastos seleccionados
                if (!gastosSeleccionados.isEmpty()) {
                    gastosSeleccionados = gastosSeleccionados.substring(0, gastosSeleccionados.length() - 2);
                }

                enviarDatos.putString("gastos", gastosSeleccionados);


                ///////////////para las cuotas///////////////////
                if (rbtn4.isChecked()) {
                    enviarDatos.putString("cuotas", "4 cuotas");
                } else if (rbtn5.isChecked()) {
                    enviarDatos.putString("cuotas", "5 cuotas");
                } else if (rbtn6.isChecked()) {
                    enviarDatos.putString("cuotas", "6 cuotas");
                }
                ////////////////////////////////////////////////



                Intent intent = new Intent(MainActivity.this, MainActivityDos.class);
                intent.putExtras(enviarDatos);
                startActivity(intent);
            }

        });
    }


    private void calcularTotalPagar() {
        /////////////////////gastos Adicionales/////////////
        int gastosAdicionales = 0;
        if (chkcBiblioteca.isChecked()) {
            gastosAdicionales += 25;
        }
        if (chkcMedio.isChecked()) {
            gastosAdicionales += 22;
        }
        //////////////////////////////////////////////////////////

        //////////////costo carrera/////////////////
        int costoCarrera = 0;
        String carrera = etacarrera.getText().toString().toLowerCase();
        if (carrera.equals("sistema") || carrera.equals("medicina")) {
            costoCarrera = 3000;
        } else if (carrera.equals("civil")) {
            costoCarrera = 1000;
        }

        ////////////////////////////////////////////
        double porcentajeInteres = 0.12; // 12% de interés

        ////////cuotas//////////////////////////
        int numeroCuotas = 0;
        if (rbtn4.isChecked()) {
            numeroCuotas = 4;
        } else if (rbtn5.isChecked()) {
            numeroCuotas = 5;
        } else if (rbtn6.isChecked()) {
            numeroCuotas = 6;
        }
        //////////////////////////////////
        double pension = (costoCarrera + (costoCarrera * porcentajeInteres)) / numeroCuotas;
        int totalPagar = gastosAdicionales + costoCarrera + (int) pension;
        tvTotalPagar.setText("$" + totalPagar);
        tvCostoCarrera.setText("$"+costoCarrera);
        tvGastosAdicionales.setText("$" + gastosAdicionales);
        tvPension.setText("$"+pension);


    }
}