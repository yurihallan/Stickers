import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e Buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_kyty6or8";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (Map<String, String> filme : listDeFilmes) {

            String urlImagem = filme.get("image");
            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(filme.get("title"));
            System.out.println();
        }
    }
}
