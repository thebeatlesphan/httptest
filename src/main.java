import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.tools.javac.Main;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class main {

    //private static HttpURLConnection connection;

    public static void main(String[] args) throws IOException, InterruptedException {
         //Method 1: java.net.HttpURLConnection >> synchronous
//        BufferedReader reader;
//        String line;
//        StringBuffer responseContent = new StringBuffer();
//        try {
//            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
//            connection = (HttpURLConnection) url.openConnection();
//
//            //request setup
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000); //5ms == 5sec
//            connection.setReadTimeout(5000);
//
//            int status = connection.getResponseCode();
//            //System.out.println(status);
//
//            if (status > 299) {
//                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                while ((line = reader.readLine()) != null) {
//                    responseContent.append(line);
//                }
//                reader.close();
//            } else {
//                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                while ((line = reader.readLine()) != null) {
//                    responseContent.append(line);
//                }
//                //System.out.println(responseContent.toString());
//                parse(responseContent.toString());
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            connection.disconnect();
//        }

        // Method 2: java.net.http.HttpClient   >> added in Java 11 - asynchronously
        HttpClient client = HttpClient.newHttpClient(); // equivalent to HttpClient.newBuilder().build();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
//                .build();
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenApply(main::parse)
//                .join();

        //POST test

        var values = new HashMap<String, Object>() {{
            put("userId", 420);
            put("id", 420);
            put("title", "PANDINGO");
            put("body", "this is pandingo!");
        }};

        //use ObjectMapper to convert HashMap to JSON
        var objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(values);
        //POST request
        HttpRequest POSTrequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> POSTresponse = client.send(POSTrequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(POSTresponse.body());

        //PUT test
//        HttpRequest PUTrequest = HttpRequest.newBuilder()
//                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
//                .PUT(HttpRequest.BodyPublishers)

        //DELETE test
        HttpRequest DELETErequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
                .DELETE()
                .build();
        HttpResponse<String> DELETEresponse = client.send(DELETErequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(DELETEresponse.statusCode() + "gucci?");
        System.out.println(DELETEresponse.body());
    }

    public static String parse(String responseBody) {
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id + " " + title + " " + userId);
        }
        return null;

    }
}