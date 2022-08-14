package tk.alateka.netflyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        path = "api/1/login",
        produces = "application/json",
        consumes = "application/json"
    )
    public String login(@RequestBody User user) {
        String token;
        List<User> users = userRepo.findAll();

        for (User dbUser : users) {

            if ( dbUser.getEmail().equals(user.getEmail())
                && dbUser.getPassword().equals(user.getPassword())
            ) {
                token = jwtUtils.create(Integer.toString(dbUser.getId()), dbUser.getEmail());
                return token;
            }
        }
        return "Error";
    }

    @CrossOrigin
    @GetMapping(path = "api/1/check")
    public String checkLogin(@RequestHeader(value = "Authorization") String token) {

        try {
            String userEmail = jwtUtils.getValue(token);
            String userID = jwtUtils.getKey(token);

            List<User> users = userRepo.findAll();

            for (User dbUser : users) {
                
                if ( dbUser.getId() == Integer.parseInt(userID) 
                    && dbUser.getEmail().equals(userEmail)) 
                        return "OK";
            }
            return "Error";
        } catch (Exception e) {
            return "Error";
        }
    }
}
