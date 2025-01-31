package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import tn.iit.entity.Compte;
import tn.iit.service.ClientService;
import tn.iit.service.CompteService;
@Controller
@RequestMapping("/comptes")
public class CompteController {

    private final CompteService compteService;
    private final ClientService clientService;

    @Autowired
    public CompteController(CompteService compteService, ClientService clientService) {
        this.compteService = compteService;
        this.clientService = clientService;
    }

    // Get list of all comptes and show in Thymeleaf view
    @GetMapping
    public String getAllComptes(HttpSession session, Model model) {
        // Retrieve the username from the session
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("clients", clientService.findAll()); // Fetch all clients
        model.addAttribute("compte", new Compte()); // For creating a new Compte
        model.addAttribute("comptes", compteService.findAll()); // Fetch all comptes
        return "comptes"; // Refers to `comptes.html`
    }

    // Get details of a specific compte by RIB
    @GetMapping("/{rib}")
    public String getCompteById(@PathVariable Integer rib, Model model) {
        Compte compte = compteService.findById(rib);
        if (compte == null) {
            return "redirect:/comptes"; // Redirect if the compte is not found
        }
        // Fetch all clients to populate select options
        model.addAttribute("clients", clientService.findAll()); 
        model.addAttribute("compte", compte);
        return "compte-detail"; // Refers to `compte-update.html`
    }

    // Create new Compte (POST method for creation)
    @PostMapping
    public String createCompte(@RequestParam String clientCin, @ModelAttribute Compte compte) {
        compteService.save(compte, clientCin); // Save the compte with the associated client
        return "redirect:/comptes"; // Redirect to the list after creation
    }

   
    @DeleteMapping("/delete/{rib}")
    public ResponseEntity<String> deleteClient(@PathVariable Integer rib) {
        try {
        	compteService.deleteById(rib);
            return ResponseEntity.ok("rib deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete the comptes.");
        }
    }

    // Update a compte's solde and client (POST method for updating)
    @PostMapping("/{rib}/update")
    public String updateCompte(@PathVariable Integer rib, @ModelAttribute Compte updatedCompte, @RequestParam String clientCin) {
        compteService.updateCompte(rib, updatedCompte, clientCin); 
        return "redirect:/comptes"; // Redirect back to the list of comptes after update
    }
}
