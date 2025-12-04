package com.projeto.modulo1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;

import com.projeto.modulo1.repositories.entities.UserEntity;

public interface UserRepository extends ListCrudRepository<UserEntity, Integer>{
    public Optional<UserEntity> findByEmail(String email);
    public List<UserEntity> findByAtivoIsTrue(Pageable pageable);
    public Optional<UserEntity> findByIdAndAtivoIsTrue(int id);
}
