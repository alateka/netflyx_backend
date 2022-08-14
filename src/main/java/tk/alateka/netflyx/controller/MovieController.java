package tk.alateka.netflyx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import tk.alateka.netflyx.model.User;
import tk.alateka.netflyx.model.Movie;
import tk.alateka.netflyx.repo.IUserRepo;
import tk.alateka.netflyx.utils.JWTUtils;
import tk.alateka.netflyx.repo.IMovieRepo;

@RestController
public class MovieController {

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private IMovieRepo movieRepo;

    private String result;

    @Autowired
    private JWTUtils jwtUtils;
    
    @CrossOrigin
    @GetMapping(
        path = "api/1/movies",
        produces = "application/json"
        )
    public String getUsers(@RequestHeader(value = "Authorization") String token) {

        String userEmail = jwtUtils.getValue(token);
        String userID = jwtUtils.getKey(token);

        List<User> users = userRepo.findAll();
        List<Movie> movies = movieRepo.findAll();

        for (User dbUser : users) {
            
            if ( dbUser.getId() == Integer.parseInt(userID) 
                && dbUser.getEmail().equals(userEmail)) {
                    
                    result = "{ \"movies\":[";
                    for (Movie movie : movies) {
                        result += "{\"name\":\""+movie.getName()+"\",";
                        result += "\"description\":\""+movie.getDescription()+"\",";
                        result += "\"url\":\""+movie.getUrl()+"\",";
                        result += "\"poster\":\""+movie.getPoster()+"\"},";
                    }
                    result = result.substring(0, result.length()-1);
                    return result += "]}";
            }
        }
        return "Error";
    }
}
