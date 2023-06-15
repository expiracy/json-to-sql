package network;

import json.JSONData;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class HttpManager {

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException, ParseException {
        HttpManager.getExpandedJsonResponse("https://pokeapi.co/api/v2/pokemon/ditto", 1);
    }

    public static HttpResponse<String> getResponse(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public static JSONData getExpandedJsonResponse(String uri, int depth) throws URISyntaxException, IOException, InterruptedException, ParseException {
        String expandedResponseString = HttpManager.expandStringResponse(HttpManager.getResponse(uri).body(), depth);
        JSONData jsonData = new JSONData(expandedResponseString);
        return jsonData;
    }

    public static String expandStringResponse(String responseString, int depth) throws IOException {
        String last4 = "    ";
        String innerUri = "";
        int startUriIndex = -1;
        Map<String, String> responses = new HashMap<>();

        int i = 0;

        while (i < responseString.length()) {
            char c = responseString.charAt(i);

            switch (c) {
                case ']', '}' -> startUriIndex = -1;
                case '"' -> {
                    if (startUriIndex != -1 && !responses.containsKey(innerUri)) {
                        try {
                            responses.put(innerUri, HttpManager.getResponse(innerUri).body());
                            startUriIndex = -1;
                        } catch (URISyntaxException | InterruptedException e) {
                            System.out.println(innerUri);
                        }
                    }
                }

                default -> {
                    last4 = last4.substring(1, 4) + c;

                    if (startUriIndex != -1) innerUri += c;

                    if (last4.equals("http")) {
                        startUriIndex = i; innerUri = last4;
                    }
                }
            }
            i++;
        }

        responseString = HttpManager.replaceUrisWithResponses(responseString, responses);

        return (depth > 1) ? HttpManager.expandStringResponse(responseString, depth - 1) : responseString;
    }

    private static String replaceUrisWithResponses(String responseString, Map<String, String> responses) {
        String newResponseString = responseString;
        int offset = -1;

        for (Map.Entry<String, String> uriAndResponse : responses.entrySet()) {
            String uri = uriAndResponse.getKey();
            String response = uriAndResponse.getValue();

            int index = responseString.indexOf(uri) + offset;

            newResponseString =
                    newResponseString.substring(0, index)
                    + response
                    + newResponseString.substring(index + uri.length());

            offset += positions.get(uri);
        }

        return newResponseString;
    }

    public static boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
    }
}

