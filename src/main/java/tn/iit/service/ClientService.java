package tn.iit.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.iit.dao.ClientRepository;
import tn.iit.dao.CompteRepository;
import tn.iit.entity.Client;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final CompteRepository compteRepository;

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
        Optional<Client> clientOpt = clientRepository.findById(cin);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            compteRepository.deleteAll(client.getComptes());
            clientRepository.delete(client);
            return true;
        }
        return false;
    }

}
