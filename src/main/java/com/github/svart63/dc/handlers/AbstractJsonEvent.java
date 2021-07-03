package com.github.svart63.dc.handlers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;

@Slf4j
public abstract class AbstractJsonEvent<T> implements HttpHandler {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SneakyThrows
    @Override
    public void handle(HttpExchange exchange) {
        try {
            log.debug("Received request from: {}", exchange.getRemoteAddress());
            T event = MAPPER.readValue(exchange.getRequestBody(), getTypeRef());
            handle(event);
            doResponse(exchange);
        } catch (Exception e) {
            log.error("Failed handling request", e);
            try (PrintStream printer = new PrintStream(exchange.getResponseBody())) {
                exchange.sendResponseHeaders(500, 0L);
                e.printStackTrace(printer);
            }
        } finally {
            exchange.close();
        }
    }

    protected abstract TypeReference<T> getTypeRef();

    @SneakyThrows
    protected void doResponse(HttpExchange exchange) {
        exchange.sendResponseHeaders(200, 0L);
    }


    public abstract void handle(T event);
}
