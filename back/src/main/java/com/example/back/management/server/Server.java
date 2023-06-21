package com.example.back.management.server;

import com.example.back.base.HistEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Server extends HistEntity {
    // PK Mapping 참고 : https://www.baeldung.com/hibernate-identifiers
    @Id
    private String name;

    @Column(nullable = false)
    private int port;

    private String desc;

    public void setPort(int port) {
        this.port = port;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
