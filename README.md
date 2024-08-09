# Example MC Client using Apache Fluent

## Description
Simple example using ~~Apache Fluent~~ HttpClient to make a basic auth request to metadata cloud.

### Quickstart
```bash
mvn clean compile assembly:single
java -jar target/test-mc-client-1.0-jar-with-dependencies.jar goobi 'XXXX'
```
(where XXXX is the goobi password)