package com.victorbarreto.gerenciador_tarefas.execption;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String msg) {
        super(msg);
    }
}
