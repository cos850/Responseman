package com.example.back.connector;


import java.io.IOException;

public interface Server {
    void create() ;
    void start() throws IOException;

    void stop() throws IOException;

    void send(byte[] data) throws IOException;

    byte[] recv() throws IOException;
}
