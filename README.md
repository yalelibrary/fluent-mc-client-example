# Example MC Client using Apache Fluent

## Description
Example using Apache Fluent and HttpClient (with and without CredentialsProvider) to make a basic auth request to metadata cloud.

If you use HttpClient with CredentialsProvider, you need the `basic=1` parameter since it will not send
credentials until it's prompted by the server.

### Quickstart
```bash
mvn clean compile assembly:single
java -jar target/test-mc-client-1.0-jar-with-dependencies.jar goobi 'XXXX'
```
(where XXXX is the goobi password)