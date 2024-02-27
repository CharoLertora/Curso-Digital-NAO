package com.top10autores.integracion.modelo;


/**
 * La clase AutorModelo cumple con el modelo para los autores de la API.
 *
 * @author Charo Lértora
 * @version 1.1
 * */
public class AutorModelo {
    private String autorId;
    private String nombre;
    private String afiliacion;


    /**
     * Constructor sin parámetros.
     * */
    public AutorModelo() {
        super();
    }

    /**
     * Constructor con parámetros.
     *
     * @param autorId representa el id del autor
     * @param nombre representa el nombre del autor
     * @param afiliacion representa las afiliaciones que tiene el autor
     * */
    public AutorModelo(String autorId, String nombre, String afiliacion) {
        this.autorId = autorId;
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    /**
     * Función que devuelve el id del autor.
     * @return autorID(String)
     * */
    public String getAutorId() {
        return autorId;
    }

    /**
     * Método que sirve para modificar el id del autor
     * @param autorId representa el nuevo id del autor
     */
    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    /**
     * Función que devuelve el nombre del autor
     * @return nombre(String)
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que sirve para modificar el nombre del autor
     * @param nombre representa el nuevo nombre del autor
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Función que devuelve las afiliaciones del autor
     * @return afiliacion(String)
     */
    public String getAfiliacion() {
        return afiliacion;
    }

    /**
     * Método que sirve para modificar las afiliaciones del autor
     * @param afiliacion representa las nuevas afiliaciones del autor
     */
    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

}
