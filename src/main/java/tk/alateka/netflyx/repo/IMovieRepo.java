package tk.alateka.netflyx.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tk.alateka.netflyx.model.Movie;

public interface IMovieRepo extends JpaRepository<Movie, Integer>{

}
