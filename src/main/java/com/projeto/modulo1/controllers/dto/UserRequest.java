package com.projeto.modulo1.controllers.dto;

import java.util.Date;

public record UserRequest(
    String nome,
    String password,
    String email,
    Date dtaNasc
) {}
