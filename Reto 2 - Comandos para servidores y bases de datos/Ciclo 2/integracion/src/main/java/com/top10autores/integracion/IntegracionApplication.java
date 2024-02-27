package com.top10autores.integracion;

import com.top10autores.integracion.controlador.AutorControlador;
import com.top10autores.integracion.modelo.AutorModelo;
import com.top10autores.integracion.vista.AutorVista;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;


/**
 * @author Charo Lértora
 * @version 1.0
 * @see AutorVista()
 * @see AutorModelo ()
 * @see AutorControlador()
 *
 * El método IntegracionApplication tiene al método main el cual se encarga de inicializar la app.
 * */
@SpringBootApplication
public class IntegracionApplication {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		AutorVista vista = new AutorVista();
		AutorModelo modelo = new AutorModelo();
		AutorControlador controlador = new AutorControlador(modelo, vista);

		controlador.iniciarApp();

		System.out.println("Gracias por elegirnos! Hasta pronto :)");

	}

}
