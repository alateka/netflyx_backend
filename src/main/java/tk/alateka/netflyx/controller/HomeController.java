package tk.alateka.netflyx.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/login")
    public String login(@RequestParam(name = "nombre", required = false) String nombre, Model model) {
        model.addAttribute("nombre", nombre);
        return "login";
    }
}
