package tn.iit.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest; // Update for Jakarta
import jakarta.servlet.http.HttpServletResponse; // Update for Jakarta
import java.io.IOException;

@Controller
public class LogoutController implements LogoutSuccessHandler {

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Invalidate the session
        request.getSession().invalidate();
        
        // Redirect to login page after logout
        response.sendRedirect("/login?logout");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // Optional: custom actions when logout is successful
        response.sendRedirect("/login?logout");
    }
}
