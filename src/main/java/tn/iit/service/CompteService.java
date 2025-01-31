package tn.iit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tn.iit.dao.ClientRepository;
import tn.iit.dao.CompteRepository;
import tn.iit.entity.Client;
import tn.iit.entity.Compte;
import tn.iit.exception.CompteNotFoundException;

@RequiredArgsConstructor
@Transactional
@Service
public class CompteService {

	   @Autowired
	    private CompteRepository compteRepository;
	   
	   @Autowired
	    private ClientRepository clientRepository;


	    public List<Compte> findAll() {
	        return compteRepository.findAll();
	    }

	    public Compte findById(Integer rib) {
	        return compteRepository.findById(rib).orElse(null);
	    }

	    public Compte save(Compte compte, String clientCin) {
	        // Find the client by CIN
	        Client client = clientRepository.findById(clientCin)
	            .orElseThrow(() -> new IllegalArgumentException("Client with CIN " + clientCin + " not found"));

	        // Associate the client with the compte
	        compte.setClient(client);

	        // Save the compte
	        return compteRepository.save(compte);
	    }

	    public boolean deleteById(Integer rib) {
	        Optional<Compte> compte = compteRepository.findById(rib);
	        if (compte.isPresent()) {
	        	compteRepository.delete(compte.get());
	            return true;
	        } else {
	            return false; // Client not found
	        }
	    }
	    public List<Compte> findByClientCin(String cin) {
	        return compteRepository.findByClientCin(cin);
	    }
	    
	    public Compte updateCompte(Integer rib, Compte updatedCompte, String clientCin) {
	        // Find the existing Compte by rib
	        Compte existingCompte = compteRepository.findById(rib)
	            .orElseThrow(() -> new IllegalArgumentException("Compte with RIB " + rib + " not found"));

	        // Find the client by CIN
	        Client client = clientRepository.findById(clientCin)
	            .orElseThrow(() -> new IllegalArgumentException("Client with CIN " + clientCin + " not found"));

	        // Update attributes
	        existingCompte.setSolde(updatedCompte.getSolde());
	        existingCompte.setClient(client);

	        // Save the updated Compte
	        return compteRepository.save(existingCompte);
	    }
	    		
	    public Compte updateCompte(Integer rib, Compte updatedCompte) {
	        // Find the compte by rib
	        Compte existingCompte = compteRepository.findById(rib)
	            .orElseThrow(() -> new IllegalArgumentException("Compte with RIB " + rib + " not found"));

	        // Update the fields of the existing compte
	        existingCompte.setSolde(updatedCompte.getSolde());
	        existingCompte.setClient(updatedCompte.getClient());

	        // Save the updated compte back to the repository
	        return compteRepository.save(existingCompte);
	    }

	}