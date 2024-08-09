package edu.yale.library;

import org.apache.http.HttpHeaders;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Test Metadata Cloud using Fluent
 */
public class App {

    public static void main( String[] args ) throws IOException {
        String auth = Base64.getEncoder().encodeToString((args[0].trim() + ":" + args[1].trim()).getBytes(StandardCharsets.UTF_8));
        Request request = Request.Get("https://metadata-api.library.yale.edu/metadatacloud/api/search?barcode=39002091330184");
        addHeaders(request, auth);
        System.out.println(request.execute().returnContent().asString());
    }

    private static Request addHeaders(Request request, String auth) {
        return request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth).addHeader("Accept", "application/json");
    }
}
