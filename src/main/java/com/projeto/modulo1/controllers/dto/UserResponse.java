package com.projeto.modulo1.controllers.dto;

import java.util.Date;

public record UserResponse(
    Integer id,
    String nome,
    String email,
    Date dtaNasc,
    boolean ativo
) { }
