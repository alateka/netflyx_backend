package tk.alateka.netflyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tk.alateka.netflyx.model.User;
import tk.alateka.netflyx.repo.IUserRepo;
import tk.alateka.netflyx.utils.JWTUtils;

@RestController
public class AuthController {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private JWTUtils jwtUtils;
    
    @CrossOrigin
    @PostMapping(
        path = "api/1/login"
    )
    public String login(@RequestBody User user) {
        String token;
        List<User> users = userRepo.findAll();
        for (User dbUser : users) {
            if ( dbUser.getPassword() == user.getPassword() 
                && dbUser.getEmail() == user.getEmail()) {
                    token = jwtUtils.create(Integer.toString(dbUser.getId()), dbUser.getEmail());
                    return token;
            }
        }
        return "Error";
    }
}
