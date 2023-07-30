package com.example.back.web.server;

import com.example.back.connector.Status;
import com.example.back.connector.management.ServerManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServerService {
    public Log logger = LogFactory.getLog(this.getClass());
    private final ServerRepository repository;
    private ServerManager serverManager;
    private final char CRATE = 'C', RUN = 'R', STOP = 'P';

    @Autowired
    public ServerService(ServerRepository repository){
        this.repository = repository;
    }

    public void setServerManager(ServerManager serverManager){
        this.serverManager = serverManager;
    }

    public ServerDto getServer(String name){
        return new ServerDto(repository.findById(name).get());
    }

    public ServerDto save(ServerDto dto){
        validation(dto, false);
        dto = new ServerDto(repository.save(dto.toEntity()));
        create(dto.getName());
        return dto;
    }

    public ServerDto update(ServerDto dto){
        validation(dto, true);
        Optional<Server> result = repository.findById(dto.getName());

        result.ifPresent(server->{
            server.setPort(dto.getPort());
            server.setDesc(dto.getDesc());
            server.setResponseData(dto.getResponseData());

            repository.save(server);
        });
        return new ServerDto(result.get());
    }

    public List<ServerDto> delete(final String name){
        try {
            repository.deleteById(name);
        } catch(Exception e){
            throw e;    // 데이터베이스 내부 로직을 캡슐화 하려면 새 Exception 생성 후 반환
        }

        return list();
    }

    public List<ServerDto> list(){
        return repository.findAll().stream().map(ServerDto::new).collect(Collectors.toList());
    }

    public void validation(ServerDto server, boolean idCheck){
        Assert.notNull(server, "서버 정보가 null 입니다");

        String message = "필수 요소를 입력하지 않았습니다. 필수 요소[{0}]";
        Assert.notNull(server.getName(), MessageFormat.format(message, "name"));
        Assert.notNull(server.getPort(), MessageFormat.format(message, "port"));
    }

    public boolean control(String name, char type){
        Assert.notNull(name, MessageFormat.format("필수 요소를 입력하지 않았습니다. 필수 요소[{0}]", "name"));

        switch(type){
            case CRATE -> create(name);
            case RUN -> start(name);
            case STOP -> stop(name);
            default -> throw new IllegalArgumentException(MessageFormat.format("유효하지 않은 제어 타입입니다. type=[{0}]", type));
        }
        return false;
    }

    private boolean create(String name) {
        ServerDto dto = getServer(name);
        serverManager.createServer(dto);

        return serverManager.getStatus(name) == Status.ERROR ? false : true;
    }

    private boolean start(String name) {
        return false;
    }
    private boolean stop(String name){
        return false;
    }
}
