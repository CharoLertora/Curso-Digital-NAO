package com.top10autores.integracion.controlador;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.top10autores.integracion.modelo.Autor;
import com.top10autores.integracion.vista.AutorVista;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutorControlador {

    private Autor modeloAutor;
    private AutorVista vistaAutor;
    private String id;
    private int resultadosTotales;
    private List<Autor> autoresGuardados;


    public AutorControlador() {
        super();
    }

    public AutorControlador(Autor modeloAutor, AutorVista vistaAutor) {
        this.modeloAutor = modeloAutor;
        this.vistaAutor = vistaAutor;
        this.resultadosTotales = 10;
        this.autoresGuardados = new ArrayList<>();
    }

    public void iniciarApp() {
        
        int respuestaUsuario = 1;
        do {

            this.id = vistaAutor.pedirIdAutor();
            try {
                consultarAutores(this.id);
            } catch (IOException | InterruptedException | URISyntaxException | JSONException e) {
                System.out.println("Lo sentimos, el id del autor no es válido, por favor inténtelo de nuevo.");
                throw new RuntimeException(e);
            }

            mostrarAutores();
            respuestaUsuario = vistaAutor.continuarBuscando();

        } while(respuestaUsuario == 1);

    }

    public void consultarAutores(String id) throws IOException, InterruptedException, URISyntaxException, JSONException {

        HttpClient client = HttpClient.newHttpClient();
        String apiKey = "4eb3107ae5bd993752fa635e91c2f708a5d74a83a3f29e7ec7856def0448dbbd";
        String baseUrl = "https://serpapi.com/search.json?engine=google_scholar_author";

        String apiUrl = baseUrl + "&author_id=" + this.id + "&num=" + this.resultadosTotales + "&api_key=" + apiKey;

        HttpRequest req = HttpRequest.newBuilder().uri(new URI(apiUrl)).build();
        HttpResponse<String> respuesta = client.send(req, HttpResponse.BodyHandlers.ofString());

        String jsonResp = respuesta.body();
        JSONObject jsonObject = new JSONObject(jsonResp);

        String nombre = jsonObject.getJSONObject("author").getString("name");
        String afiliacion = jsonObject.getJSONObject("author").getString("affiliations");

        Autor nuevoAutor = new Autor(id, nombre, afiliacion);

        autoresGuardados.add(nuevoAutor);

    }

    public void mostrarAutores() {
        for(int i = 0; i < autoresGuardados.size(); i++) {
            vistaAutor.mostrarAutor(this.autoresGuardados.get(i));
        }
    }
}
