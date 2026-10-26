package com.example.listamiguelangel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaVideojuegos;
    private ArrayList<Videojuego> videojuegos;
    private VideojuegoAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaVideojuegos = findViewById(R.id.listaVideojuegos);

        videojuegos = new ArrayList<>();

        listaVideojuegos.setLayoutManager(new LinearLayoutManager(this));

        adaptador = new VideojuegoAdapter(videojuegos, this);
        listaVideojuegos.setAdapter(adaptador);
    }
}
