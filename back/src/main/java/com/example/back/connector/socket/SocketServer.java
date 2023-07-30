package com.example.back.connector.socket;

import com.example.back.connector.Server;
import com.example.back.connector.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

// TCP 프로토콜
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocketServer implements Server {

    private final Log logger = LogFactory.getLog(this.getClass());
    private ServerSocket server;
    private Socket client;
    private String name ;
    private String address;
    private int port;
    private Status status;
    private byte[] requestData;
    private byte[] responseData;

    private OutputStream out;
    private InputStream in;

    public SocketServer(String address, int port){
        this.address = address;
        this.port = port;
    }

    public SocketServer(int port){
        this.port = port;
    }

    public byte[] getRequestData() {
        return requestData;
    }

    public void setResponseData(byte[] responseData) {
        this.responseData = responseData;
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
        try {
            bind();
            client = accept();
            in = client.getInputStream();
            out = client.getOutputStream();

            recv();
        }catch (Throwable th){
            status = Status.ERROR;
            logger.error("서버 실행 실패", th);
        }
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
    public void send() throws IOException {
        out.write(responseData);
        out.close();
    }

    @Override
    public byte[] recv() throws IOException {
        requestData = in.readAllBytes();
        in.close();
        return requestData;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }
}
