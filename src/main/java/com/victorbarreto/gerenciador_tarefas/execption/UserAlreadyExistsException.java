package com.victorbarreto.gerenciador_tarefas.execption;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}

