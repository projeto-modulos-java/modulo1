package com.projeto.modulo1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.modulo1.controllers.adapters.UserDTOAdapter;
import com.projeto.modulo1.controllers.dto.UserRequest;
import com.projeto.modulo1.controllers.dto.UserResponse;
import com.projeto.modulo1.services.UserService;

import jakarta.annotation.security.RolesAllowed;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    final private UserService service;
    final private UserDTOAdapter adapter;
    final private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserService service, UserDTOAdapter adapter){
        this.service = service;
        this.adapter = adapter;
    }

    @GetMapping("teste")
    @RolesAllowed("GUI")
    public String teste(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> get(@PathVariable int id){
        logger.info("Listar usuario por id: {}", id);
        return ResponseEntity.ok(adapter.adaptResponse(service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll(Pageable pageable){
        logger.info("Listar todos os usuários");
        var users = this.adapter.adaptResponse(service.findAll(pageable));
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Object> post(@RequestBody UserRequest user) throws Exception{
        logger.info("Cadastro novo usuário");
        service.createUser(adapter.adaptUser(user));
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> put(@PathVariable("id") int id, @RequestBody UserRequest user) throws Exception{
        logger.info("Atualizando usuário: {}", id);
        service.updateUser(adapter.adaptUser(user), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity<Object> delete(@PathVariable int id){
        logger.info("Desativando usuário: {}", id);
        this.service.delete(id);
        return ResponseEntity.ok().build();
    }

}