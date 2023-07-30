package com.example.back.web.server;

import com.example.back.web.base.HistDto;
import com.example.back.web.base.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ServerDto extends HistDto<Server> {

    private String name;
    private Type type;
    private int port ;
    private String desc;
    private String responseData;

    public ServerDto(final Server server){
        super(server);

        this.name = server.getName();
        this.type = server.getType();
        this.port = server.getPort();
        this.desc = server.getDesc();
        this.responseData = server.getResponseData();
    }

    public Server toEntity(){
        return Server.builder()
                .name(name)
                .type(type)
                .port(port)
                .desc(desc)
                .responseData(this.responseData).build();
    }
}
