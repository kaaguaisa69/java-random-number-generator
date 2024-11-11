import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Random;

public class RandomNumberGenerator {
    public static void main(String[] args) throws IOException {
        int port = 8080;  // Puerto en el que escuchará el servidor
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Servir la página HTML directamente desde Java
        server.createContext("/", new HtmlHandler());
        
        // Generar números aleatorios en /random
        server.createContext("/random", new RandomHandler());

        server.setExecutor(null);  // Crear un hilo predeterminado
        System.out.println("Server started on port " + port);
        server.start();
    }

    // Genera y sirve el HTML directamente desde Java
    static class HtmlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String htmlResponse = "<!DOCTYPE html>"
                    + "<html lang='en'>"
                    + "<head>"
                    + "<meta charset='UTF-8'>"
                    + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    + "<title>Random Number Generator</title>"
                    + "</head>"
                    + "<body>"
                    + "<h1>Random Number Generator</h1>"
                    + "<form id='randomNumberForm'>"
                    + "<label for='min'>Minimum Value:</label>"
                    + "<input type='number' id='min' name='min' required>"
                    + "<br><br>"
                    + "<label for='max'>Maximum Value:</label>"
                    + "<input type='number' id='max' name='max' required>"
                    + "<br><br>"
                    + "<button type='button' onclick='generateRandomNumber()'>Generate</button>"
                    + "</form>"
                    + "<h2 id='result'></h2>"
                    + "<script>"
                    + "async function generateRandomNumber() {"
                    + "    const min = document.getElementById('min').value;"
                    + "    const max = document.getElementById('max').value;"
                    + "    const response = await fetch(`/random?min=${min}&max=${max}`);"
                    + "    const resultText = await response.text();"
                    + "    document.getElementById('result').innerText = resultText;"
                    + "}"
                    + "</script>"
                    + "</body>"
                    + "</html>";

            exchange.sendResponseHeaders(200, htmlResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }

    static class RandomHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            int min = 1;
            int max = 100;

            if (query != null) {
                String[] params = query.split("&");
                for (String param : params) {
                    String[] pair = param.split("=");
                    if (pair[0].equals("min")) {
                        min = Integer.parseInt(pair[1]);
                    } else if (pair[0].equals("max")) {
                        max = Integer.parseInt(pair[1]);
                    }
                }
            }

            if (min > max) {
                String response = "Invalid range: min should be less than max.";
                exchange.sendResponseHeaders(400, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            // Generar número aleatorio
            Random random = new Random();
            int randomNumber = random.nextInt((max - min) + 1) + min;
            String response = "Generated random number: " + randomNumber;

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
