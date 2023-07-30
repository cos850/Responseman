package com.example.back.web.server;

import com.example.back.web.base.HistEntity;
import com.example.back.web.base.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Server extends HistEntity {
    // PK Mapping 참고 : https://www.baeldung.com/hibernate-identifiers
    @Id
    private String name;

    private Type type;

    @Column(nullable = false)
    private int port;

    private String desc;

    private String responseData;

    public void setPort(int port) {
        this.port = port;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
}
