package com.example.back.connector.management;

import com.example.back.connector.Server;
import com.example.back.connector.Status;
import com.example.back.connector.socket.SocketServer;
import com.example.back.web.server.ServerDto;
import com.example.back.web.server.ServerService;
import jakarta.annotation.PostConstruct;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 서버별 executor에서 실행
@Component
public class ServerManager {

    public Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private ServerService serverService;
    private Map<String, Server> servers  ;

    public ServerManager(){
        servers = new HashMap<>();
    }

    @PostConstruct
    public void initialized() {
        serverService.setServerManager(this);
        List<ServerDto> serverDtos = serverService.list();

        // ServerType에 따라 서버 생성
        for(ServerDto dto : serverDtos){
            createServer(dto);
        }
    }

    public void createServer(ServerDto dto) {
        Server server = switch (dto.getType()){
            case SOCKET -> new SocketServer().builder()
                    .name(dto.getName())
                    .port(dto.getPort())
                    .responseData(dto.getResponseData().getBytes())
                    .build();
            case HTTP -> null; // new HttpServer<>()
        } ;
        servers.put(dto.getName(), server);

        create(dto.getName());
        run(dto.getName());
    }

    private void create(String name) {
        Server server = servers.get(name);

        try {
            server.create();
            server.setStatus(Status.CREATED);
            servers.put(name, server);
            logger.info("서버 생성 완료");
        } catch (Throwable th){
            server.setStatus(Status.ERROR);
            logger.error("서버 생성 실패", th);
        }
    }

    public void run(String name){
        Server server = servers.get(name);

        try {
            server.start();
            server.setStatus(Status.RUNNING);
            servers.put(name, server);
            logger.info("서버 시작 완료");
        }catch (Throwable th){
            server.setStatus(Status.ERROR);
            logger.error("서버 실행 실패", th);
        }
    }

    public Status getStatus(String name){
        Server server = servers.get(name);
        if(server == null)
            return null;

        return server.getStatus();
    }

}
