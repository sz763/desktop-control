package com.github.svart63.dc.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class WebResourceHandler implements HttpHandler {
    private final String webResourcePath;

    public WebResourceHandler(String webResourcePath) {
        this.webResourcePath = webResourcePath;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String uriPath = resolveUriPath(exchange);
            File file = buildFilePath(uriPath);
            if (file.exists()) {
                addContentType(exchange, file);
                exchange.sendResponseHeaders(200, file.length());
                fillResponseBody(exchange, file);
            } else {
                exchange.sendResponseHeaders(404, 0L);
            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(500, 0L);
        } finally {
            exchange.close();
        }
    }

    private void addContentType(HttpExchange exchange, File file) {
        String value = "plain/text";
        if (endWith(file, "js")) {
            value = "application/javascript";
        } else if (endWith(file, "html")) {
            value = "text/html";
        } else if (endWith(file, "css")) {
            value = "text/css";
        }else {
            value = "x-unknown-content-type";
        }

        exchange.getResponseHeaders().add("Content-Type", value);
    }

    private boolean endWith(File file, String expected) {
        String name = file.getName();
        return expected.equals(name.substring(name.length() - expected.length()));
    }

    private File buildFilePath(String uriPath) {
        Path resource = Paths.get(webResourcePath, uriPath);
        return resource.toFile();
    }

    private String resolveUriPath(HttpExchange exchange) {
        String uriPath = exchange.getRequestURI().getPath();
        if ("/".equals(uriPath)) {
            uriPath = "index.html";
        }
        return uriPath;
    }

    @SneakyThrows
    private void fillResponseBody(HttpExchange exchange, File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            OutputStream outputStream = exchange.getResponseBody();
            byte[] buffer = new byte[1024];
            int readCount;
            while ((readCount = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, readCount);
            }
        }
    }
}
