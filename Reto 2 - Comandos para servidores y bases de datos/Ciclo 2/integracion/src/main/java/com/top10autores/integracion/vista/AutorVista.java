package com.top10autores.integracion.vista;

import com.top10autores.integracion.modelo.AutorModelo;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * La clase AutorVista cumple con la vista para los autores de la API.
 *
 * @author Charo Lértora
 * @version 1.0
 * */
public class AutorVista {

    private AutorModelo autorModelo;

    private Scanner scan = new Scanner(System.in);

    /**
     * Constructor sin parámetros.
     */
    public AutorVista() {
        super();
    }

    /**
     * Constructor con parámetros
     * @param autorModelo Objeto necesario para el modelo del autor que utilizaremos.
     */
    public AutorVista(AutorModelo autorModelo) {
        this.autorModelo = autorModelo;
    }

    /**
     * Pide al usuario que ingrese por pantalla el id del autor que queremos buscar.
     * @return id(String)
     */
    public String pedirIdAutor() {

        System.out.println("Ingrese el ID del autorModelo: ");
        String id = scan.next();

        return id;
    }

    /**
     * Método que sirve para mostrar los datos de un autor.
     * @param autorModelo Objeto que contendrá la información que queremos mostrar, no puede ser nulo.
     */
    public void mostrarAutor(@NotNull AutorModelo autorModelo) {
        System.out.println(
                "AUTOR NRO: " + autorModelo.getAutorId() +
                "\nNombre: " + autorModelo.getNombre() +
                "\nAfiliación: " + autorModelo.getAfiliacion() +
                "\n");
    }

    /**
     * Función que retornara un valor para saber si el usuario desea seguir buscando autores o no.
     * @return respuesta(int)
     */
    public int continuarBuscando() {
        System.out.println("Desea hacer otra búsqueda?: \n" +
                "1. Si\n" +
                "2. No");
        int respuesta = scan.nextInt();

        return respuesta;
    }

}
