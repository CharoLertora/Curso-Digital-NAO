package com.top10autores.integracion.controlador;

import com.top10autores.integracion.modelo.AutorModelo;
import com.top10autores.integracion.vista.AutorVista;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La clase AutorControaldor cumple con el controlador para los autores de la API.
 *
 * @author Charo Lértora
 * @version 1.2
 * */
public class AutorControlador {

    private AutorModelo modeloAutor;
    private AutorVista vistaAutor;
    private List<AutorModelo> autoresGuardados;


    /**
     * Constructor sin parámetros.
     */
    public AutorControlador() {
        super();
    }


    /**
     * Constructor con parámetros.
     * @param modeloAutor es el objeto para el modelo del autor que vamos a utilizar
     * @param vistaAutor es el objeto para la vista del autor que vamos a utilizar
     */
    public AutorControlador(AutorModelo modeloAutor, AutorVista vistaAutor) {
        this.modeloAutor = modeloAutor;
        this.vistaAutor = vistaAutor;
        this.autoresGuardados = new ArrayList<>();
    }


    /**
     * Método que se encarga de ejecutar los métodos y las funciones necesarias para que la app funcione.
     * En el caso de que no funcione, se le avisará al usuario por pantalla y se lanzará una excepción.
     * Se va a ejecutar hasta que el usuario no desee buscar más autores.
     */
    public void iniciarApp() {

        Scanner scan = new Scanner(System.in);
        int respuestaUsuario = 1;
        do {

            System.out.println("Ingrese el ámbito donde desea realizar la búsqueda: (Ej: ciencia, arte, política, etc)");
            String ambito = scan.next();;
            consultarAutores(ambito);

            mostrarAutores();
            respuestaUsuario = vistaAutor.continuarBuscando();

        } while(respuestaUsuario == 1);

    }

    /**
     * Este método es el encargado de realizar la conexión con la URL y de realizar la consulta GET a la API de Google Scholar Author.
     * @param tipoBusqueda recibe el id del autor que queremos consultar.
     * @throws IOException Excepción general de Java
     * @throws InterruptedException Se lanza cuando un hilo está esperando, inactivo o ocupado de otro modo y el hilo se interrumpe, ya sea antes o durante la actividad.
     * @throws URISyntaxException Se lanza cuando una cadena no se puede analizar como una referencia de URI.
     * @throws JSONException Se lanza cuando ocurre un error con Json.
     */
    private void consultarAutores(String tipoBusqueda) {

        HttpClient client = HttpClient.newHttpClient();
        String apiKey = "4eb3107ae5bd993752fa635e91c2f708a5d74a83a3f29e7ec7856def0448dbbd";
        String baseUrl = "https://serpapi.com/search.json?engine=google_scholar";

        String apiUrl = baseUrl + "&q=" + tipoBusqueda + "&hl=es-419" + "&api_key=" + apiKey;
        try {

            HttpRequest req = HttpRequest.newBuilder().uri(new URI(apiUrl)).build();
            HttpResponse<String> respuesta = client.send(req, HttpResponse.BodyHandlers.ofString());

            String jsonResp = respuesta.body();
            JSONObject jsonObject = new JSONObject(jsonResp);

            if (jsonObject.has("organic_results")) {

                JSONArray resultados = jsonObject.getJSONArray("organic_results");

                for (int i = 0; i < resultados.length(); i++) {

                    JSONObject resultado = resultados.getJSONObject(i);
                    JSONObject publicacionInfo = resultado.getJSONObject("publication_info");

                    if (publicacionInfo != null) {
                        String titulo = resultado.getString("title");
                        String link = resultado.getString("link");
                        String nombre = publicacionInfo.getString("summary");

                        AutorModelo nuevoAutor = new AutorModelo(titulo, nombre, link);
                        autoresGuardados.add(nuevoAutor);

                    } else {
                        System.out.println("No se encontró información de publicación para este resultado.");
                    }
                }
            } else {
                System.out.println("No se encontró información en este ámbito de búsqueda.");
            }
        } catch (IOException | InterruptedException | URISyntaxException | JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al procesar la respuesta JSON", e);
        }
    }


    /**
     * Método que se encarga de mostrar todos los autores guardados en la memoria Java.
     */
    private void mostrarAutores() {
        for(int i = 0; i < autoresGuardados.size(); i++) {
            vistaAutor.mostrarAutor(this.autoresGuardados.get(i));
        }
    }
}
