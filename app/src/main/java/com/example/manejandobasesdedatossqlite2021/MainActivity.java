package com.example.manejandobasesdedatossqlite2021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    ArrayList<Movil> moviles;


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
                moviles = new ArrayList<>();
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
                        moviles.add(new Movil(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
                    }
                    arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, lista);
                    listaVIEW.setAdapter(arrayAdapter);
                    listaVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            editTextID.setText(moviles.get(i).getId());
                            editTextMarca.setText(moviles.get(i).getMarca());
                        }
                    });
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
    class Movil{
        String id,modelo, precio,marca;

        public Movil(String id, String marca, String modelo, String precio) {
            this.id = id;
            this.modelo = modelo;
            this.precio = precio;
            this.marca = marca;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public String getPrecio() {
            return precio;
        }

        public void setPrecio(String precio) {
            this.precio = precio;
        }

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }
    }
}