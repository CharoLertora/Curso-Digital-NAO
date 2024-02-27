package com.top10autores.integracion.modelo;

public class Autor {
    private String autorId;
    private String nombre;
    private String afiliacion;


    public Autor() {
        super();
    }

    public Autor(String autorId, String nombre, String afiliacion) {
        this.autorId = autorId;
        this.nombre = nombre;
        this.afiliacion = afiliacion;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

}
