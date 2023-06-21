package com.example.back.management.server;

import com.example.back.base.HistDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ServerDto extends HistDto<Server> {

    private String name;
    private int port ;
    private String desc;

    public ServerDto(final Server server){
        super(server);

        this.name = server.getName();
        this.port = server.getPort();
        this.desc = server.getDesc();
    }

    public Server toEntity(){
        return Server.builder()
                .name(this.name)
                .port(this.port)
                .desc(this.desc).build();
    }
}
