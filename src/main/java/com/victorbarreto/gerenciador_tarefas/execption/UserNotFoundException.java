package com.victorbarreto.gerenciador_tarefas.execption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
