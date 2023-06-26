package com.example.back.connector.socket;

import com.example.back.connector.Server;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;

// TCP 프로토콜
@NoArgsConstructor
public class SocketServer implements Server {

    private ServerSocket server;
    private Socket client;

    private String address;

    private int port;

    private byte[] data;

    private String status;
    private OutputStream out;

    private InputStream in;

    public SocketServer(String address, int port){
        this.address = address;
        this.port = port;
    }

    public SocketServer(int port){
        this.port = port;
    }

    @Override
    public void create() {
        try {
            server = new ServerSocket();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() throws IOException {
        if(address == null)
            server.bind(new InetSocketAddress(port));
        else
            server.bind(new InetSocketAddress(address, port));
    }

    // TODO : 예외처리 필요
    @Override
    public void start() throws IOException {
        bind();
        client = accept();
        in = client.getInputStream();
        out = client.getOutputStream();
    }

    public Socket accept() throws IOException {
        return server.accept();
    }

    @Override
    public void stop() throws IOException {
        if(!client.isInputShutdown())
            client.shutdownInput();
        if(!client.isOutputShutdown())
            client.shutdownOutput();
        if(!client.isClosed())
            client.close();
        if(!server.isClosed())
            server.close();
    }

    @Override
    public void send(byte[] data) throws IOException {
        out.write(data);
        out.close();
    }

    @Override
    public byte[] recv() throws IOException {
        byte[] data = in.readAllBytes();
        in.close();
        return data;
    }
}
