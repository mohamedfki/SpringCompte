package tn.iit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import tn.iit.dao.UserRepository;
import tn.iit.entity.User;
import tn.iit.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class UserController {

    @Autowired
    private UserService userService; // Service for business logic
    @Autowired
    private UserRepository userRepository; // Repository for database operations
    @Autowired
    private CustomUserDetailsService customUserDetailsService; // UserDetailsService for security

    // Example: Registration page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Your view name for the registration form
    }

    // Example: Registration submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Encrypt password using BCryptPasswordEncoder
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // Save user to the repository
        userRepository.save(user);
        return "redirect:/login"; // Redirect to the login page after registration
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        // Optionally, you can add some logic here if needed
        return "redirect:/login?logout";  // Redirect to login page after logout
    }
    // Example: Login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Your view name for the login page
    }


}
