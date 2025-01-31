package tn.iit.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.iit.dao.ClientRepository;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(String cin) {
        return clientRepository.findById(cin).orElse(null);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }
    public boolean existsByCin(String cin) {
        return clientRepository.existsByCin(cin);
    }
  
    public boolean deleteById(String cin) {
        Optional<Client> client = clientRepository.findById(cin);
        if (client.isPresent()) {
        	clientRepository.delete(client.get());
            return true;
        } else {
            return false; // Client not found
        }
    }

}
