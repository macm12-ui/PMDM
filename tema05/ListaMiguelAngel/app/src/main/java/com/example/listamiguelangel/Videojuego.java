package com.example.listamiguelangel;

public class Videojuego {

    private String nombre;
    private String plataforma;
    private int imagen;

    public Videojuego(String nombre, String plataforma, int imagen) {
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public int getImagen() {
        return imagen;
    }
}
