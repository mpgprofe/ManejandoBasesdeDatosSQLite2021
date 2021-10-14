package com.example.manejandobasesdedatossqlite2021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button buttonGuardar, buttonMostrar, buttonBorrar, buttonActualizar;
    EditText editTextID, editTextModelo, editTextMarca, editTextPrecio;
    ListView listaVIEW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonActualizar = findViewById(R.id.buttonActualizar);
        buttonBorrar = findViewById(R.id.buttonBorrar);
        buttonMostrar = findViewById(R.id.buttonMostrar);
        buttonGuardar = findViewById(R.id.buttonGuardar);
        editTextID = findViewById(R.id.editTextId);
        editTextMarca = findViewById(R.id.editTextMarca);
        editTextModelo = findViewById(R.id.editTextModelo);
        editTextPrecio = findViewById(R.id.editTextPrecio);
        listaVIEW = findViewById(R.id.lista);
        ManejadorBD manejadorBD = new ManejadorBD(this);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean resultado = manejadorBD.insertar(editTextModelo.getText().toString(),
                        editTextMarca.getText().toString(), editTextPrecio.getText().toString());
                if (resultado) {
                    Toast.makeText(MainActivity.this, "Insertado teléfono correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error en la inserción", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean borrado = manejadorBD.borrar(editTextID.getText().toString());

                Toast.makeText(MainActivity.this, borrado ? "Borrado Correctamente" : "No se ha borrado nada", Toast.LENGTH_SHORT).show();

            }
        });

        buttonMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = manejadorBD.listar();
                ArrayAdapter<String> arrayAdapter;
                List<String> lista = new ArrayList<>();

                if (cursor != null && cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String fila = "";
                        fila += "ID: " + cursor.getString(0);
                        fila += " MARCA: " + cursor.getString(1);
                        fila += " MODELO: " + cursor.getString(2);
                        fila += " PRECIO: " + cursor.getString(3);
                        lista.add(fila);
                    }
                    arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, lista);
                    listaVIEW.setAdapter(arrayAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Nada que mostrar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean resultado = manejadorBD.actualizar(editTextID.getText().toString(), editTextModelo.getText().toString(),
                        editTextMarca.getText().toString(), editTextPrecio.getText().toString());
                Toast.makeText(MainActivity.this, resultado ? "Modificado Correctamente" : "No se ha modificado nada", Toast.LENGTH_SHORT).show();


            }
        });

    }
}