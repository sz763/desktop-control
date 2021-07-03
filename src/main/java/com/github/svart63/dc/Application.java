package com.github.svart63.dc;

import com.github.svart63.dc.handlers.KeyboardEvenHandler;
import com.github.svart63.dc.handlers.MouseClickEventHandler;
import com.github.svart63.dc.handlers.MouseMoveEventHandler;
import com.github.svart63.dc.handlers.WebResourceHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Application {
    @SneakyThrows
    public static void main(String[] args) {
        log.info("Start web server");
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress("0.0.0.0", 8080), 0/*use system default*/);
        addContext(server, "/mouse/move", new MouseMoveEventHandler());
        addContext(server, "/mouse/click", new MouseClickEventHandler());
        addContext(server, "/keyboard", new KeyboardEvenHandler());
        addContext(server, "/", new WebResourceHandler("./web"));
        server.start();

    }

    private static void addContext(HttpServer server, String path, HttpHandler handler) {
        log.info("Register handler by path: {}", path);
        server.createContext(path, handler);
    }
}
