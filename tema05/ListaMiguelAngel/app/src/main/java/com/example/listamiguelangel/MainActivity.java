package com.example.listamiguelangel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
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

        videojuegos.add(new Videojuego("Minecraft", "PC",
                R.drawable.minecraft));
        videojuegos.add(new Videojuego("The Legend of Zelda", "Nintendo Switch",
                R.drawable.saga_the_legend_of_zelda));
        videojuegos.add(new Videojuego("Super Mario Kart 8 Deluxe", "Nintendo Switch",
                R.drawable.mariokart));
        videojuegos.add(new Videojuego("Fortnite", "PC",
                R.drawable.fortnite));
        videojuegos.add(new Videojuego("GTA V", "PC",
                R.drawable.gtav));
        videojuegos.add(new Videojuego("FIFA", "PlayStation",
                R.drawable.fifa));
        videojuegos.add(new Videojuego("Pokémon", "Nintendo Switch",
                R.drawable.pokemon));
        videojuegos.add(new Videojuego("Dead by Daylight", "PC",
                R.drawable.dbd));

        listaVideojuegos.setLayoutManager(
                new LinearLayoutManager(this));

        // Añadir separadores entre los videojuegos
        listaVideojuegos.addItemDecoration(
                new DividerItemDecoration(
                        this, LinearLayoutManager.VERTICAL));

        adaptador = new VideojuegoAdapter(videojuegos, this);
        listaVideojuegos.setAdapter(adaptador);
    }
}
