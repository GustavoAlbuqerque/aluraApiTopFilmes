package br.com.gustavo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
	
	public static void main(String[] args) throws Exception {

		//		Fazer uma conexão HTTP E buscar o top 250 filmes
		
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endereco = URI.create(url);
		HttpClient cliente = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		
		HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());

		String body = response.body();
		
		
		//		Extrair os dados que interessa (Titulo / Poster / classificação)
		
		JsonParser parser = new JsonParser();	
		List<Map <String , String>> listaFilmes = parser.parse(body);
		
		// Exibir os dados 	
		
		for (Map<String, String> filme : listaFilmes) {
			System.out.println(filme.get("title"));
			System.out.println(filme.get("image"));
			System.out.println(filme.get("imDbRating"));		
			System.out.println();	
		}
		
	}
}
