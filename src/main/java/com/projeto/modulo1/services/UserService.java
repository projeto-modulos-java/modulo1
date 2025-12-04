package com.projeto.modulo1.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projeto.modulo1.entities.User;
import com.projeto.modulo1.repositories.UserRepository;
import com.projeto.modulo1.repositories.entities.UserEntity;
import com.projeto.modulo1.services.adapters.UserAdapter;
import com.projeto.modulo1.services.exceptions.UserException;

@Service

public class UserService {

    final private UserRepository repository;
    final private UserAdapter adapter;

    public UserService(UserRepository repository, UserAdapter adapter){
        this.repository = repository;
        this.adapter = adapter;
    }

    public User findById(int id){
        return adapter.adaptUser(repository.findByIdAndAtivoIsTrue(id).orElseThrow());
    }

    public List<User> findAll(Pageable pageable){
        return adapter.adaptUsers(repository.findByAtivoIsTrue(pageable));
    }

    public void createUser(User user) throws Exception{
        if(this.repository.findByEmail(user.getEmail()).isPresent()) throw new UserException("Usuário com este email já existe");
        user.setAtivo(true);
        this.repository.save(adapter.adaptUser(user));
    }

    public void updateUser(User user, int id) throws Exception{

        UserEntity entity = this.repository.findById(id).orElse(null);

        User userExistence = null;

        if(entity != null){
            userExistence = adapter.adaptUser(entity);
        }

        if(userExistence == null || userExistence.getId() == id) {
            user.setId(id);
            user.setAtivo(true);
            this.repository.save(adapter.adaptUser(user));
        } else {
            throw new UserException("Usuario com email ja cadastrado");
        }

    }
    public void delete(int id){
        User user = this.findById(id);
        user.setAtivo(false);
        this.repository.save(adapter.adaptUser(user));
    }


}
