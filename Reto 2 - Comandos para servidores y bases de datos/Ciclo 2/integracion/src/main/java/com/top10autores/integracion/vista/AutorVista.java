package com.top10autores.integracion.vista;

import com.top10autores.integracion.modelo.Autor;

import java.util.Scanner;

public class AutorVista {

    private Autor autor;

    private Scanner scan = new Scanner(System.in);

    public AutorVista() {
        super();
    }

    public AutorVista(Autor autor) {
        this.autor = autor;
    }

    public String pedirIdAutor() {

        System.out.println("Ingrese el ID del autor: ");
        String id = scan.next();

        return id;
    }

    public void mostrarAutor(Autor autor) {
        System.out.println(
                "AUTOR NRO: " + autor.getAutorId() +
                "\nNombre: " + autor.getNombre() +
                "\nAfiliación: " + autor.getAfiliacion() +
                "\n");
    }

    public int continuarBuscando() {
        System.out.println("Desea hacer otra búsqueda?: \n" +
                "1. Si\n" +
                "2. No");
        int respuesta = scan.nextInt();

        return respuesta;
    }

}
