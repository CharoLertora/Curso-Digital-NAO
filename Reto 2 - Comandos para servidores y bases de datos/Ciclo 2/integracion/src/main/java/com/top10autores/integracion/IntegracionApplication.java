package com.top10autores.integracion;

import com.top10autores.integracion.controlador.AutorControlador;
import com.top10autores.integracion.modelo.Autor;
import com.top10autores.integracion.vista.AutorVista;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class IntegracionApplication {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		AutorVista vista = new AutorVista();
		Autor modelo = new Autor();
		AutorControlador controlador = new AutorControlador(modelo, vista);

		controlador.iniciarApp();

		System.out.println("Gracias por elegirnos! Hasta pronto :)");

	}

}
