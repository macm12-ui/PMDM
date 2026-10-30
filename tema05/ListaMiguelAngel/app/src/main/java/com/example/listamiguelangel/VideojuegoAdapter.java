package com.example.listamiguelangel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideojuegoAdapter extends RecyclerView.Adapter<VideojuegoAdapter.VideojuegoViewHolder> {

    private ArrayList<Videojuego> listaVideojuegos;
    private MainActivity contexto;

    public VideojuegoAdapter(ArrayList<Videojuego> listaVideojuegos, MainActivity contexto) {
        this.listaVideojuegos = listaVideojuegos;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public VideojuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_videojuego, parent, false);
        return new VideojuegoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull VideojuegoViewHolder holder, int position) {
        Videojuego videojuego = listaVideojuegos.get(position);

        holder.txtNombre.setText(videojuego.getNombre());
        holder.txtPlataforma.setText(videojuego.getPlataforma());
        holder.imgVideojuego.setImageResource(videojuego.getImagen());

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(contexto, videojuego.getNombre(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return listaVideojuegos.size();
    }

    public static class VideojuegoViewHolder extends RecyclerView.ViewHolder {

        ImageView imgVideojuego;
        TextView txtNombre;
        TextView txtPlataforma;

        public VideojuegoViewHolder(@NonNull View itemView) {
            super(itemView);

            imgVideojuego = itemView.findViewById(R.id.imgVideojuego);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtPlataforma = itemView.findViewById(R.id.txtPlataforma);
        }
    }
}
