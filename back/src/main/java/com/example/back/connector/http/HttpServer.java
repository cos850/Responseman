package com.example.back.connector.http;

import com.example.back.connector.Server;
import com.sun.net.httpserver.HttpHandler;

public interface HttpServer<T extends HttpHandler> extends Server {

}
