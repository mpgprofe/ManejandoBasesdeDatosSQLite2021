package com.example.manejandobasesdedatossqlite2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button buttonGuardar, buttonMostrar,buttonBorrar, buttonActualizar;
EditText editTextID, editTextModelo, editTextMarca, editTextPrecio;
ListView lista;

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
        lista = findViewById(R.id.lista);
        ManejadorBD manejadorBD = new ManejadorBD(this);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean resultado = manejadorBD.insertar(editTextModelo.getText().toString(),
                        editTextMarca.getText().toString(),editTextPrecio.getText().toString());
                if (resultado){
                    Toast.makeText(MainActivity.this, "Insertado teléfono correctamente", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error en la inserción", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}