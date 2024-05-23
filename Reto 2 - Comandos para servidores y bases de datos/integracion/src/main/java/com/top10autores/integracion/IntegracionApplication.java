package com.top10autores.integracion;

import com.top10autores.integracion.controlador.AutorControlador;
import com.top10autores.integracion.modelo.AutorModelo;
import com.top10autores.integracion.vista.AutorVista;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


/**
 * El método IntegracionApplication tiene al método main el cual se encarga de inicializar la app.
 *
 * @author Charo Lértora
 * @version 1.2
 * @see AutorVista
 * @see AutorModelo
 * @see AutorControlador
 * */
@SpringBootApplication
public class IntegracionApplication {

	@SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ClassNotFoundException {

		AutorVista vista = new AutorVista();
		AutorModelo modelo = new AutorModelo();
		AutorControlador controlador = new AutorControlador(modelo, vista);
		BaseDeDatos BD = new BaseDeDatos("jdbc:mysql://localhost:3306/busquedas", "root", "#ChLertora1711*");
        BD.crearBasedeDatos();
        /*
		controlador.iniciarApp();
         */
        
		System.out.println("Gracias por elegirnos! Hasta pronto :)");

	}

}
