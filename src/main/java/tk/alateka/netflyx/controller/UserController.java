package tk.alateka.netflyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tk.alateka.netflyx.model.User;
import tk.alateka.netflyx.repo.IUserRepo;
import tk.alateka.netflyx.utils.JWTUtils;

@RestController
public class UserController {

    @Autowired
    private IUserRepo userRepo;

    private String result;

    @Autowired
    private JWTUtils jwtUtils;
    
    @GetMapping(
        path = "api/1/users",
        produces = "application/json"
        )
    public String getUsers(@RequestHeader(value = "Authorization") String token) {

        String userEmail = jwtUtils.getValue(token);
        String userID = jwtUtils.getKey(token);

        List<User> users = userRepo.findAll();

        for (User dbUser : users) {
            
            if ( dbUser.getId() == Integer.parseInt(userID) 
                && dbUser.getEmail().contains(userEmail)) {
                    
                    result = "{ \"users\":[";
                    for (User user : users) {
                        result += "{\"name\":\""+user.getName()+"\",";
                        result += "\"email\":\""+user.getEmail()+"\"},";
                    }
                    result = result.substring(0, result.length()-1);
                    return result += "]}";
            }
        }
        return "Error";
    }
}
