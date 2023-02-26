package com.fallInFall.tweetWriteOutBatch.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.*;

@Slf4j
public class RestTemplateBaseInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("えーぴーあい叩くヨォ : URI = {}, Header = {}, Body = {}", request.getURI(), request.getHeaders(), new String(body));

        ClientHttpResponse clientHttpResponse = new BufferingClientHttpResponseWrapper(execution.execute(request, body));

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientHttpResponse.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = bufferedReader.readLine();
        }

        if(clientHttpResponse.getStatusCode() == HttpStatus.OK) {
            log.info("えーぴーあいは正常に通ったヨォ : URI = {}, Header = {}, Body = {}",
                    clientHttpResponse.getStatusCode(),
                    clientHttpResponse.getStatusText(),
                    clientHttpResponse.getHeaders(),
                    stringBuilder.toString());
        } else {
            log.error("えーぴーあいは叩いたけどダメだったヨォ : URI = {}, Header = {}, Body = {}",
                    clientHttpResponse.getStatusCode(),
                    clientHttpResponse.getStatusText(),
                    clientHttpResponse.getHeaders(),
                    stringBuilder.toString());
        }

        return clientHttpResponse;
    }
    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;

        private byte[] body;

        BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
            this.response = response;
        }


        @Override
        public HttpStatus getStatusCode() throws IOException {
            return this.response.getStatusCode();
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.getBody());
            }
            return new ByteArrayInputStream(this.body);
        }

        @Override
        public void close() {
            this.response.close();
        }

    }
}
