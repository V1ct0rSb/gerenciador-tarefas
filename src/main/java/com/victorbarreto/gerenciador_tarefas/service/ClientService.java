package com.victorbarreto.gerenciador_tarefas.service;

import org.springframework.stereotype.Service;
import com.victorbarreto.gerenciador_tarefas.entity.Client;
import com.victorbarreto.gerenciador_tarefas.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client salvar(Client client) {
        return clientRepository.save(client);
    }

    public Client obterPorClientID(String clientId) {
        return clientRepository.findByClientId(clientId);
    }


}
