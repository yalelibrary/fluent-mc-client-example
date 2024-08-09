package edu.yale.library;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Test Metadata Cloud using Fluent
 */
public class App {

    public static void main( String[] args ) throws IOException {
        useHttpClientBasicParam(args);
        useHttpClient(args);
        useFluent(args);
    }

    public static void useFluent( String[] args ) throws IOException {
        String auth = Base64.getEncoder().encodeToString((args[0].trim() + ":" + args[1].trim()).getBytes(StandardCharsets.UTF_8));
        Request request = Request.Get("https://metadata-api.library.yale.edu/metadatacloud/api/search?barcode=39002091330184");
        addHeaders(request, auth);
        System.out.println(request.execute().returnContent().asString());
    }

    public static void useHttpClient(String[] args) throws IOException {
        CloseableHttpClient client;
        client = HttpClients.createDefault();
//      httpclient will not provide credentials preemptively, so just add the header:
//        CredentialsProvider credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
//                new UsernamePasswordCredentials(args[0], args[1]));
//        client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet method = new HttpGet("https://metadata-api.library.yale.edu/metadatacloud/api/search?barcode=39002091330184");
        String auth = Base64.getEncoder().encodeToString((args[0].trim() + ":" + args[1].trim()).getBytes(StandardCharsets.UTF_8));
        method.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth);
        byte[] response = client.execute(method, byteArrayResponseHandler);
        System.out.println(new String(response));
    }

    public static void useHttpClientBasicParam(String[] args) throws IOException {
        CloseableHttpClient client;
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(args[0], args[1]));
        client = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet method = new HttpGet("https://metadata-api.library.yale.edu/metadatacloud/api/search?barcode=39002091330184&basic=1");
        byte[] response = client.execute(method, byteArrayResponseHandler);
        System.out.println(new String(response));
    }



    public static ResponseHandler<byte[]> byteArrayResponseHandler = new ResponseHandler<byte[]>() {
        @Override
        public byte[] handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toByteArray(entity);
            } else {
                return null;
            }
        }
   };



    private static Request addHeaders(Request request, String auth) {
        return request.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + auth).addHeader("Accept", "application/json");
    }
}
