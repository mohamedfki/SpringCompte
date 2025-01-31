package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tn.iit.entity.Client;
import tn.iit.service.ClientService;
@Controller
public class ErrorController {

    @GetMapping("/erreur")
    public String showErrorPage() {
        return "erreur"; // Make sure this maps to the 'erreur.html' template
    }
}
