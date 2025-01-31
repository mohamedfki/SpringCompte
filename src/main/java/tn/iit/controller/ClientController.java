package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import tn.iit.entity.Client;
import tn.iit.service.ClientService;
@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // View All Clients
    @GetMapping
    public String getAllClients(HttpSession session,Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("client", new Client()); // For the modal form
        return "clients"; // Refers to `clients.html`
    }

    // Save Client (Create or Update)
    // Save Client (Create or Update)
    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") Client client, @RequestParam(value = "editMode", defaultValue = "false") boolean editMode, Model model) {
        if (editMode) {
            // Handle edit logic, e.g., update existing client
            clientService.save(client);
            return "redirect:/clients"; // Redirect to the client list after update
        } else {
            // Handle add logic
            if (clientService.existsByCin(client.getCin())) {
                // Add error message to model
                model.addAttribute("error", "The CIN already exists in the system!");
                return "erreur"; // Return the same page to show the error
            }
            clientService.save(client); // Save the new client
            return "redirect:/clients"; // Redirect to refresh the client list
        }
    }
    @PostMapping("/delete/{cin}")
    public ResponseEntity<String> deleteClient(@PathVariable("cin") String cin, @RequestParam("_csrf") String csrfToken) {
        // Validate CSRF token, Spring Security should do this automatically
        boolean isDeleted = clientService.deleteById(cin);

        if (isDeleted) {
            return ResponseEntity.ok("Client deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting client");
        }
    }



}
