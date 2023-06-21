package com.example.back.management.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("server")
public class ServerController {

    private final ServerService service;

    @Autowired
    public ServerController(ServerService service){
        this.service = service;
    }

    /**
     * RequestBody      : json http body data -> java object (setter 불필요)
     * ModelAttribute   : multipart/form-data http body -> java object (setter 필요)
     */

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ServerDto dto){
        try{
            return ResponseEntity.ok().body(service.save(dto));
        }catch (Throwable th) {
            if(th instanceof IllegalArgumentException)
                return ResponseEntity.badRequest().body(th.getMessage());

            return ResponseEntity.internalServerError().body(th.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ServerDto dto){
        try{
            return ResponseEntity.ok().body(service.update(dto));
        } catch (Throwable th) {
            if(th instanceof IllegalArgumentException)
                return ResponseEntity.badRequest().body(th.getMessage());

            return ResponseEntity.internalServerError().body(th.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("name") String name){
        try {
            Assert.notNull(name, "서버명이 입력되어야 합니다.");

            return ResponseEntity.ok().body(service.delete(name));
        } catch (Throwable th){
            if(th instanceof IllegalArgumentException)
                return ResponseEntity.badRequest().body(th.getMessage());
            else
                return ResponseEntity.internalServerError().body(th.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getOne(@RequestParam("name") String name){
        try{
            Assert.notNull(name, "서버명이 입력되어야 합니다.");

            return ResponseEntity.ok().body(service.getServer(name));
        }catch (Throwable th){
            if(th instanceof IllegalArgumentException)
                return ResponseEntity.badRequest().body(th.getMessage());
            else
                return ResponseEntity.internalServerError().body(th.getMessage());
        }
    }

    @PostMapping(path="/list")
    public ResponseEntity<?> list(){
        try{
            return ResponseEntity.ok().body(service.list());    
        }catch(Throwable th) {
            return ResponseEntity.internalServerError().body("목록 조회 실패");
        }
    }


}
